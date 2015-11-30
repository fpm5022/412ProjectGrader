package view;

import controller.FunctionsPanelController;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.beans.XMLDecoder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.Student;
import model.TestRunnerModel;
import model.CompilerModel;
import model.FunctionsPanelModel;
import model.XMLObject;



import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;






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
    Font myFont = new Font("Century Schoolbook", Font.BOLD, 50);
    Font myFont2 = new Font("Century Schoolbook", Font.PLAIN, 14);
    
    Border thickBorder = new LineBorder(Color.decode("#4B0082"), 3);
    
    
   
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
    
    
    
    
    
    
    
    
    
    public FunctionsPanel(Frame frame, XMLObject xmlObject) {
        this.frame = frame;
        this.model = new FunctionsPanelModel();
        this.self = this;
        model.setDefaults(xmlObject);
        initComponents();
        this.setLayout(null);
        this.setVisible(true);
        //this.setBackground(Color.decode("#60DFE5"));
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
        jLabel1.setFont(myFont);
        jLabel1.setBounds(frame.WIDTH / 3 - 150, 20, 400, 40);
        this.add(jLabel1);
          
        sourceDirectoryButton.setText("Source Directory");
        sourceDirectoryButton.setFont(myFont2);
        sourceDirectoryButton.setBounds(10, 100, 150, 30);
        sourceDirectoryButton.setBorder(thickBorder);
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
        sourceDirectoryTextField.setFont(myFont2);
        sourceDirectoryTextField.setText(model.sourceCodeDirectory);
        this.add(sourceDirectoryTextField);

        compilePathButton.setText("Compile Path..");
        compilePathButton.setFont(myFont2);
        compilePathButton.setBounds(10, 150, 150, 30);
        compilePathButton.setBorder(thickBorder);
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
        compilePathTextField.setFont(myFont2);
        this.add(compilePathTextField);

        cmdLnArg.setText("Command Line Arguments");
        cmdLnArg.setFont(myFont2);
        cmdLnArg.setBounds(10, 250, 300, 30);
        cmdLnArg.setEnabled(false);
        this.add(cmdLnArg);
        
        expectedOutput.setText(model.expectedTestOutput);
        expectedOutput.setBounds(10, 300, 300, 30);
        expectedOutput.setFont(myFont2);
        this.add(expectedOutput);

        compileAndTestButton.setText("Compile And Test");
        compileAndTestButton.setFont(myFont2);
        compileAndTestButton.setBounds(500, 365, 150, 30);
        compileAndTestButton.setBorder(thickBorder);
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
        mainClassNameTextField.setFont(myFont2);
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