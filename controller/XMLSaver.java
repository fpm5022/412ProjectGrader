/**
 * @author Feek <feek@psu.edu>
 *
 */
package controller;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.XMLObject;

public class XMLSaver implements Runnable {
    private XMLEncoder encoder;
    private final String location; // location to save this xml file
    private XMLObject object;
    
    public XMLSaver() {
        this.object = new XMLObject();
        this.location = "/Users/Feek/Desktop"; // temp. Get from runtime / classpath
    }
    
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
