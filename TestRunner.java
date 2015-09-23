
import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestRunner {

    private final String path;
    private final String classPath;
    private final String outputFileName;
    private String mainClassAbsolutePath;
    
    public static void main(String[] args) {
        TestRunner t = new TestRunner("/Users/Feek/Desktop/compiled/412/", "/Users/Feek/Desktop/compiled/412/smithjq/", "ArrayLoops");
        t.runJava();
    }
    private String mainClassName;
    

    /*
    path:
    classpath: absolute path containing .class files
    mainClassName: name of .class file to compile (not containing .class in name)
    */
    public TestRunner(String path, String classPath, String mainClassName) {
        this.path = path;
        this.classPath = classPath;
        this.outputFileName = "test-output.txt";
        this.mainClassName = mainClassName;
        this.mainClassAbsolutePath = classPath + mainClassName;
    }

    public void runJava() {
        try {
            File outputFile = new File(classPath + outputFileName);
            System.out.println(classPath + outputFileName);
            
//        create new java ProcessBuilder using arg ArrayList
            ProcessBuilder pb = new ProcessBuilder("java", mainClassName);
            
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
            pb.redirectOutput(Redirect.appendTo(outputFile));
            
//        start java process
            Process p = pb.start();
            
//        want processes to run sequentially to keep output in order         
//        basically joins thread to process to force sequential execution
//        need to be careful - if any process hangs, whole run hangs
            p.waitFor();
            
            assert pb.redirectInput() == Redirect.PIPE;
            assert pb.redirectOutput().file() == outputFile;
            assert p.getInputStream().read() == -1;
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(TestRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void compareResults() {
    }
}
