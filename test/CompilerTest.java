/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.Compiler;
import controller.TestRunnerController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.assertEquals;
import model.CompilerModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hites
 */
public class CompilerTest {

    public CompilerModel model;
    public ProcessBuilder pb;

    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        Properties prop = new Properties();
        InputStream input;
        try {
            input = new FileInputStream("test.properties");
               prop.load(input);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CompilerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CompilerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        String path = prop.getProperty("path");
        String sourcePath = prop.getProperty("sourcepath");
        String classPath = prop.getProperty("classPath");
        this.model = new CompilerModel(path, classPath, sourcePath);
        this.pb = new ProcessBuilder();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of compileJava method, of class Compiler.
     */
    @Test
    public void testCompileJava() {
        System.out.println("compileJava");
        int expResult = 0;
        int result = Compiler.compileJava(model);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUpEnv method, of class Compiler.
     */
    @Test
    public void testSetUpEnv() {
        Compiler.setUpEnv(model, pb);
        Map env = pb.environment();
        
        assertEquals("path was not set properly", env.get("PATH"), model.path);
        assertEquals("classpath was not set properly", env.get("CLASSPATH"), model.classPath);
    }
    
    @Test
    public void testFileSetUp(){
        System.out.println("fileSetUp");
        File file = Compiler.fileSetUp(model, pb);
        assertNotNull(file);
        String expResult = model.classPath + "/" + model.outputFileName;
        String result = file.getPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
