package model;

import java.io.File;

/**
 *
 * @author hites
 */

/**
 * This struct holds all data necessary to compile a students code
 */
public class CompilerModel {
    public String classPath;
    public String sourcePath;
    public final String outputFileName = "output.txt";
    public int success;  // Outcome of compilation, success = 0
    public File outputFile;
    
    public CompilerModel(String classPath, String sourcePath) {
        this.classPath = classPath;
        this.sourcePath = sourcePath;
        this.success = 1;
    }
}
