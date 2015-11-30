package controller;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CompilerModel;

public class Compiler {

    public static int compileJava(CompilerModel model) {
        try {
            new File(model.classPath).mkdir();
       
            ProcessBuilder pb
                    = new ProcessBuilder("javac", model.sourcePath, "-d", model.classPath);
            
            
            model.outputFile = setUpEnv(model, pb);

            pb.redirectErrorStream(true);
            pb.redirectOutput(Redirect.appendTo(model.outputFile));
            Process p = pb.start();

            //    need other processes to wait for compilation to finish
            //    basically joins the thread to the javac process to force sequential
            //    execution - need to be careful - if any process hangs, whole run hangs
            model.success = p.waitFor();

            assert pb.redirectInput() == Redirect.PIPE;
            assert pb.redirectOutput().file() == model.outputFile;
            assert p.getInputStream().read() == -1;

        } catch (IOException ex) {
            Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model.success;
    }
    
    public static File setUpEnv(CompilerModel model, ProcessBuilder pb){
            Map<String, String> env = pb.environment();
            env.clear();
            env.put("PATH", model.path);
            env.put("CLASSPATH", model.classPath);
            
            File cwd = pb.directory();
            File outputFile = new File(model.classPath + "/" + model.outputFileName);
            return outputFile;
    }
    
}