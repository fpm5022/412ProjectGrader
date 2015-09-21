
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
    
    public BatchGUI(Frame frame) {
        this.frame = frame;
        this.setSize(frame.WIDTH, frame.HEIGHT);
        initComponents();
        this.setLayout(null); // yolo  ¯\_(ツ)_/¯ 
        this.setVisible(true);
    }

    private void initComponents() {
        studentPanel = new StudentPanel(frame);
        scrollPane = new JScrollPane(studentPanel);
        scrollPane.setBounds(0, 0, frame.WIDTH / 3, frame.HEIGHT);
        add(scrollPane);

        functionsPanel = new FunctionsPanel(frame);
        functionsPanel.setBounds(frame.WIDTH / 3, 0, frame.WIDTH/3 + frame.WIDTH / 3, frame.HEIGHT);
        add(functionsPanel);
        
        /*
        HOW TO GET SELECTED STUDENTS
        ArrayList<Student> selected = studentPanel.getSelectedStudents();
        for (Student s : selected) {
            System.out.println(s.getInfo());
        }
               */  
            
    }
}
