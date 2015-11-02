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
import util.TestTools;

public class TestRunner {

    private final String path;
    private final String classPath;
    private String mainClassName;
    private String[] scannerInput;
    private String[] commandLineArgs;
    private String expectedOutput;
    private String actualOutput;
    
    /*
        example usage: 

            String[] scannerInput = {"1", "1"};
            String[] commandLineArgs = {};
            TestRunner t = new TestRunner("/Users/Feek/Desktop/compiled/412/", "/Users/Feek/Desktop/compiled/412/smithjq/", "ArrayLoops", commandLineArgs, scannerInput);
            t.testJava();

        -------------

        path: parent directory of classpath
        classpath: absolute path containing .class files
        mainClassName: name of .class file to compile (not containing .class in name)
        commandLineArgs (OPTIONAL): array of strings to pass as command line args
        scannerInput (OPTIONAL): array of strings to pass as scanner input 
    */
    public TestRunner(String path, String classPath, String mainClassName, String[] commandLineArgs, String[] scannerInput, String expectedOutput) {
        this.path = path;
        this.classPath = classPath;
        this.mainClassName = mainClassName;
        this.commandLineArgs = commandLineArgs;
        this.scannerInput = scannerInput;
        this.expectedOutput = expectedOutput;
    }

    public boolean testJava() {
        try {
            // this args list will contain all arguments to pass to the process builder
            ArrayList<String> args = new ArrayList<>();
            
            args.add("java");
            args.add(mainClassName);
            
            // if command line args were supplied, pop them into the list of args to pass 
            // to the process builder
            if(commandLineArgs != null) {
                args.addAll(Arrays.asList(commandLineArgs));
            }
            
            // will look something like `java ArrayLoops "1" "1"`
            ProcessBuilder pb = new ProcessBuilder(args);
            
//        Create environment map and set environmental variables
            Map<String, String> env = pb.environment();
            env.clear();
            env.put("PATH", path);
            env.put("CLASSPATH", classPath);
            
            // we need to get this process builder into the class path directory in order to execute .class
            File cwd = pb.directory();
            File nwd = TestTools.cd(cwd, classPath);
            pb.directory(nwd);
            
            pb.redirectErrorStream(true);
            //pb.redirectOutput(Redirect.appendTo(outputFile)); we want to be able to capture output in this class, so
            // not writing to file direclty
            
            System.out.println("Beginning testing of " + classPath + File.separator + mainClassName);
            
            Process p = pb.start();
            
            // these allow communication with program being tested
            InputStream stdout = p.getInputStream();
            OutputStream stdin = p.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
            
            // wait for the reader to be ready
            while(!reader.ready()) {
                Thread.yield();
            }
            
            Scanner inScanner = new Scanner(stdout);
            
            int i = 0; // index of scanner input to read from
            
            StringBuilder sb = new StringBuilder();
            // read from program being tested
            while(inScanner.hasNextLine()) {
                String line = inScanner.nextLine();
                
                // only provide input if enough scanner inputs were provided 
                if(scannerInput != null && scannerInput.length > i) {
                    //System.out.println("writing: " + scannerInput[i]);
                    writer.write(scannerInput[i]);
                    writer.newLine();
                    writer.flush();
                    i++;
                } else {
                    if (scannerInput != null) {
                        // there is no more scanner input, start building the output
                        // to compare
                        sb.append(line);
                    }
                }
            }
            
            this.actualOutput = sb.toString();
            
//        want processes to run sequentially to keep output in order         
//        basically joins thread to process to force sequential execution
//        need to be careful - if any process hangs, whole run hangs
            p.waitFor();
            
            assert pb.redirectInput() == Redirect.PIPE;
            assert p.getInputStream().read() == -1;
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(TestRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return compareResults();
    }

    private boolean compareResults() {
        System.out.println("expected: " + expectedOutput);
        System.out.println("actual: " + actualOutput);
        
        // might want to refine this...
        if (actualOutput.equals(expectedOutput) ||
                expectedOutput.contains(actualOutput) ||
                actualOutput.contains(expectedOutput)
        ) {
            return true;
        }
        return false;
    }
}
