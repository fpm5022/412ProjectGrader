/**
 * @author Feek <feek@psu.edu>
 *
 */
package controller;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.XMLObject;

/**
 * This class saves the xmlobject when the program exits via a hook added in frame
 */
public class XMLSaver implements Runnable {
    private XMLEncoder encoder;
    private final String location; // location to save this xml file
    private final XMLObject object;
    
    public XMLSaver(String location, XMLObject xmlObject) {
        this.object = xmlObject;
        this.location = location;
    }
    
    /**
     * Given the supplied key, save the value into the xml object
     * @param key
     * @param value 
     */
    public void addValueToWrite(String key, String value) {
        switch(key) {
            case "studentFileLocationAbsolutePath":
                this.object.studentFileLocationAbsolutePath = value;
                break;
            case "mainClassName":
                this.object.mainClassName = value;
                break;
            case "compilePathDirectory":
                this.object.compilePathDirectory = value;
                break;
            case "sourceCodeDirectory":
                this.object.sourceCodeDirectory = value;
                break;
            case "expectedOutput":
                this.object.expectedOutput = value;
                break;
        }
    }

    @Override
    public void run() {
        try {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(location + "/paths.xml")));
            encoder.writeObject(object);
            encoder.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLSaver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
