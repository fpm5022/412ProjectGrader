
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
@author Feek <feek@psu.edu>
**/


public class StudentPanel extends JPanel{
    private final Frame frame;
    private ArrayList<Student> students;
    
    public StudentPanel(Frame frame) {
        this.frame = frame;
        importStudents();
        initComponents();
    }

    private void initComponents() {
        setBackground(Color.red);
        this.setSize(frame.WIDTH / 3, frame.HEIGHT);
        this.setVisible(true);
    }

    private void importStudents() {
        
    }
}
