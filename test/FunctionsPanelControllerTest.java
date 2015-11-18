/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import controller.FunctionsPanelController;
import java.util.ArrayList;
import model.FunctionsPanelModel;
import model.Student;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.FunctionsPanel;

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
        System.out.println("sourceDirectoryActionPerformed");
        FunctionsPanel panel = null;
        FunctionsPanelController.sourceDirectoryActionPerformed(panel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compilePathActionPerformed method, of class FunctionsPanelController.
     */
    @Test
    public void testCompilePathActionPerformed() {
        System.out.println("compilePathActionPerformed");
        FunctionsPanel panel = null;
        FunctionsPanelController.compilePathActionPerformed(panel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compileActionPerformed method, of class FunctionsPanelController.
     */
    @Test
    public void testCompileActionPerformed() {
        System.out.println("compileActionPerformed");
        FunctionsPanel panel = null;
        FunctionsPanelModel model = null;
        ArrayList<Student> selectedStudents = null;
        FunctionsPanelController.compileActionPerformed(panel, model, selectedStudents);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
