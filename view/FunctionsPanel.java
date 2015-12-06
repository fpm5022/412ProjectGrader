package view;

import controller.BatchGUIController;
import controller.FunctionsPanelController;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.beans.XMLDecoder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.FunctionsPanelModel;
import model.XMLObject;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class FunctionsPanel extends JPanel {
    public final Frame frame;
    private JButton compilePathButton, sourceDirectoryButton, compileAndTestButton, clearOutputButton;
    public JTextField sourceDirectoryTextField, cmdLnArg, expectedOutput, compilePathTextField, mainClassNameTextField;
    private JScrollPane outputScroller;
    private TextPanel textPanel;
    public XMLDecoder readPaths;
    public FunctionsPanelModel model;
    private final FunctionsPanel self;
    
    private JLabel jLabel1;

    public FunctionsPanel(Frame frame, XMLObject xmlObject) {
        this.frame = frame;
        this.model = new FunctionsPanelModel();
        this.self = this;
        model.setDefaults(xmlObject);
        
        initComponents();
        this.setLayout(null);
        this.setVisible(true);
    }

    /**
     * Initializes and adds all components
     */
    private void initComponents() {
        this.compilePathButton = BatchGUIController.generateButton("Compile Path");
        this.sourceDirectoryButton = BatchGUIController.generateButton("Source Directory");
        this.clearOutputButton = BatchGUIController.generateButton("Clear Output Area");
        this.sourceDirectoryTextField = new JTextField();
        this.jLabel1 = new JLabel();
        this.compileAndTestButton = BatchGUIController.generateButton("Compile And Test");
        this.cmdLnArg = new JTextField();
        this.expectedOutput = new JTextField();
        this.textPanel = new TextPanel();
        this.outputScroller = new JScrollPane(textPanel);
        this.mainClassNameTextField = new JTextField();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel1.setText("Batch Tester");
        jLabel1.setBounds(frame.WIDTH / 3 - 150, 20, 400, 40);
        this.add(jLabel1);
          
        sourceDirectoryButton.setBounds(10, 100, 150, 30);
        this.add(sourceDirectoryButton);
        sourceDirectoryButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FunctionsPanelController.sourceDirectoryActionPerformed(self);
            }
        });

        sourceDirectoryTextField = new JTextField();
        sourceDirectoryTextField.setBounds(230, 100, 400, 30);
        sourceDirectoryTextField.setEditable(false);
        sourceDirectoryTextField.setText(model.sourceCodeDirectory);
        this.add(sourceDirectoryTextField);

        compilePathButton.setBounds(10, 150, 150, 30);
        this.add(compilePathButton);
        compilePathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FunctionsPanelController.compilePathActionPerformed(self);
            }
        });

        compilePathTextField = new JTextField();
        compilePathTextField.setBounds(230, 150, 400, 30);
        compilePathTextField.setEditable(false);
        compilePathTextField.setText(model.compilePathDirectory);
        this.add(compilePathTextField);

        cmdLnArg.setText(model.commandLineArguments);
        cmdLnArg.setBounds(10, 250, 300, 30);
        this.add(cmdLnArg);
        
        expectedOutput.setText(model.expectedTestOutput);
        expectedOutput.setBounds(10, 300, 300, 30);
        this.add(expectedOutput);

        compileAndTestButton.setBounds(500, 365, 150, 30);
        this.add(compileAndTestButton);
        compileAndTestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runCompileActionPerformed(evt);
            }
        });
        
        outputScroller.setVisible(true);
        outputScroller.setBounds(10,365,450, 300);
        this.add(outputScroller);
        
        mainClassNameTextField.setText(model.mainClassName);
        mainClassNameTextField.setBounds(230, 200, 400, 30);
        this.add(mainClassNameTextField);
        
        clearOutputButton.setBounds(500, 405, 150, 30);
        clearOutputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FunctionsPanelController.clearOutputArea(textPanel);
            }
        });
        this.add(clearOutputButton);
    }

    /*
     The action listener when the compile button is pressed
     */
    private void runCompileActionPerformed(ActionEvent evt) {

        model.mainClassName = mainClassNameTextField.getText();
        model.commandLineArguments = cmdLnArg.getText();
        model.expectedTestOutput = expectedOutput.getText();
        
        FunctionsPanelController.compileActionPerformed(self, model, frame.batchGUI.getSelectedStudents());
    }
    
    /**
     * Wrapper for adding a label to the text panel
     * @param message
     * @param error if true, output will be red
     */
    public void appendToTextArea(String message, boolean error) {
        JLabel text = new JLabel(message + "\n");
        textPanel.addLabel(text,error);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = Color.decode("#60DFE5");
        Color color2 = Color.WHITE;
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}