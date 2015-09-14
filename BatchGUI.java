
import javax.swing.JPanel;

/**
@author Feek <feek@psu.edu>
**/


public class BatchGUI extends JPanel {
    private final Frame frame;
    
    public BatchGUI(Frame frame) {
        this.frame = frame;
        this.setSize(frame.WIDTH, frame.HEIGHT);
        initComponents();
        this.setLayout(null); // yolo  ¯\_(ツ)_/¯ 
        this.setVisible(true);
    }

    private void initComponents() {
        
    }
}
