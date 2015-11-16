/**
@author Feek <feek@psu.edu>
**/

package model;

public class FunctionsPanelModel {
    public String compilePathDirectory; // directory to compile code into
    public String sourceCodeDirectory; // directory source code resides
    public String expectedTestOutput;
    public String mainClassName;
    public String commandLineArguments;

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
    }
}
