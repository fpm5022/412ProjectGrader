
import java.awt.Color;
import javax.swing.JPanel;

/**
@author Feek <feek@psu.edu>
**/


public class StudentPanel extends JPanel{
    private Frame frame;
    
    public StudentPanel(Frame frame) {
        this.frame = frame;
        initComponents();
    }

    private void initComponents() {
        setBackground(Color.red);
        this.setSize(frame.WIDTH / 3, frame.HEIGHT);
        this.setVisible(true);
    }
}
