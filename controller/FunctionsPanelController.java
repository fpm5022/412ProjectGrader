/**
 * @author Feek <feek@psu.edu>
 *
 */
package controller;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import model.CompilerModel;
import model.FunctionsPanelModel;
import model.Student;
import model.TestRunnerModel;
import view.FunctionsPanel;
import view.SourceDirectoryFileChooser;
import worker.CompileAndTestWorker;

public class FunctionsPanelController {

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
        // loop through selected students and start compiling
        for (Student s : selectedStudents) {
            String studentName = s.getName();
            String compilePath = FunctionsPanelController.getStudentCompilePath(model, s);
            
            String sourcePath = model.sourceCodeDirectory + File.separator + studentName + File.separator + model.mainClassName; // what to compile

            CompilerModel compilerModel = new CompilerModel(model.compilePathDirectory, compilePath, sourcePath);
            CompileAndTestWorker worker = new CompileAndTestWorker(panel, compilerModel, model, s);
            worker.execute();
        }
        
        // save the settings...
        panel.frame.xmlSaver.addValueToWrite("mainClassName", model.mainClassName);
        panel.frame.xmlSaver.addValueToWrite("compilePathDirectory", model.compilePathDirectory);
        panel.frame.xmlSaver.addValueToWrite("sourceCodeDirectory", model.sourceCodeDirectory);
        panel.frame.xmlSaver.addValueToWrite("expectedOutput", model.expectedTestOutput);
    }

    public static void testCode(FunctionsPanelModel model, FunctionsPanel panel, String studentName, String compilePath) {
        // command line args should be a CSV. We need to parse that into an array.
        // splits on commas
        String[] splitCommandLineArgs = model.commandLineArguments.split("\\s*,\\s*");
        String[] scannerInput = {"1", "1"}; // to do

        // remove the .java from the class name
        String mainClassNameWithoutFileType = model.mainClassName.substring(0, model.mainClassName.length() - 5);

        TestRunnerModel testRunnerModel = new TestRunnerModel(compilePath, compilePath, mainClassNameWithoutFileType, splitCommandLineArgs, scannerInput, model.expectedTestOutput);

        double similarity = TestRunnerController.runAndTestJava(testRunnerModel);
        boolean failed = (similarity != 100);

        panel.appendToTextArea(studentName + " " + similarity + "% similar to expected output", failed);
    }

    // directory to compile into
    public static String getStudentCompilePath(FunctionsPanelModel model, Student student) {
        return model.compilePathDirectory + student.getName();
    }

}