
import com.sun.org.apache.bcel.internal.generic.InstructionConstants;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleTester {

    public static void main(String[] args) {
        try {
            //  initialize student and class configuration data
            int studentNumber = 0;
            int runNumber = 1;
            String studentName = "blank";
            String studentHandle = "000000";
            String className = "412/";
            String configFileName = "configSingle.txt";
            String path = "/Users/Feek/Desktop/compiled/";

//  set fixed paths and file names:
            String sourcePath = "/Users/Feek/repos/412ProjectGrader/src";
            String testDataPath = sourcePath;
            String argsFileName = testDataPath + "/args.txt";
            String testInputFileName = testDataPath + "/TestInput.txt";
            String mainClassName = "ArrayLoops.java";
            
            /*    config file contains:
             - path to Java jdk (enclosed in quotes) on first line
             e.g. - "C:/Program Files/Java/jdk1.7.0_25/bin"
             - student name (lowercase lastnamefm) on second line
             e.g. - smithjq for John Q. Smith
             - random 6-digit handle on third line
             e.g. - 543890 - use the one given to you in class
             */
            SingleTester s = new SingleTester();
            String configFilePath = s.getClass().getResource("").getPath() + configFileName;
            File configFile = new File(configFilePath);
            Scanner in = new Scanner(configFile);
            String line = in.nextLine();
            Scanner inLine = new Scanner(line);
            path = inLine.next();
            line = in.nextLine();
            inLine = new Scanner(line);
            studentName = inLine.next();
            line = in.nextLine();
            inLine = new Scanner(line);
            studentHandle = inLine.next();

//    set paths and file names:
            String classPath = "/Users/Feek/Desktop/compiled/" + className + studentName;
            String studentPath = sourcePath + "/" + studentName;
            String inputFileStub = studentPath + "/input";
            String outputFileName = "/output-" + studentName + ".txt";
            System.out.println(sourcePath);
//    run javac compiler - returns 0 on success
            Compiler c = new Compiler(runNumber, studentName, studentHandle, path, classPath, sourcePath, studentPath, outputFileName, mainClassName);
            int success = c.compileJava();

//    Print whether or not compile successful
            if (success != 0) {
                System.err.println("Compile failed");
                System.exit(0);
            }

//    Run the test cases
//    TestRunner consructor:
//    public TestRunner(int numbr, String nme, String hndl, String pth, String clsPath,
//    String srcPath, String stdPath, String tstDataPath, String argFileName, 
//    String tstInputFileName, String inputFileName, String outFileName)
            TestRunner r = new TestRunner(runNumber, studentName, studentHandle, path, classPath,
                    sourcePath, studentPath, testDataPath, argsFileName, testInputFileName, inputFileStub,
                    outputFileName);
            r.runJava();
            runNumber++;
            System.out.println();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SingleTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
