/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.Compiler;
import controller.TestRunnerController;
import java.io.File;
import java.util.Map;
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
        String path = "C:\\Users\\hites\\Documents\\GitHub\\412ProjectGrader\\src";
        String sourcePath = "C:\\Users\\hites\\Documents\\New Folder";
        String classPath = "C:\\Users\\hites\\Documents\\GitHub\\412ProjectGrader\\src";
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
        String expResult = pb.directory().getAbsolutePath();
        System.out.println(expResult);
        String result = model.classPath;
        assertEquals("process builder is not in the correct directory", expResult,result);
        Map env = pb.environment();
        
//        assertEquals("path was not set properly", env.get("PATH"), model.path);
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
