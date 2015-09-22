import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleTester 
{       
        int studentNumber;
        int runNumber;
        String sourcePath;
        String compiledPath;
        String studentName;
        String studentHandle;
        String testDataPath;
        String argsFileName;
        String testInputFileName;
        
        String classPath = compiledPath
//                    + className + studentName
         ;
        String studentPath = sourcePath + "/" + studentName;
        String inputFileStub = studentPath + "/input";
        String outputFileName = "/output-" + studentName + ".txt";
    public SingleTester(String srcPath,String cmpPath, String tstFilLoc)
    {


         studentNumber = 0;
         runNumber = 1;
         sourcePath = srcPath;
         compiledPath = cmpPath;
         studentName = "blank";
         studentHandle = "000000";
         testDataPath = tstFilLoc;
         argsFileName = testDataPath + "/args.txt";
         testInputFileName = testDataPath + "/TestInput.txt";

         classPath = compiledPath
//                    + className + studentName
                ;
         studentPath = sourcePath + "/" + studentName;
         inputFileStub = studentPath + "/input";
         outputFileName = "/output-" + studentName + ".txt";
         
         
    } 
    
    public void runJava()
    {
        TestRunner r = new TestRunner(runNumber, studentName, studentHandle, compiledPath, classPath,
        sourcePath, studentPath, testDataPath, argsFileName, testInputFileName, inputFileStub,
        outputFileName);
        r.runJava();
        runNumber++;
    }
}
