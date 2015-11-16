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
import model.Student;
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

    public static ArrayList<Student> importStudents(String studentFileLocationAbsolutePath, String delimiter) {
        ArrayList<Student> students = new ArrayList();
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

        return students;
    }

}
