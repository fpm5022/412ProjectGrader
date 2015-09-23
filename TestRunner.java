
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestRunner {

    private String path;
    private String classPath;
    private String studentPath;
    private String testInputFileName;
    private String inputFileStub;
    private String inputFileName;
    private String outputFileName;
    private final String sourcePath;
    private final String testDataPath;
    private final String argsFileName = "";
    private final int success;

    public TestRunner(String pth, String clsPath, String srcPath, String stdPath, String tstDataPath, String inFileStub) {
        path = pth;
        classPath = clsPath;
        sourcePath = srcPath;
        studentPath = stdPath;
        testDataPath = tstDataPath;
        testInputFileName = "";
        inputFileStub = tstDataPath;
        outputFileName = "";
        success = 1;  // Outcome of compilation, success = 0
    }

    public void runJava() {
        try {

//    set up input files
//    TestInput.txt has inputs for each test on a single line
            File testInputFile = new File(testInputFileName);
            Scanner testInputs = new Scanner(testInputFile);
//    input.txt has inputs for a single run each on a separate line
//    and is created immediately before each test run from TestInput.txt

//    instantiate output file
            File outputFile = new File(classPath + outputFileName);

//    instantiate command-line arguments file
            File argsFile = new File(argsFileName);

//    instantiate argument Scanner
            Scanner argsInput = new Scanner(argsFile);
            
            int run = 0;

            while (argsInput.hasNextLine()) {
                run++;
//        declare arg ArrayList for java ProcessBuilder
                List<String> arg = new ArrayList<String>();
                String argsLine = argsInput.nextLine();
//        parse argsLine via TestTools.parseLine
                arg = TestTools.parseLine(argsLine);
                arg.add(0, "java");

//        scan TestInput.txt
                String testInputLine = testInputs.nextLine();

//        create input file for current run
                List<String> inputs = new ArrayList<String>();
                inputs = TestTools.parseLine(testInputLine);
                inputFileName = inputFileStub + run + ".txt";
                PrintWriter writeTests = new PrintWriter(inputFileName);
                for (String element : inputs) {
                    writeTests.println(element);
                }
                writeTests.close();
                File inputFile = new File(inputFileName);

//        create new java ProcessBuilder using arg ArrayList
                ProcessBuilder pb = new ProcessBuilder(arg);

//        Create environment map and set environmental variables
                Map<String, String> env = pb.environment();
                env.clear();
                env.put("PATH", path);
                env.put("CLASSPATH", classPath);

//        Determine current working directory
                File cwd = pb.directory();
//        NB - ProcessBuilder default is to return a null
//        pointer for the abstract path to indicate that it
//        is using System.Properties "user.dir", i.e., the 
//        current system working directory; hence the
//        critical need to handle a NullPointerException.
//        Also returns a null pointer if the directory
//        doesn't exist.

//        compute new abstract working directory path = studentPath
                File nwd = TestTools.cd(cwd, studentPath);

                /*        debug code - to confirm correct new path
                 String nwdPath = nwd.getAbsolutePath();
                 System.out.println("new cwd path: " + nwdPath);
                 TestTools.dir(nwd);
                 */
//        set ProcessBuilder working directory to new abstract path
                pb.directory(nwd);

//        redirect standard input, error, and output files; print process arguments
                pb.redirectInput(Redirect.from(inputFile));
                pb.redirectErrorStream(true);
                pb.redirectOutput(Redirect.appendTo(outputFile));

//        start java process
                Process p = pb.start();

//        want processes to run sequentially to keep output in order         
//        basically joins thread to process to force sequential execution
//        need to be careful - if any process hangs, whole run hangs
                p.waitFor();

//        alternately, can get it sequential most times by sleeping a bit

                assert pb.redirectInput() == Redirect.PIPE;
                assert pb.redirectOutput().file() == outputFile;
                System.out.println(outputFile);
                assert p.getInputStream().read() == -1;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestRunner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestRunner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void compareResults() {
//      if (this.handle = 000)
//      {
//          
//      }
    }
}
