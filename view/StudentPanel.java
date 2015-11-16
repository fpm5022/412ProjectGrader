package view;


import controller.StudentPanelController;
import java.awt.Color;
import static java.awt.Component.LEFT_ALIGNMENT;


import model.Student;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.XMLObject;

/**
@author Feek <feek@psu.edu>
**/

public class StudentPanel extends JPanel{
    private final Frame frame;
    private ArrayList<Student> students;
    private final ArrayList<JCheckBox> checkboxes;
    private String studentFileLocationAbsolutePath;
    private final String delimiter = ", |\\n"; // delmiter seperating students in students.txt. , and new line
    private final int X = 10;
    private int y = 10;
    private final int Y_INCREMENT = 20; // space between boxes
    private final int WIDTH;
    private int HEIGHT; // adjusted when students are added
    private JScrollPane scrollPane;
    
    private JButton studentLocationButton;
    private JTextField studentFileLocationTextField;
    
    private JButton selectAll;
    private JButton deselectAll;
    
    public StudentPanel(Frame frame, XMLObject xmlObject) {
        this.frame = frame;
        this.students = new ArrayList<>();
        this.checkboxes = new ArrayList<>();
        this.WIDTH = frame.WIDTH / 3;
        this.HEIGHT = frame.HEIGHT;
        this.setLayout(null);
        this.setBackground(Color.pink);
        initComponents();
        
        if (xmlObject.studentFileLocationAbsolutePath != null) {
            this.studentFileLocationAbsolutePath = xmlObject.studentFileLocationAbsolutePath;
            this.studentFileLocationTextField.setText(xmlObject.studentFileLocationAbsolutePath);
            initCheckboxes();
            selectAll.setEnabled(true);
            deselectAll.setEnabled(true);
        }
    }

    private void initComponents() {
        this.setSize(WIDTH, HEIGHT);
        initStudentLocationComponents();
        initSelectButtons();
        this.setVisible(true);
    }
    
    private void initSelectButtons() {
        selectAll = new JButton("Select All");
        deselectAll = new JButton("Deselect All");
        
        selectAll.setBounds(X, y, 100, 30);
        deselectAll.setBounds(X + 110, y, 100, 30);
        
        selectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectAllClicked(e);
            }
        });
        deselectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deselectAllClicked(e);
            }
        });
        
        add(selectAll);
        add(deselectAll);
        
        // at this time, no students have been added
        selectAll.setEnabled(false);
        deselectAll.setEnabled(false);
        
        y += 40;
    }
    
    private void selectAllClicked(ActionEvent e) {
        StudentPanelController.selectAllBoxes(this.checkboxes);
    }
    
    private void deselectAllClicked(ActionEvent e) {
        StudentPanelController.deselectAllBoxes(this.checkboxes);
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
        
        studentFileLocationTextField = new JTextField();
        studentFileLocationTextField.setBounds(X + 165, y, 150, 30);
        studentFileLocationTextField.setEnabled(false);
        add(studentFileLocationTextField);
        
        y += 40;
    }
    
    // adds a file chooser accepting .text files and then calls init checkboxes
    private void studentLocationButtonClicked(ActionEvent e) {
        StudentFileChooser chooser = new StudentFileChooser();
        this.add(chooser);

        int val = chooser.showOpenDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
            this.studentFileLocationAbsolutePath = chooser.getSelectedFile().getAbsolutePath();
            this.studentFileLocationTextField.setText(this.studentFileLocationAbsolutePath);
            this.frame.xmlSaver.addValueToWrite("studentFileLocationAbsolutePath", this.studentFileLocationAbsolutePath);
            initCheckboxes();
            
            // enable the buttons now
            selectAll.setEnabled(true);
            deselectAll.setEnabled(true);
        }
    }

    private void importStudents() {
        this.students = StudentPanelController.importStudents(this.studentFileLocationAbsolutePath, this.delimiter);
    }
    
    // loads in the students from the file and displays them along with checkboxes
    private void initCheckboxes() {
        importStudents();
        
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
        this.revalidate();
    }
    
    public ArrayList<Student> getSelectedStudents() {
        return StudentPanelController.getSelectedStudents(this.checkboxes, this.students);
    }
    
}
