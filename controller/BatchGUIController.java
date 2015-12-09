/**
@author Feek <feek@psu.edu>
**/

package controller;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import view.Frame;

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
        } else {
            button.setBorder(null);
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
    
    /**
     * Swaps out what panel is visible on the frame
     * @param frame
     * @param remove
     * @param add 
     */
    public static void swap(Frame frame, JPanel remove, JPanel add) {
        frame.remove(remove);
        frame.add(add);
        frame.revalidate();
        frame.repaint();
    }
    
    /**
     * Changes the swing look and feel, falls back to cross platform
     * @param s 
     */
    public static void setLayoutFeel(String s) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (s.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // If Nimbus is not available, fall back to cross-platform
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            }
        }
    }
}
