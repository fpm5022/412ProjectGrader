
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
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
@author Feek <feek@psu.edu>
**/

public class StudentPanel extends JPanel{
    private final Frame frame;
    private final ArrayList<Student> students;
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
    
    public StudentPanel(Frame frame) {
        this.frame = frame;
        this.students = new ArrayList<>();
        this.checkboxes = new ArrayList<>();
        this.WIDTH = frame.WIDTH / 3;
        this.HEIGHT = frame.HEIGHT;
        this.setLayout(null);
        initComponents();
    }

    private void initComponents() {
        this.setSize(WIDTH, HEIGHT);
        initStudentLocationComponents();
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
        
        studentFileLocationTextField = new JTextField();
        studentFileLocationTextField.setBounds(X + 165, y, 150, 30);
        studentFileLocationTextField.setEnabled(false);
        add(studentFileLocationTextField);
        
        y += 40;
    }
    
    // adds a file chooser accepting .text files and then calls init checkboxes
    private void studentLocationButtonClicked(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        // only allow text file to be selected
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Please select the text file containing students");
        this.add(chooser);

        int val = chooser.showOpenDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
            this.studentFileLocationAbsolutePath = chooser.getSelectedFile().getAbsolutePath();
            this.studentFileLocationTextField.setText(this.studentFileLocationAbsolutePath);
            initCheckboxes();
        }
    }

    private void importStudents() {
        try {
            File file = new File(this.studentFileLocationAbsolutePath);
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
