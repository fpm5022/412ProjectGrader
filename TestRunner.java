
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

    /*
    path:
    classpath: absolute path to .class file to execute
    */
    public TestRunner(String path, String classPath) {
        this.path = path;
        this.classPath = classPath;
        this.outputFileName = "test-output.txt";
    }

    public void runJava() {
        try {
            File outputFile = new File(classPath + outputFileName);
            
//        create new java ProcessBuilder using arg ArrayList
            ProcessBuilder pb = new ProcessBuilder("java", classPath);
            
//        Create environment map and set environmental variables
            Map<String, String> env = pb.environment();
            env.clear();
            env.put("PATH", path);
            env.put("CLASSPATH", classPath);
            
//        Determine current working directory
            File cwd = pb.directory();

            File nwd = TestTools.cd(cwd, classPath);
            
//        set ProcessBuilder working directory to new abstract path
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
