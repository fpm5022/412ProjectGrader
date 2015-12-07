/**
 * @author Feek <feek@psu.edu>
 *
 */
package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFileChooser;
import javax.swing.SwingWorker;
import model.CompilerModel;
import model.FunctionsPanelModel;
import model.Student;
import model.TestRunnerModel;
import util.TestTools;
import view.FunctionsPanel;
import view.SourceDirectoryFileChooser;
import view.TextPanel;
import worker.CompileAndTestWorker;

public class FunctionsPanelController {
    
    private static ExecutorService service;

    /**
     * Shows a file chooser and responds to the choosers result
     *
     * @param panel
     */
    public static void sourceDirectoryActionPerformed(FunctionsPanel panel) {
        JFileChooser chooser = new SourceDirectoryFileChooser();
        panel.add(chooser);

        int val = chooser.showOpenDialog(panel);
        if (val == JFileChooser.APPROVE_OPTION) {
            panel.model.sourceCodeDirectory = chooser.getSelectedFile().getAbsolutePath();
            panel.sourceDirectoryTextField.setText(panel.model.sourceCodeDirectory);
        }
    }

    /**
     * shows a file chooser and responds accordingly
     *
     * @param panel
     */
    public static void compilePathActionPerformed(FunctionsPanel panel) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setDialogTitle("Please select the class path");
        panel.add(chooser);

        int val = chooser.showOpenDialog(panel);
        if (val == JFileChooser.APPROVE_OPTION) {
            panel.model.compilePathDirectory = chooser.getSelectedFile().getAbsolutePath() + File.separator; // append trailing slash
            panel.compilePathTextField.setText(panel.model.compilePathDirectory);
        }
    }

    /**
     * boots up a new swing worker to compile the students code. if the code compiles, it is then
     * tested. Also saves the settings
     * @param panel
     * @param model
     * @param selectedStudents 
     */
    public static void compileActionPerformed(FunctionsPanel panel, FunctionsPanelModel model, ArrayList<Student> selectedStudents) {
        // clean directory 
        TestTools.cleanDir(new File(model.compilePathDirectory));
        
        // loop through selected students and start compiling
        for (Student s : selectedStudents) {
            String studentName = s.getName();
            String compilePath = FunctionsPanelController.getStudentCompilePath(model, s);
            
            String sourcePath = model.sourceCodeDirectory + File.separator + studentName + File.separator + model.mainClassName; // what to compile

            CompilerModel compilerModel = new CompilerModel(compilePath, sourcePath);
            CompileAndTestWorker worker = new CompileAndTestWorker(panel, compilerModel, model, s);
            addWorker(worker);
        }
        
        // save the settings used in this run
        FunctionsPanelController.saveSettings(panel.frame.xmlSaver, model);
    }

    public static void testCode(FunctionsPanelModel model, FunctionsPanel panel, Student student, String compilePath) {
        // command line args should be a CSV. We need to parse that into an array.
        // splits on commas
        String[] splitCommandLineArgs = model.commandLineArguments.split("\\s*,\\s*");
        String[] scannerInput = model.scannerInput.split("\\s*,\\s*");

        // remove the .java from the class name
        String mainClassNameWithoutFileType = model.mainClassName.substring(0, model.mainClassName.length() - 5);

        TestRunnerModel testRunnerModel = new TestRunnerModel(compilePath, mainClassNameWithoutFileType, splitCommandLineArgs, scannerInput, model.expectedTestOutput);

        TestRunnerController.runAndTestJava(testRunnerModel);
        boolean failed = (testRunnerModel.similarity != 100);
        
        String message = student.getName() + " " + testRunnerModel.similarity + "% similar to expected output";
        
        if (failed) {
            panel.appendToTextArea(message, failed, student.getInfo() + " actual output: " + testRunnerModel.actualOutput);
        } else {
            panel.appendToTextArea(message);
        }
    }

    // directory to compile into
    public static String getStudentCompilePath(FunctionsPanelModel model, Student student) {
        return model.compilePathDirectory + student.getName();
    }

    /**
     * wrapper to assign settings to be saved when the program closes
     * @param xmlSaver
     * @param model 
     */
    private static void saveSettings(XMLSaver xmlSaver, FunctionsPanelModel model) {
        xmlSaver.addValueToWrite("mainClassName", model.mainClassName);
        xmlSaver.addValueToWrite("compilePathDirectory", model.compilePathDirectory);
        xmlSaver.addValueToWrite("sourceCodeDirectory", model.sourceCodeDirectory);
        xmlSaver.addValueToWrite("expectedOutput", model.expectedTestOutput);
        xmlSaver.addValueToWrite("commandLineArguments", model.commandLineArguments);
        xmlSaver.addValueToWrite("scannerInput", model.scannerInput);
    }

    public static void clearOutputArea(TextPanel panel) {
        panel.removeAll();
        panel.resetHeight();
    }
    
    private static void addWorker(SwingWorker worker) {
        if (service == null) {
            service = Executors.newFixedThreadPool(10); // create a 10 thread threadpool
        }
        service.submit(worker);
    }

}