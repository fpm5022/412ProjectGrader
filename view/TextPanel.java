package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TextPanel extends JPanel {
    private final int X = 10;
    private int Y = 10; 
    private final int Y_INCREMENT = 25; // space between boxes
    private int WIDTH;
    private int HEIGHT; // adjusted when students are added
    private int INITIAL_HEIGHT; // used when cleared
    private int INITIAL_Y;
    
    public TextPanel() {
        initializePanelSettings();
    }
    
    /**
     * initializes panel settings like width and font etc.
     */
    private void initializePanelSettings() {
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setLayout(null);
        this.setFont(new java.awt.Font("Century Schoolbook", 0, 14));
        WIDTH = 450;
        HEIGHT = 300;
        this.INITIAL_HEIGHT = HEIGHT;
        this.INITIAL_Y = Y;
    }
    
    /**
     * appends a label to the text area, if it's an error it will show up red
     * 
     * @param l
     * @param error 
     */
    public void addLabel(JLabel l, boolean error){
        l.setBounds(X, Y, WIDTH, 15);
        this.add(l);
        this.repaint();
        Y += Y_INCREMENT;
        
        if (error) {
            l.setForeground(Color.red);
        } else {
            l.setForeground(Color.black);
        }
        
        this.HEIGHT = Y; // update height of panel so scrolling can happen
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.revalidate();
    }
    
    public void resetHeight() {
        this.HEIGHT = this.INITIAL_HEIGHT;
        this.Y = this.INITIAL_Y;
        this.revalidate();
        this.repaint();
    }
    
}
