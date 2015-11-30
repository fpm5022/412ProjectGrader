/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import model.Student;
import model.StudentPanelModel;
import model.XMLObject;
import view.StudentFileChooser;
import view.StudentPanel;

/**
 *
 * @author fpm5022
 */
public class StudentPanelController {

    public static void selectAllBoxes(List<JCheckBox> checkboxes) {
        // not optimal, but will do
        for (JCheckBox box : checkboxes) {
            box.setSelected(true);
        }
    }

    public static void deselectAllBoxes(ArrayList<JCheckBox> checkboxes) {
        // not optimal, but will do
        for (JCheckBox box : checkboxes) {
            box.setSelected(false);
        }
    }

    public static ArrayList<Student> getSelectedStudents(ArrayList<JCheckBox> checkboxes, ArrayList<Student> students) {
        // less than optimal way, but it works for now
        ArrayList<Student> selected = new ArrayList<>();
        for (int i = 0; i < checkboxes.size(); i++) {
            if (checkboxes.get(i).isSelected()) {
                selected.add(students.get(i));
            }
        }
        return selected;
    }

    public static void importStudents(String studentFileLocationAbsolutePath, String delimiter, ArrayList<Student> students) {
        try {
            File file = new File(studentFileLocationAbsolutePath);
            Scanner read = new Scanner(file);
            read.useDelimiter(delimiter);

            while (read.hasNext()) {
                String name = read.next();
                String handle = read.next();

                students.add(new Student(name, handle));
            }

            read.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // adds a file chooser accepting .text files and then calls init checkboxes
    public static void studentLocationButtonClicked(StudentPanel studentPanel, StudentPanelModel model) {
        StudentFileChooser chooser = new StudentFileChooser();
        studentPanel.add(chooser);

        int val = chooser.showOpenDialog(studentPanel);
        if (val == JFileChooser.APPROVE_OPTION) {
            model.studentFileLocationAbsolutePath = chooser.getSelectedFile().getAbsolutePath();
            studentPanel.studentFileLocationTextField.setText(model.studentFileLocationAbsolutePath);
            studentPanel.frame.xmlSaver.addValueToWrite("studentFileLocationAbsolutePath", model.studentFileLocationAbsolutePath);
            studentPanel.initCheckboxes();
            studentPanel.enableSelectButtons();
        }
    }
    
    /**
     * 
     * @param xmlObject
     * @param model
     * @return true if model was updated
     */
    public static boolean setDefaults(XMLObject xmlObject, StudentPanelModel model) {
        if (xmlObject.studentFileLocationAbsolutePath != null) {
            model.studentFileLocationAbsolutePath = xmlObject.studentFileLocationAbsolutePath;
            return true;
        }
        return false;
    }

    /**
     * takes care of cleaning up the students and their labels etc. Used in case
     * different student file is selected after already selecting one
     * @param panel
     * @param model 
     */
    public static void clearStudents(StudentPanel panel, StudentPanelModel model) {
        for(JCheckBox box : panel.checkboxes) {
            panel.remove(box);
        }
        panel.HEIGHT = panel.INITIAL_HEIGHT;
        panel.Y = panel.INITIAL_Y + panel.BUTTON_PADDING;
        panel.checkboxes.clear();
        model.students.clear();
        panel.revalidate();
        panel.repaint();
    }
}
