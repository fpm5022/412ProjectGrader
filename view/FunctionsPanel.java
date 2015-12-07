package view;

import controller.BatchGUIController;
import controller.FunctionsPanelController;
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

public class FunctionsPanel extends JPanel {
    public final Frame frame;
    private JButton compilePathButton, sourceDirectoryButton, compileAndTestButton, clearOutputButton;
    public JTextField sourceDirectoryTextField, cmdLnArg, expectedOutput, compilePathTextField, mainClassNameTextField;
    private JScrollPane outputScroller;
    private TextPanel textPanel;
    public XMLDecoder readPaths;
    public FunctionsPanelModel model;
    private final FunctionsPanel self;
    private JLabel title, mainClassNameLabel;
    
    private final int COMPONENT_LEFT = 10, COMPONENT_MIDDLE = 230, COMPONENT_RIGHT = 500, COMPONENT_HEIGHT = 30;
    private JLabel commandLineArgsLabel;
    private JLabel expectedResultsLabel;

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
        this.title = new JLabel();
        this.compileAndTestButton = BatchGUIController.generateButton("Compile And Test");
        this.cmdLnArg = new JTextField();
        this.expectedOutput = new JTextField();
        this.textPanel = new TextPanel();
        this.outputScroller = new JScrollPane(textPanel);
        this.mainClassNameTextField = new JTextField();
        this.mainClassNameLabel = BatchGUIController.generateLabel("Main Class Name (include .java)");
        this.commandLineArgsLabel = BatchGUIController.generateLabel("Command Line Args (CSV)");
        this.expectedResultsLabel = BatchGUIController.generateLabel("Expected Output");

        title.setFont(new java.awt.Font("Tahoma", 0, 24));
        title.setText("Batch Tester");
        title.setBounds(frame.WIDTH / 3 - 150, 20, 400, 40);
        this.add(title);
          
        sourceDirectoryButton.setBounds(COMPONENT_LEFT, 100, 150, COMPONENT_HEIGHT);
        this.add(sourceDirectoryButton);
        sourceDirectoryButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FunctionsPanelController.sourceDirectoryActionPerformed(self);
            }
        });

        sourceDirectoryTextField = new JTextField();
        sourceDirectoryTextField.setBounds(COMPONENT_MIDDLE, 100, 400, COMPONENT_HEIGHT);
        sourceDirectoryTextField.setText(model.sourceCodeDirectory);
        this.add(sourceDirectoryTextField);

        compilePathButton.setBounds(COMPONENT_LEFT, 150, 150, COMPONENT_HEIGHT);
        this.add(compilePathButton);
        compilePathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FunctionsPanelController.compilePathActionPerformed(self);
            }
        });

        compilePathTextField = new JTextField();
        compilePathTextField.setBounds(COMPONENT_MIDDLE, 150, 400, COMPONENT_HEIGHT);
        compilePathTextField.setText(model.compilePathDirectory);
        this.add(compilePathTextField);

        commandLineArgsLabel.setBounds(COMPONENT_LEFT, 250, 300, COMPONENT_HEIGHT);
        this.add(commandLineArgsLabel);
        
        cmdLnArg.setText(model.commandLineArguments);
        cmdLnArg.setBounds(COMPONENT_MIDDLE, 250, 300, COMPONENT_HEIGHT);
        this.add(cmdLnArg);
        
        expectedResultsLabel.setBounds(COMPONENT_LEFT, 300, 300, COMPONENT_HEIGHT);
        this.add(expectedResultsLabel);
        
        expectedOutput.setText(model.expectedTestOutput);
        expectedOutput.setBounds(COMPONENT_MIDDLE, 300, 300, COMPONENT_HEIGHT);
        this.add(expectedOutput);

        compileAndTestButton.setBounds(COMPONENT_RIGHT, 365, 150, COMPONENT_HEIGHT);
        this.add(compileAndTestButton);
        compileAndTestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runCompileActionPerformed(evt);
            }
        });
        
        outputScroller.setVisible(true);
        outputScroller.setBounds(COMPONENT_LEFT,365,450, 300);
        this.add(outputScroller);
        
        mainClassNameLabel.setBounds(COMPONENT_LEFT, 200, 300, COMPONENT_HEIGHT);
        this.add(mainClassNameLabel);
        
        mainClassNameTextField.setText(model.mainClassName);
        mainClassNameTextField.setBounds(COMPONENT_MIDDLE, 200, 400, COMPONENT_HEIGHT);
        this.add(mainClassNameTextField);
        
        clearOutputButton.setBounds(COMPONENT_RIGHT, 405, 150, COMPONENT_HEIGHT);
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