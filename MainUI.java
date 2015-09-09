/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

/**
 *
 * @author jtm5448
 */

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
public class JavaApplication4 {
public static boolean RIGHT_TO_LEFT = false;
    public static void addComponentsToPane(Container pane) {
         
        if (!(pane.getLayout() instanceof BorderLayout)) {
            pane.add(new JLabel("Container doesn't use BorderLayout!"));
            return;
        }
         
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(
                    java.awt.ComponentOrientation.RIGHT_TO_LEFT);
        }
         
        JButton button = new JButton();
        JLabel label = new JLabel("Welcome! Please choose a tester.");
        label.setFont(new java.awt.Font("SWRomnd", 2, 24));
        pane.add(label, BorderLayout.PAGE_START);
//pane.add(button, BorderLayout.PAGE_START);
         
        //Make the center component big, since that's the
        //typical usage of BorderLayout.
        JButton button2 = new JButton();
        button2 = new JButton("Batch");
        button2.setFont(new java.awt.Font("SWRmond", 2, 18));
        button2.setSize(200, 100);
        pane.add(button2, BorderLayout.EAST);
                      button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //SingleSelectorActionPerformed(evt);
            }
                      }
        JButton button3 = new JButton();
        button3 = new JButton("Single");
        button3.setFont(new java.awt.Font("SWRmond", 2, 18));
        button3.setSize(new Dimension(100, 100));
        pane.add(button3, BorderLayout.LINE_START);
                button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //BatchSelectorActionPerformed(evt);
            }
        });
         
       /* button = new JButton("Long-Named Button 4 (PAGE_END)");
        pane.add(button, BorderLayout.PAGE_END);*/
         
        button = new JButton("5 (LINE_END)");
       // pane.add(button, BorderLayout.LINE_END);
    }
     
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
         
        //Create and set up the window.
        JFrame frame = new JFrame("Java Homework Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
        //Use the content pane's default BorderLayout. No need for
        //setLayout(new BorderLayout());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
     
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
        private void SingleSelector2ActionPerformed(java.awt.event.ActionEvent evt) {
        // Link to SingleGUI
    }
    
        private void BatchSelectorActionPerformed(java.awt.event.ActionEvent evt) {
        // Link to BatchGUI
    }
}

