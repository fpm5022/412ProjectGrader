
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
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
        
        // TEMP FOR NOW. This prints all the students that are selected to compile when the button is pressed
        JButton button = new JButton("yo");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Student> selected = studentPanel.getSelectedStudents();
                for (Student s : selected) {
                    System.out.println(s.getInfo());
                }
                
            }
        });
        
        button.setBounds(400, 100, 200, 200);
        this.add(button);
    }
}