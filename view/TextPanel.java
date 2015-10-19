package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class TextPanel extends Panel {
    
    public TextPanel(){
        this.setVisible(true);
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setFont(new java.awt.Font("Tahoma", 0, 14));
        this.setBounds(10, 365, 450, 300);
    }
    
//    public void addLabels(){
//        textPanel.setBounds(5, 5 ,450, y);
//        this.y += Y_INCREMENT;
//        HEIGHT = y; // update height of panel so scrolling can happen
//        textPanel.setSize(new Dimension(5, HEIGHT));
//        textPanel.setPreferredSize(new Dimension(5, HEIGHT));
//
//                
//        JLabel text = new JLabel(message + "\n");
//        text.setLocation(0, y);
//        
//        if (error) {
//            text.setForeground(Color.red);
//        } else {
//            text.setForeground(Color.black);
//        }
//        textPanel.add(text);
//        textPanel.repaint();
//        
//    }
    
}
