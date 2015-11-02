/**
@author Feek <feek@psu.edu>
**/

package controller;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.XMLObject;

public class XMLReader {
    private XMLDecoder decoder;
    private XMLObject object;
    
    public XMLReader(String location) {
        try {
            this.decoder = new XMLDecoder(new FileInputStream(location));
            this.object = (XMLObject) decoder.readObject();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public XMLObject getObject() {
        return this.object;
    }
}
