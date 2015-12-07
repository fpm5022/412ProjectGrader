/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import controller.XMLSaver;
import java.beans.XMLEncoder;
import model.XMLObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class XMLSaverTest {
    private XMLEncoder encoder;
    private XMLSaver saver;
    private XMLObject object;
    private String key = "mainClassName";
    @BeforeClass
    public void setUpClass() {
        String location = "C:\\Users\\Laptop Boiz\\Documents\\New Folder\\compile";
        this.object = new XMLObject();
        this.saver = new XMLSaver(location, object);
    }

    @Test
    public void testAddValueToWrite() {
        saver.addValueToWrite(key, "ArrayLoops");
        assertEquals("mainClassName was not equal to ArrayLoops", "ArrayLoops", object.mainClassName);
    }

    /**
     * Test of run method, of class XMLSaver.
     */
    @Test
    public void testRun() {
        saver.run();
    }
    
}
