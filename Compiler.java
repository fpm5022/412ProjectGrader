
import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Compiler {
    private String path;
    private String classPath;
    private String studentPath;
    private String outputFileName;
    private int success;
    private String mainClassName; // main java file to compile

    public Compiler(String pth, String clsPath, String srcPath, String stdPath, String outFileName, String mainClassName) {
        path = pth;
        classPath = clsPath;
        studentPath = stdPath;
        outputFileName = outFileName;
        success = 1;  // Outcome of compilation, success = 0
        this.mainClassName = mainClassName;
    }

    public int compileJava() {
        try {
            boolean createBin = new File(classPath).mkdir();
       
            ProcessBuilder pb
                    = new ProcessBuilder("javac", "-d", classPath, studentPath);
      
            Map<String, String> env = pb.environment();
            env.clear();
            env.put("PATH", path);
            env.put("CLASSPATH", classPath);

            File cwd = pb.directory();
            File outputFile = new File(classPath + "/" + outputFileName);

            outputFile.delete();
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
