/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import controller.XMLReader;
import model.XMLObject;
import model.TestRunnerModel;
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
public class XMLReaderTest {
    private XMLReader decoder;
    private XMLObject object;
    
    @BeforeClass
    public void setUpClass() throws Exception
    {
        String location = "C:\\Users\\Laptop Boiz\\Documents\\New Folder\\compile";
        this.decoder = new XMLReader(location);
        this.object = null;
        this.object = new XMLObject();
    }
    
    @Test
    public void testNotNullObject() throws Exception 
    {
        object = this.decoder.getObject();
        assertNull("XMLObject exists", object);
    }
    
    /**
     * Test of getObject method, of class XMLReader.
     */
    @Test
    public void testGetObject() {
        decoder.getObject();
        assertNotNull("XMLObject does not exist", object);
    }
    
}
