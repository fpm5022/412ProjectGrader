/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import controller.StudentPanelController;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
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
        System.out.println("deselectAllBoxes");
        ArrayList<JCheckBox> checkboxes = null;
        StudentPanelController.deselectAllBoxes(checkboxes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelectedStudents method, of class StudentPanelController.
     */
    @Test
    public void testGetSelectedStudents() {
        System.out.println("getSelectedStudents");
        ArrayList<JCheckBox> checkboxes = null;
        ArrayList<Student> students = null;
        ArrayList<Student> expResult = null;
        ArrayList<Student> result = StudentPanelController.getSelectedStudents(checkboxes, students);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of importStudents method, of class StudentPanelController.
     */
    @Test
    public void testImportStudents() {
        System.out.println("importStudents");
        String studentFileLocationAbsolutePath = "";
        String delimiter = "";
        ArrayList<Student> students = null;
        StudentPanelController.importStudents(studentFileLocationAbsolutePath, delimiter, students);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of studentLocationButtonClicked method, of class StudentPanelController.
     */
    @Test
    public void testStudentLocationButtonClicked() {
        System.out.println("studentLocationButtonClicked");
        StudentPanel studentPanel = null;
        StudentPanelModel model = null;
        StudentPanelController.studentLocationButtonClicked(studentPanel, model);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDefaults method, of class StudentPanelController.
     */
    @Test
    public void testSetDefaults() {
        System.out.println("setDefaults");
        XMLObject xmlObject = null;
        StudentPanelModel model = null;
        boolean expResult = false;
        boolean result = StudentPanelController.setDefaults(xmlObject, model);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
