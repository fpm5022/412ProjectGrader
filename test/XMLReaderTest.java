package test;

import controller.XMLReader;
import model.XMLObject;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Feek
 */
public class XMLReaderTest {
    private XMLReader decoder;
    
    @Before
    public void setUp() throws Exception
    {
        String location = "/Users/Feek/ProjectGrader/paths.xml";
        this.decoder = new XMLReader(location);
    }
    
    /**
     * Test of getObject method, of class XMLReader.
     */
    @Test
    public void testGetObject() {
        XMLObject object = decoder.getObject();
        assertNotNull("XMLObject does not exist", object);
    }
    
}
