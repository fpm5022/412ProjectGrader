package view;


/**
 *
 * @author jtm5448
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SplashScreen extends JPanel {

    public static boolean RIGHT_TO_LEFT = false;
    private final Frame frame;

    private void addComponents() {

        if (RIGHT_TO_LEFT) {
            this.setComponentOrientation(
                    java.awt.ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton button = new JButton();
        JLabel label = new JLabel("Welcome! Please choose a tester.");
        label.setFont(new java.awt.Font("SWRomnd", 2, 24));
        this.add(label, BorderLayout.PAGE_START);
//this.add(button, BorderLayout.PAGE_START);

        //Make the center component big, since that's the
        //typical usage of BorderLayout.
        JButton batchButton = new JButton();
        batchButton = new JButton("Batch");
        batchButton.setFont(new java.awt.Font("SWRmond", 2, 18));
        batchButton.setSize(200, 100);
        this.add(batchButton, BorderLayout.EAST);
        batchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BatchSelectorActionPerformed(evt);
            }
        });
        JButton singleButton = new JButton();
        singleButton = new JButton("Single");
        singleButton.setFont(new java.awt.Font("SWRmond", 2, 18));
        singleButton.setSize(new Dimension(100, 100));
        this.add(singleButton, BorderLayout.LINE_START);
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
