package view;

import java.awt.Color;
import java.awt.Panel;

public class TextPanel extends Panel {
    
    public TextPanel(){
        this.setVisible(true);
        this.setBackground(Color.WHITE);
        this.setFont(new java.awt.Font("Tahoma", 0, 14));
        this.setBounds(10, 365, 450, 300);
    }
    
}
