
/**
 *
 * @author jtm5448
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SplashScreen extends JPanel {

    public static boolean RIGHT_TO_LEFT = false;
    private final Frame frame;
    
    JButton button, batchButton, singleButton;
    JLabel welcome, label;

    private void addComponents() 
    {

        //background color
        this.setBackground(Color.pink);
        this.setLayout(null);
        
        if (RIGHT_TO_LEFT) {
            this.setComponentOrientation(
                    java.awt.ComponentOrientation.RIGHT_TO_LEFT);
        }

        button = new JButton();
        welcome = new JLabel("Welcome!");
        label = new JLabel(" Please choose a tester");
        
        welcome.setFont(new java.awt.Font("Herculanum", 2, 80));
        welcome.setForeground(Color.white);
        welcome.setBounds(345, 50, 500, 70);
        this.add(welcome);
         
        label.setFont(new java.awt.Font("Herculanum", 2, 30));
        label.setForeground(Color.white);
        label.setBounds(362, 140, 500, 70);
        this.add(label);
        
//this.add(button, BorderLayout.PAGE_START);

        //Make the center component big, since that's the
        //typical usage of BorderLayout.
        batchButton = new JButton();
        batchButton = new JButton("Batch");
        batchButton.setFont(new java.awt.Font("SWRmond", 2, 18));
        batchButton.setBounds(410, 250, 100, 40);
        this.add(batchButton);
        
        batchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BatchSelectorActionPerformed(evt);
            }
        });
        singleButton = new JButton();
        singleButton = new JButton("Single");
        singleButton.setFont(new java.awt.Font("SWRmond", 2, 18));
        singleButton.setBounds(560, 250, 100, 40);
        this.add(singleButton);
        
        singleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SingleSelectorActionPerformed(evt);
            }
        });

        /* button = new JButton("Long-Named Button 4 (PAGE_END)");
         this.add(button, BorderLayout.PAGE_END);*/
        button = new JButton("5 (LINE_END)");
        // this.add(button, BorderLayout.LINE_END);
    }

    private void createAndShowGUI() {
        this.setSize(frame.WIDTH, frame.HEIGHT);
        addComponents();
        this.setVisible(true);
    }

    public SplashScreen (Frame frame) {
        this.frame = frame;
        createAndShowGUI();
    }

    private void SingleSelectorActionPerformed(java.awt.event.ActionEvent evt) {
        frame.swap(this, frame.singleGUI);
    }

    private void BatchSelectorActionPerformed(java.awt.event.ActionEvent evt) {
        frame.swap(this, frame.batchGUI);
    }
}
