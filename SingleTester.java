
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleTester {

    public static void main(String[] args) {
        try {
            SingleTester tester = new SingleTester(); // to get current directory
            Properties prop = new Properties();
            final String PROP_FILE_NAME = "config.properties";
            InputStream inputStream = tester.getClass().getResourceAsStream(PROP_FILE_NAME);

            try {
                prop.load(inputStream);
            } catch (IOException ex) {
                Logger.getLogger(SingleTester.class.getName()).log(Level.SEVERE, null, ex);
            }

            String className = prop.getProperty("className");
            String configFileName = prop.getProperty("configFileName");
            String compiledPath = prop.getProperty("compiledPath");
            String sourcePath = prop.getProperty("sourcePath");
            String mainClassName = prop.getProperty("mainClassName");

            int studentNumber = 0;
            int runNumber = 1;
            String studentName = "blank";
            String studentHandle = "000000";
            String testDataPath = sourcePath;
            String argsFileName = testDataPath + "/args.txt";
            String testInputFileName = testDataPath + "/TestInput.txt";

            /*    config file contains:
             - student name (lowercase lastnamefm) on second line
             e.g. - smithjq for John Q. Smith
             - random 6-digit handle on third line
             e.g. - 543890 - use the one given to you in class
             */
            String configFilePath = tester.getClass().getResource("").getPath() + configFileName;
            File configFile = new File(configFilePath);
            Scanner in = new Scanner(configFile);
            studentName = in.next();
            studentHandle = in.next();

            String classPath = compiledPath + className + studentName;
            String studentPath = sourcePath + "/" + studentName;
            String inputFileStub = studentPath + "/input";
            String outputFileName = "/output-" + studentName + ".txt";

            Compiler c = new Compiler(runNumber, studentName, studentHandle, compiledPath, classPath, sourcePath, studentPath, outputFileName, mainClassName);
            int success = c.compileJava();

            if (success != 0) {
                System.err.println("Compile failed");
                System.exit(0);
            }

            TestRunner r = new TestRunner(runNumber, studentName, studentHandle, compiledPath, classPath,
                    sourcePath, studentPath, testDataPath, argsFileName, testInputFileName, inputFileStub,
                    outputFileName);
            r.runJava();
            runNumber++;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SingleTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
