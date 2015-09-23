
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
@author Feek <feek@psu.edu>
**/


public class StudentPanel extends JPanel{
    private final Frame frame;
    private final ArrayList<Student> students;
    private final ArrayList<JCheckBox> checkboxes;
    private final String studentFileLocation = "/Users/Feek/repos/412ProjectGrader/"; // TO DO: make this a setting
    private final String studentFileName = "students.txt"; // to do: pull out like ^^^
    private final String delimiter = ", |\\n"; // delmiter seperating students in students.txt. , and new line
    private final int X = 10;
    private int y = 10;
    private final int Y_INCREMENT = 20; // space between boxes
    private final int WIDTH;
    private int HEIGHT; // adjusted when students are added
    private JScrollPane scrollPane;
    
    private JButton studentLocationButton;
    
    public StudentPanel(Frame frame) {
        this.frame = frame;
        this.students = new ArrayList<>();
        this.checkboxes = new ArrayList<>();
        this.WIDTH = frame.WIDTH / 3;
        this.HEIGHT = frame.HEIGHT;
        this.setLayout(null);
        importStudents();
        initComponents();
    }

    private void initComponents() {
        this.setSize(WIDTH, HEIGHT);
        initStudentLocationComponents();
        initCheckboxes();
        this.setVisible(true);
    }
    
    private void initStudentLocationComponents() {
        studentLocationButton = new JButton("Student File Location");
        studentLocationButton.setBounds(X, y, 150, 30);
        studentLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentLocationButtonClicked(e);
            }
        });
        add(studentLocationButton);
        y += 40;
    }
    
    private void studentLocationButtonClicked(ActionEvent e) {
    }

    private void importStudents() {
        try {
            File file = new File(studentFileLocation + studentFileName);
            Scanner read = new Scanner (file);
            read.useDelimiter(delimiter);
            
            while(read.hasNext()) {
                String name = read.next();
                String handle = read.next();
                
                students.add(new Student(name, handle));
            }
            
            read.close();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initCheckboxes() {
        for(Student s : students) {
            JCheckBox box = new JCheckBox(s.getInfo());
            box.setAlignmentY(LEFT_ALIGNMENT);
            box.setBounds(X, y, 200, 15);
            this.y += Y_INCREMENT;
            this.add(box);
            checkboxes.add(box);
        }
        this.HEIGHT = y; // update height of panel so scrolling can happen
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    
    public ArrayList<Student> getSelectedStudents() {
        // less than optimal way, but it works for now
        ArrayList<Student> selected = new ArrayList<>();
        for (int i = 0; i < checkboxes.size(); i++) {
            if (checkboxes.get(i).isSelected()) {
                selected.add(students.get(i));
            }
        }
        
        return selected;
    }
}
