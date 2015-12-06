/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.Compiler;
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
            String url = "/Users/Feek/repos/412ProjectGrader/test/test.properties";
            input = new FileInputStream(url);
               prop.load(input);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CompilerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CompilerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        String classPath = prop.getProperty("classPath");
        String sourcePath = prop.getProperty("sourcePath");
        this.pb = new ProcessBuilder();
        
        this.model = new CompilerModel(classPath, sourcePath);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of compileJava method, of class Compiler.
     */
    @Test
    public void testCompileJava() {
        int expResult = 0;
        int result = Compiler.compileJava(model);
        assertEquals(expResult, result);
    }

    /**
     * Test of setUpEnv method, of class Compiler.
     */
    @Test
    public void testSetUpEnv() {
        Compiler.setUpEnv(model, pb);
        Map env = pb.environment();
        
        assertEquals("classpath was not set properly", env.get("CLASSPATH"), model.classPath);
    }
    
    @Test
    public void testFileSetUp(){
        File file = Compiler.fileSetUp(model, pb);
        assertNotNull(file);
        String expResult = model.classPath + model.outputFileName;
        String result = file.getPath();
        assertEquals(expResult, result);
    }
}
