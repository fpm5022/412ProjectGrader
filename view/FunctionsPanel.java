package view;

import controller.FunctionsPanelController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.beans.XMLDecoder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import model.FunctionsPanelModel;
import model.XMLObject;
import worker.CompileAndTestWorker;

public class FunctionsPanel extends JPanel {
    public final Frame frame;
    private JButton compilePathButton;
    private JButton sourceDirectoryButton;
    public JTextField sourceDirectoryTextField;
    private JLabel jLabel1;
    private JButton compileAndTestButton;
    private JTextField cmdLnArg;
    private JTextField expectedOutput;
    private JScrollPane outputScroller;
    public JTextField compilePathTextField;
    private JTextField mainClassNameTextField;
    private TextPanel textPanel;
    XMLDecoder readPaths;
    public FunctionsPanelModel model;
    private final FunctionsPanel self;
    
    public FunctionsPanel(Frame frame, XMLObject xmlObject) {
        this.frame = frame;
        this.model = new FunctionsPanelModel();
        this.self = this;
        model.setDefaults(xmlObject);
        initComponents();
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.pink);
    }

    private void initComponents() {
        this.compilePathButton = new JButton();
        this.sourceDirectoryButton = new JButton();
        this.sourceDirectoryTextField = new JTextField();
        this.jLabel1 = new JLabel();
        this.compileAndTestButton = new JButton();
        this.cmdLnArg = new JTextField();
        this.expectedOutput = new JTextField();
        this.textPanel = new TextPanel();
        this.outputScroller = new JScrollPane(textPanel);
        this.mainClassNameTextField = new JTextField();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel1.setText("Batch Tester");
        jLabel1.setBounds(frame.WIDTH / 3 - 70, 10, 200, 40);
        this.add(jLabel1);
          
        sourceDirectoryButton.setText("Source Directory");
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

        compilePathButton.setText("Compile Path..");
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

        cmdLnArg.setText("Command Line Arguments");
        cmdLnArg.setBounds(10, 250, 300, 30);
        cmdLnArg.setEnabled(false);
        this.add(cmdLnArg);
        
        expectedOutput.setText(model.expectedTestOutput);
        expectedOutput.setBounds(10, 300, 300, 30);
        this.add(expectedOutput);

        compileAndTestButton.setText("Compile And Test");
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
    
    // if error, output will be red
    public void appendToTextArea(String message, boolean error) {
        JLabel text = new JLabel(message + "\n");
        textPanel.addLabel(text,error);
    }
}