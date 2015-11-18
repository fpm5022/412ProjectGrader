/**
@author Feek <feek@psu.edu>
**/

package model;

import java.util.ArrayList;

public class StudentPanelModel {
    public ArrayList<Student> students;
    public String studentFileLocationAbsolutePath;
    public final String delimiter = ", |\\n"; // delmiter seperating students in students.txt. , and new line
    
    public StudentPanelModel() {
        this.students = new ArrayList();
    }
}
