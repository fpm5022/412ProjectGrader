package test;

import controller.XMLSaver;
import java.beans.XMLEncoder;
import model.XMLObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class XMLSaverTest {
    private XMLEncoder encoder;
    private XMLSaver saver;
    private XMLObject object;
    
    @Before
    public void setUp() {
        String location = "/Users/Feek/ProjectGrader/";
        this.object = new XMLObject();
        this.saver = new XMLSaver(location, object);
    }

    @Test
    public void testAddValueToWrite() {
        saver.addValueToWrite("mainClassName", "ArrayLoops");
        assertEquals("mainClassName was not equal to ArrayLoops", "ArrayLoops", object.mainClassName);
    }
    
}
