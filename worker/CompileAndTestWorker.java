/**
@author Feek <feek@psu.edu>
**/

package worker;

import controller.FunctionsPanelController;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import model.CompilerModel;
import model.FunctionsPanelModel;
import model.Student;
import view.FunctionsPanel;

public class CompileAndTestWorker extends SwingWorker {
    private final FunctionsPanel panel;
    private final Student student;
    private final CompilerModel compilerModel;
    private final FunctionsPanelModel panelModel;
    
    public CompileAndTestWorker(FunctionsPanel panel, CompilerModel compilerModel, FunctionsPanelModel panelModel, Student student) {
        this.panel = panel;
        this.student = student;
        this.compilerModel = compilerModel;
        this.panelModel = panelModel;
    }

    @Override
    protected Object doInBackground() throws Exception {
        int success = controller.Compiler.compileJava(compilerModel);
        return success;
    }
    
    @Override
    protected void done() {
        try {
            int success = (int) get();
            if (success != 0) {
                String errorMessage = student.getInfo() + " could not be compiled. output: ";
                ArrayList<String> lines = null;
                
                try {
                    lines = (ArrayList<String>) Files.readAllLines(compilerModel.outputFile.toPath(), StandardCharsets.UTF_8);
                } catch (IOException ex) {
                    Logger.getLogger(CompileAndTestWorker.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                for (String line : lines) {
                    errorMessage += line;
                }
                
                panel.appendToTextArea(student.getInfo() + " compile failed.", true, errorMessage);
            } else {
                panel.appendToTextArea(student.getInfo() + " compile success");
                FunctionsPanelController.testCode(panelModel, panel, student, FunctionsPanelController.getStudentCompilePath(panelModel, student));
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(CompileAndTestWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
