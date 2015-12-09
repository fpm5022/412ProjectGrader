package test;

import controller.FunctionsPanelController;
import model.FunctionsPanelModel;
import model.Student;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Feek <feek@psu.edu>
 */
public class FunctionsPanelControllerTest {

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
    
}
