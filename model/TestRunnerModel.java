/**
@author Feek <feek@psu.edu>
**/

package model;

/**
 * This struct is used for holding all data necessary to run a test
 */

public class TestRunnerModel {
    public final String path;
    public final String classPath;
    public String mainClassName;
    public String[] scannerInput;
    public String[] commandLineArgs;
    public String expectedOutput;

    /*
     example usage: 

     String[] scannerInput = {"1", "1"};
     String[] commandLineArgs = {};
     TestRunnerModel t = new TestRunnerModel("/Users/Feek/Desktop/compiled/412/", "/Users/Feek/Desktop/compiled/412/smithjq/", "ArrayLoops", commandLineArgs, scannerInput);

     -------------

     path: parent directory of classpath
     classpath: absolute path containing .class files
     mainClassName: name of .class file to compile (not containing .class in name)
     commandLineArgs (OPTIONAL): array of strings to pass as command line args
     scannerInput (OPTIONAL): array of strings to pass as scanner input 
     */
    public TestRunnerModel(String path, String classPath, String mainClassName, String[] commandLineArgs, String[] scannerInput, String expectedOutput) {
        this.path = path;
        this.classPath = classPath;
        this.mainClassName = mainClassName;
        this.commandLineArgs = commandLineArgs;
        this.scannerInput = scannerInput;
        this.expectedOutput = expectedOutput;
    }
}
