/**
@author Feek <feek@psu.edu>
**/

package controller;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import model.XMLObject;

/**
 * This class reads in from the saved xml file and loads everything into the xmlobject
 */
public class XMLReader {
    private XMLDecoder decoder;
    private XMLObject object;
    
    public XMLReader(String location) {
        try {
            this.decoder = new XMLDecoder(new FileInputStream(location));
            this.object = (XMLObject) decoder.readObject();
            
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
            // noop, we don't want to do anything if the file isn't found. it'll be made the next time
            // the program exits
        }
    }
    
    public XMLObject getObject() {
        if (this.object == null) {
            this.object = new XMLObject();
        }
        
        return this.object;
    }
}
