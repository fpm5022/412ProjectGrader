package view;


import model.Student;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
@author Feek <feek@psu.edu>
**/


public class BatchGUI extends JPanel {
    private final Frame frame;
    private StudentPanel studentPanel;
    private JScrollPane scrollPane;
    private FunctionsPanel functionsPanel;
    
    private int scrollPaneWidth;
    
    public BatchGUI(Frame frame) {
        this.frame = frame;
        this.scrollPaneWidth = frame.WIDTH / 3;
        this.setSize(frame.WIDTH, frame.HEIGHT);
        initComponents();
        this.setLayout(null); // yolo  ¯\_(ツ)_/¯ 
        this.setVisible(true);
    }

    private void initComponents() {
        studentPanel = new StudentPanel(frame);
        scrollPane = new JScrollPane(studentPanel);
        scrollPane.setBounds(0, 0, scrollPaneWidth, frame.HEIGHT);
        add(scrollPane);

        functionsPanel = new FunctionsPanel(frame);
        functionsPanel.setBounds(scrollPaneWidth, 0, scrollPaneWidth * 2, frame.HEIGHT);
        add(functionsPanel); 
    }
    
    /*
    returns info regarding which students have been selected in the student panel
    */
    public ArrayList<Student> getSelectedStudents() {
        ArrayList<Student> selected = studentPanel.getSelectedStudents();
        return selected;
    }
}
