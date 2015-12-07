/**
@author Feek <feek@psu.edu>
**/

package model;

/**
 * This struct holds all data necessary for the functions panel to operate correctly.
 */
public class FunctionsPanelModel {
    public String compilePathDirectory; // directory to compile code into
    public String sourceCodeDirectory; // directory source code resides
    public String expectedTestOutput;
    public String mainClassName;
    public String commandLineArguments;
    public String scannerInput;

    /**
     * Reads from the loading xmlobject and sets field values depending on if the field exists
     * in the xmlobject or not.
     */
    public void setDefaults(XMLObject xmlObject) {
        if (xmlObject.sourceCodeDirectory != null) {
            sourceCodeDirectory = xmlObject.sourceCodeDirectory;
        } else {
            sourceCodeDirectory = "Location of parent directory holding all students source codes";
        }
        
        if (xmlObject.compilePathDirectory != null) {
            compilePathDirectory = xmlObject.compilePathDirectory;
        } else {
            compilePathDirectory = "Directory to compile into";
        }
        
        if (xmlObject.expectedOutput != null) {
            expectedTestOutput = xmlObject.expectedOutput;
        } else {
            expectedTestOutput = "Expected Output";
        }
        
        if (xmlObject.mainClassName != null) {
            mainClassName = xmlObject.mainClassName;
        } else {
            mainClassName = "Name of java class to compile (include .java)";
        }
        
        if (xmlObject.commandLineArguments != null) {
            commandLineArguments = xmlObject.commandLineArguments;
        } else {
            commandLineArguments = "CSV Command Line Arguments";
        }
        
        if (xmlObject.scannerInput != null) {
            scannerInput = xmlObject.scannerInput;
        } else {
            scannerInput = "CSV Scanner Input";
        }
    }
}
