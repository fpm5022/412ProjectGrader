package test;

import controller.TestRunnerController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.assertEquals;
import model.TestRunnerModel;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Feek on 11/1/15.
 */
public class TestRunnerControllerTest {

    private TestRunnerModel model;
    private ProcessBuilder pb;

    @Before
    public void setUp() throws Exception {
        Properties prop = new Properties();
        InputStream input;
        try {
            String url = "/Users/Feek/repos/412ProjectGrader/test/test.properties";
            input = new FileInputStream(url);
               prop.load(input);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CompilerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CompilerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        String classPath = prop.getProperty("classPath");
        String mainClassName = "ArrayLoops";
        String[] commandLineArgs = {};
        String[] scannerInput = {"1", "1"};
        String expectedOutput = "1n = 1; range = 1; average = 1.0; stdDev = 0.0";
        this.model = new TestRunnerModel(classPath, mainClassName, commandLineArgs, scannerInput, expectedOutput);
        this.pb = new ProcessBuilder();
    }

    @Test
    public void testBuildProcess() throws Exception {
        this.pb = TestRunnerController.buildProcess(model.mainClassName, model.commandLineArgs);
        List<String> expected = new ArrayList<>();
        expected.add("java");
        expected.add("ArrayLoops");

        assertEquals("process was not built as expected", expected, pb.command());
    }

    @Test
    public void testSetUpEnvironment() throws Exception {
        TestRunnerController.setUpEnvironment(pb, model.classPath);
        assertEquals("process builder is not in the correct directory.", model.classPath, pb.directory().getAbsolutePath() + File.separator);
        System.out.println(pb.directory().getAbsolutePath());
        Map env = pb.environment();
        
        assertEquals("classpath was not set properly", env.get("CLASSPATH"), model.classPath);
    }

    @Test
    public void testRunAndTestJava() throws Exception {
        int similarity = TestRunnerController.runAndTestJava(this.model);
        assertEquals("test java similarity was not correct", similarity, 100);
    }
    
    @Test
    public void testCaptureProcessOutput() throws Exception {
        ArrayList<String> args = new ArrayList<>();
        
        /**
        args.add("cmd");
        args.add("/C");
        args.add("dir");
*/
        
        args.add("ls");
        
        ProcessBuilder pb2 = new ProcessBuilder(args);
        Process p = pb2.start();
        
        String output = TestRunnerController.captureProcessOutput(p, null);
        
        assertNotNull("no output was captured from issuing the LS command", output);
        
        p.waitFor();
    }
    
    @Test
    public void testCompareResults() throws Exception {
        int res = TestRunnerController.compareResults("The quick fox jumped", "The fox jumped");
        assertEquals(res, 70);
        
        res = TestRunnerController.compareResults("The quick fox jumped", "The fox");
        assertEquals(res, 35);
    }
}
