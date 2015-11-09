package test;

import controller.TestRunner;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Feek on 11/1/15.
 */
public class TestRunnerTest {

    private TestRunner runner;

    @Before
    public void setUp() throws Exception {
        String path = "/Users/Feek/Desktop/compiled/412/";
        String classPath = "/Users/Feek/Desktop/compiled/412/smithjq";
        String mainClassName = "ArrayLoops";
        String[] commandLineArgs = {};
        String[] scannerInput = {"1", "1"};
        String expectedOutput = "1n = 1; range = 1; average = 1.0; stdDev = 0.0";
        this.runner = new TestRunner(path, classPath, mainClassName, commandLineArgs, scannerInput, expectedOutput);
    }

    public void tearDown() throws Exception {

    }

    @Test
    public void testTestJava() throws Exception {
        int similarity = this.runner.runAndTestJava();
        assertEquals("test java similarity was not correct", similarity, 100);
    }
}