/**
@author Feek <feek@psu.edu>
**/

package controller;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class BatchGUIController {
    private static Font font;
    private static Border border;
    
    // returns a button with a border
    public static JButton generateButton(String text) {
        return generateButton(text, true);
    }
    
    public static JButton generateButton(String text, boolean border) {
        JButton button = new JButton(text);
        button.setFont(getFont());
        
        if (border) {
            button.setBorder(getBorder());
        }
        
        return button;
    }
    
    private static Font getFont() {
        if (font == null) {
            font = new Font("Century Schoolbook", Font.PLAIN, 14);
        }
        
        return font;
    }
    
    private static Border getBorder() {
        if (border == null) {
            border = new LineBorder(Color.decode("#4B0082"), 3);
        }
        
        return border;
    }
    
    public static JLabel generateLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(getFont());
        return label;
    }
}
