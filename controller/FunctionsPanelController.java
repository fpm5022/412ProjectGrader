/**
 * @author Feek <feek@psu.edu>
 *
 */
package controller;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import model.FunctionsPanelModel;
import model.Student;
import model.TestRunnerModel;
import view.FunctionsPanel;
import view.SourceDirectoryFileChooser;

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

    public static void compileActionPerformed(FunctionsPanel panel, FunctionsPanelModel model, ArrayList<Student> selectedStudents) {
        // loop through selected students and start compiling
        for (Student s : selectedStudents) {
            String studentName = s.getName();
            String compilePath = model.compilePathDirectory + studentName; // directory to compile into
            String sourcePath = model.sourceCodeDirectory + File.separator + studentName + File.separator + model.mainClassName; // what to compile

            Compiler compiler = new Compiler(model.compilePathDirectory, compilePath, sourcePath);
            int success = compiler.compileJava();

            if (success != 0) {
                panel.appendToTextArea(s.getInfo() + " compile failed: " + success, true);
            } else {
                panel.appendToTextArea(s.getInfo() + " compile success", false);
                FunctionsPanelController.testCode(model, panel, studentName, compilePath);
            }
        }
        
        // save the settings...
        panel.frame.xmlSaver.addValueToWrite("mainClassName", model.mainClassName);
        panel.frame.xmlSaver.addValueToWrite("compilePathDirectory", model.compilePathDirectory);
        panel.frame.xmlSaver.addValueToWrite("sourceCodeDirectory", model.sourceCodeDirectory);
        panel.frame.xmlSaver.addValueToWrite("expectedOutput", model.expectedTestOutput);
    }

    private static void testCode(FunctionsPanelModel model, FunctionsPanel panel, String studentName, String compilePath) {
        // command line args should be a CSV. We need to parse that into an array.
        // this will split on zero or more whitespace, a literal comma, zero or more whitespace
        String[] splitCommandLineArgs = model.commandLineArguments.split("\\s*,\\s*");
        String[] scannerInput = {"1", "1"}; // to do

        // remove the .java from the class name
        String mainClassNameWithoutFileType = model.mainClassName.substring(0, model.mainClassName.length() - 5);

        TestRunnerModel testRunnerModel = new TestRunnerModel(compilePath, compilePath, mainClassNameWithoutFileType, splitCommandLineArgs, scannerInput, model.expectedTestOutput);

        double similarity = TestRunnerController.runAndTestJava(testRunnerModel);
        boolean failed = (similarity != 100);

        panel.appendToTextArea(studentName + " " + similarity + "% similar to expected output", failed);
    }

}
