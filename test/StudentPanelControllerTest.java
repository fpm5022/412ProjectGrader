package test;

import controller.StudentPanelController;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
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
public class StudentPanelControllerTest {
    
    public StudentPanelControllerTest() {
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
     * Test of selectAllBoxes method, of class StudentPanelController.
     */
    @Test
    public void testSelectAllBoxes() {
        List<JCheckBox> checkboxes = new ArrayList();
        checkboxes.add(new JCheckBox());
        checkboxes.add(new JCheckBox());
        StudentPanelController.selectAllBoxes(checkboxes);
        
        for (JCheckBox box : checkboxes) {
            assertTrue(box.isSelected());
        }
    }

    /**
     * Test of deselectAllBoxes method, of class StudentPanelController.
     */
    @Test
    public void testDeselectAllBoxes() {
        ArrayList<JCheckBox> checkboxes = new ArrayList();
        checkboxes.add(new JCheckBox());
        checkboxes.add(new JCheckBox());
        StudentPanelController.deselectAllBoxes(checkboxes);
        
        for (JCheckBox box : checkboxes) {
            assertFalse(box.isSelected());
        }
    }

    /**
     * Test of getSelectedStudents method, of class StudentPanelController.
     */
    @Test
    public void testGetSelectedStudents() {
        // noop
    }

    /**
     * Test of importStudents method, of class StudentPanelController.
     */
    @Test
    public void testImportStudents() {
        String studentFileLocationAbsolutePath = "/Users/Feek/repos/412ProjectGrader/util/students.txt";
        String delimiter = ", |\\n";
        ArrayList<Student> students = new ArrayList();
        StudentPanelController.importStudents(studentFileLocationAbsolutePath, delimiter, students);
        assertTrue(students.size() == 51);
    }

    /**
     * Test of studentLocationButtonClicked method, of class StudentPanelController.
     */
    @Test
    public void testStudentLocationButtonClicked() {
        // noop
    }

    /**
     * Test of setDefaults method, of class StudentPanelController.
     */
    @Test
    public void testSetDefaults() {
        // noop
    }
    
}
