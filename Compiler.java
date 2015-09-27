
import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Compiler {
    private String path;
    private String classPath;
    private String sourcePath;
    private final String outputFileName = "output.txt";
    private int success;  // Outcome of compilation, success = 0
    
    /*
    Path: 
    Classpath: directory to compile the resulting .class file into
    Sourcepath: the absolute path to the .java file to compile into .class
    */
    public Compiler(String path, String classPath, String sourcePath) {
        this.path = path;
        this.classPath = classPath;
        this.sourcePath = sourcePath;
        this.success = 1;
    }

    public int compileJava() {
        try {
            new File(classPath).mkdir();
       
            ProcessBuilder pb
                    = new ProcessBuilder("javac", sourcePath, "-d", classPath);
      
            Map<String, String> env = pb.environment();
            env.clear();
            env.put("PATH", path);
            env.put("CLASSPATH", classPath);

            File cwd = pb.directory();
            File outputFile = new File(classPath + "/" + outputFileName);
            pb.redirectErrorStream(true);
            pb.redirectOutput(Redirect.appendTo(outputFile));
            Process p = pb.start();

            //    need other processes to wait for compilation to finish
            //    basically joins the thread to the javac process to force sequential
            //    execution - need to be careful - if any process hangs, whole run hangs
            success = p.waitFor();

            assert pb.redirectInput() == Redirect.PIPE;
            assert pb.redirectOutput().file() == outputFile;
            assert p.getInputStream().read() == -1;

        } catch (IOException ex) {
            Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }
}
