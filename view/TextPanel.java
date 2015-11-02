package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TextPanel extends JPanel {
    private final int X = 10;
    private int Y = 10;
    private final int Y_INCREMENT = 20; // space between boxes
    private final int WIDTH;
    private int HEIGHT; // adjusted when students are added
    
    public TextPanel(){
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setLayout(null);
        this.setFont(new java.awt.Font("Tahoma", 0, 14));
        WIDTH = 450;
        HEIGHT = 300;
        //this.setBounds(10, 365, WIDTH, HEIGHT);
    }
    
    public void addLabel(JLabel l,boolean error){
        l.setBounds(X, Y, WIDTH, 10);
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
    
}
