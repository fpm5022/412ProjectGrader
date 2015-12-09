/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import controller.BatchGUIController;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.Frame;

/**
 *
 * @author Feek <feek@psu.edu>
 */
public class BatchGUIControllerTest {
    
    public BatchGUIControllerTest() {
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
     * Test of generateButton method, of class BatchGUIController.
     */
    @Test
    public void testGenerateButton() {
        JButton result = BatchGUIController.generateButton("Button Text", true);
        assertEquals("Button Text", result.getText());
        assertNotNull(result.getBorder());
        
        result = BatchGUIController.generateButton("Button Text", false);
        assertNull(result.getBorder());
    }

    /**
     * Test of generateLabel method, of class BatchGUIController.
     */
    @Test
    public void testGenerateLabel() {
        JLabel result = BatchGUIController.generateLabel("Test Label");
        assertEquals("Test Label", result.getText());
    }

    /**
     * Test of swap method, of class BatchGUIController.
     */
    @Test
    public void testSwap() {
        Frame frame = new Frame();
        JPanel jp1 = new JPanel();
        frame.add(jp1);
        JPanel jp2 = new JPanel();
        
        BatchGUIController.swap(frame, jp1, jp2); // add jp2 and remove jp1
        
        assertEquals(SwingUtilities.getWindowAncestor(jp2), frame);
        assertNull(SwingUtilities.getWindowAncestor(jp1));
    }

    /**
     * Test of setLayoutFeel method, of class BatchGUIController.
     */
    @Test
    public void testSetLayoutFeel() {
        BatchGUIController.setLayoutFeel("Nimbus");
        assertEquals("Nimbus", UIManager.getLookAndFeel().getName());
    }
    
}
