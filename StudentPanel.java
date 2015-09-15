
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
@author Feek <feek@psu.edu>
**/


public class StudentPanel extends JPanel{
    private final Frame frame;
    private ArrayList<Student> students;
    private final String studentFileLocation = "/Users/Feek/repos/412ProjectGrader/"; // TO DO: make this a setting
    private final String studentFileName = "students.txt"; // to do: pull out like ^^^
    private final String delimiter = ", |\\n"; // delmiter seperating students in students.txt. , and new line
    
    public StudentPanel(Frame frame) {
        this.frame = frame;
        this.students = new ArrayList<>();
        importStudents();
        initComponents();
    }

    private void initComponents() {
        setBackground(Color.red);
        this.setSize(frame.WIDTH / 3, frame.HEIGHT);
        this.setVisible(true);
    }

    private void importStudents() {
        try {
            File file = new File(studentFileLocation + studentFileName);
            Scanner read = new Scanner (file);
            read.useDelimiter(delimiter);
            
            while(read.hasNext()) {
                String name = read.next();
                System.out.println(name);
                String handle = read.next();
                System.out.println(handle);
                
                students.add(new Student(name, handle));
            }
            
            read.close();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
