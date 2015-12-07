package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TestRunnerModel;
import util.TestTools;

public class TestRunnerController {

    public static String scannerInputDelimiter = "\t>>"; // this is the token used to denote scanner input into the program
    /*
        @return int percentage similar (0 - 100)
    */
    public static void runAndTestJava(TestRunnerModel model) {
        String actualOutput = "";
        try {
            ProcessBuilder pb = buildProcess(model.mainClassName, model.commandLineArgs);
            setUpEnvironment(pb, model.classPath);
            pb.redirectErrorStream(true);
            Process p = pb.start();
            actualOutput = captureProcessOutput(p, model.scannerInput);

            /*
             want processes to run sequentially to keep output in order
             basically joins thread to process to force sequential execution
             need to be careful - if any process hangs, whole run hangs
             */
            p.waitFor();

            assert pb.redirectInput() == Redirect.PIPE;
            assert p.getInputStream().read() == -1;
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(TestRunnerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        model.similarity = compareResults(actualOutput, model.expectedOutput);
        model.actualOutput = actualOutput;
    }
    
    /**
     * @param mainClassName
     * @param commandLineArgs
     * @return ProcessBuilder
     * command will look something like `java ArrayLoops "1" "1"`
     */
    public static ProcessBuilder buildProcess(String mainClassName, String[] commandLineArgs) {
        // this args list will contain all arguments to pass to the process builder
        ArrayList<String> args = new ArrayList<>();

        args.add("java");
        args.add(mainClassName);

        // if command line args were supplied, pop them into the list of args to pass 
        // to the process builder
        if (commandLineArgs != null) {
            args.addAll(Arrays.asList(commandLineArgs));
        }

        return new ProcessBuilder(args);
    }

    // Create environment map and set environmental variables
    // change process builder directory
    public static void setUpEnvironment(ProcessBuilder pb, String classPath) {
        Map<String, String> env = pb.environment();
        env.clear();
        env.put("CLASSPATH", classPath);

        // we need to get this process builder into the class path directory in order to execute .class
        File cwd = pb.directory();
        File nwd = TestTools.cd(cwd, classPath);
        pb.directory(nwd);
    }

    /**
     * captures all of the output of the program and returns the combined string
     * @param p process
     * @param scannerInput
     * @return string output
     */
    public static String captureProcessOutput(Process p, String[] scannerInput) {
        StringBuilder fullOutput = new StringBuilder(); // this captures all of the input and output
        try {
            // these allow communication with program being tested
            InputStream stdout = p.getInputStream();
            OutputStream stdin = p.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));

            // wait for the reader to be ready
            while (!reader.ready()) {
                Thread.yield();
            }

            Scanner inScanner = new Scanner(stdout);

            int i = 0; // index of scanner input to read from

            // read from program being tested
            while (inScanner.hasNextLine()) {
                String line = inScanner.nextLine();
                fullOutput.append(line).append(System.getProperty("line.separator"));
                
                // only provide input if enough scanner inputs were provided
                if (scannerInput != null && scannerInput.length > i) {
                    writer.write(scannerInput[i]);
                    writer.newLine();
                    writer.flush();
                    
                    fullOutput.append(scannerInputDelimiter).append(scannerInput[i]).append(System.getProperty("line.separator"));
                    
                    i++;
                }
            }
            
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(TestRunnerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fullOutput.toString();
    }

    /**
     * @param actual
     * @param expected
     * @return int between 0 - 100
     */
    public static int compareResults(String actual, String expected) {
        actual = sanitizeInput(actual);
        expected = sanitizeInput(expected);
        
        String longer;
        String shorter;

        if (expected.length() > actual.length()) {
            longer = expected;
            shorter = actual;
        } else {
            longer = actual;
            shorter = expected;
        }

        int longerLength = longer.length();

        double similarity = (longerLength - editDistance(longer, shorter)) / (double) longerLength;

        return (int) (similarity * 100);
    }

    // Example implementation of the Levenshtein Edit Distance
    // See http://rosettacode.org/wiki/Levenshtein_distance#Java
    private static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                } else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        }
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0) {
                costs[s2.length()] = lastValue;
            }
        }
        return costs[s2.length()];
    }

    /**
     * strips scanner delimiter, whitespace, tabs, and new lines from string
     * @param s
     * @return 
     */
    private static String sanitizeInput(String s) {
       s = s.replaceAll("\\s+",""); // remove whitespace
       s = s.replaceAll("\\t+",""); // remove tabs
       s = s.replaceAll("\\n+",""); // remove new lines
       s = s.replaceAll(scannerInputDelimiter, ""); // remove scanner delimiter
       s = s.replaceAll(">>", ""); // remove part of scanner input since tabs / new lines have already been removed...
       
       return s;
    }

}
