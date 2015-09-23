
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
    private int success;
    
    /*
    Path: 
    Classpath: directory to compile the resulting .class file into
    Sourcepath: the absolute path to the .java file to compile into .class
    */
    public Compiler(String path, String classPath, String sourcePath) {
        System.out.println("path: " + path);
        System.out.println("clspath: " + classPath);
        System.out.println("srcpath: " + sourcePath);
        this.path = path;
        this.classPath = classPath;
        this.sourcePath = sourcePath;
        this.success = 1;  // Outcome of compilation, success = 0
    }

    public int compileJava() {
        try {
            boolean createBin = new File(classPath).mkdir();
            
            System.out.println("creating bin directory here: " + classPath);
       
            ProcessBuilder pb
                    = new ProcessBuilder("javac", sourcePath, "-d", classPath); // see if second is redundant
            
            System.out.println(pb.command());
      
            Map<String, String> env = pb.environment();
            env.clear();
            env.put("PATH", path);
            System.out.println("putting path: " + path);
            env.put("CLASSPATH", classPath);
            System.out.println("putting classpath: " + classPath);

            File cwd = pb.directory();
            File outputFile = new File(classPath + "/" + outputFileName);

            //outputFile.delete();
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
