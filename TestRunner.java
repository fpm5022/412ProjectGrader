
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestRunner {

    private final String path;
    private final String classPath;
    private String mainClassName;
    private String[] scannerInput;
    
    public static void main(String[] args) {
        String[] scannerInput = {"1", "1"};
        TestRunner t = new TestRunner("/Users/Feek/Desktop/compiled/412/", "/Users/Feek/Desktop/compiled/412/smithjq/", "ArrayLoops", scannerInput);
        t.runJava();
    }
    

    /*
    path: parent directory of classpath
    classpath: absolute path containing .class files
    mainClassName: name of .class file to compile (not containing .class in name)
    scannerInput: array of strings to pass as scanner input
    */
    public TestRunner(String path, String classPath, String mainClassName, String[] scannerInput) {
        this.path = path;
        this.classPath = classPath;
        this.mainClassName = mainClassName;
        this.scannerInput = scannerInput;
    }

    public void runJava() {
        try {
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
            //pb.redirectOutput(Redirect.appendTo(outputFile));
            
            Process p = pb.start();
            
            InputStream stdout = p.getInputStream();
            OutputStream stdin = p.getOutputStream();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
            
            // wait for the ready to be ready
            while(!reader.ready()) {
                Thread.yield();
            }
            
            Scanner inScanner = new Scanner(stdout);
            
            
            int i = 0; // index of scanner input to read from
            System.out.println("scanner input length: " + scannerInput.length);
            
            while(inScanner.hasNextLine()) {
                String line = inScanner.nextLine();
                System.out.println(line);
                
                // only provide input if enough scanner inputs were provided 
                if(scannerInput.length > i) {
                    System.out.println("writing: " + scannerInput[i]);
                    writer.write(scannerInput[i]);
                    writer.newLine();
                    writer.flush();
                    i++;
                } else {
                    //System.out.println("not writing");
                }
            }
            
//        want processes to run sequentially to keep output in order         
//        basically joins thread to process to force sequential execution
//        need to be careful - if any process hangs, whole run hangs
            p.waitFor();
            
            assert pb.redirectInput() == Redirect.PIPE;
            assert p.getInputStream().read() == -1;
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(TestRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void compareResults() {
    }
}
