package test;

import controller.FunctionsPanelController;
import model.FunctionsPanelModel;
import model.Student;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Feek <feek@psu.edu>
 */
public class FunctionsPanelControllerTest {
    
    public FunctionsPanelControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sourceDirectoryActionPerformed method, of class FunctionsPanelController.
     */
    @Test
    public void testSourceDirectoryActionPerformed() {
        // noop
    }

    /**
     * Test of compilePathActionPerformed method, of class FunctionsPanelController.
     */
    @Test
    public void testCompilePathActionPerformed() {
        // noop
    }

    /**
     * Test of compileActionPerformed method, of class FunctionsPanelController.
     */
    @Test
    public void testCompileActionPerformed() {
        // noop
    }

    /**
     * Test of testCode method, of class FunctionsPanelController.
     */
    @Test
    public void testTestCode() {
        // noop
    }

    /**
     * Test of getStudentCompilePath method, of class FunctionsPanelController.
     */
    @Test
    public void testGetStudentCompilePath() {
        FunctionsPanelModel model = new FunctionsPanelModel();
        model.compilePathDirectory = "dir/";
        Student student = new Student("feek", "123");
        String result = FunctionsPanelController.getStudentCompilePath(model, student);
        assertEquals("dir/feek", result);
    }

    /**
     * Test of clearOutputArea method, of class FunctionsPanelController.
     */
    @Test
    public void testClearOutputArea() {
        // noop
    }
    
}
