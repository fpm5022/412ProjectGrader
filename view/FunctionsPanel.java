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
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

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
    public JProgressBar progressBar;
    private final int COMPONENT_LEFT = 10, COMPONENT_MIDDLE = 230, COMPONENT_RIGHT = 500, COMPONENT_HEIGHT = 30;
    private JLabel commandLineArgsLabel, scannerInputLabel, expectedResultsLabel;
    private JTextField scannerInput;

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
        createComponents();
        setComponents();
        assignActionListeners();
        addComponents();
    }
    
    private void createComponents() {
        this.compilePathButton = BatchGUIController.generateButton("Compile Path");
        this.sourceDirectoryButton = BatchGUIController.generateButton("Source Directory");
        this.clearOutputButton = BatchGUIController.generateButton("Clear Output Area");
        this.sourceDirectoryTextField = new JTextField();
        this.title = new JLabel("Automated Program Compiler And Tester");
        this.compileAndTestButton = BatchGUIController.generateButton("Compile And Test");
        this.cmdLnArg = new JTextField();
        this.expectedOutput = new JTextField();
        this.scannerInput = new JTextField();
        this.textPanel = new TextPanel();
        this.outputScroller = new JScrollPane(textPanel);
        this.mainClassNameTextField = new JTextField();
        this.mainClassNameLabel = BatchGUIController.generateLabel("Main Class Name (include .java)");
        this.commandLineArgsLabel = BatchGUIController.generateLabel("Command Line Args (CSV)");
        this.scannerInputLabel = BatchGUIController.generateLabel("Scanner Input (CSV)");
        this.expectedResultsLabel = BatchGUIController.generateLabel("Expected Output");
        this.sourceDirectoryTextField = new JTextField();
        this.compilePathTextField = new JTextField();
        this.progressBar = new JProgressBar(JProgressBar.HORIZONTAL);
    }
    
    private void addComponents() {
        this.add(title);
        this.add(sourceDirectoryButton);
        this.add(sourceDirectoryTextField); 
        this.add(compilePathButton);
        this.add(compilePathTextField);
        this.add(commandLineArgsLabel);
        this.add(cmdLnArg);
        this.add(scannerInput);
        this.add(scannerInputLabel);
        this.add(expectedResultsLabel);
        this.add(expectedOutput);
        this.add(compileAndTestButton);
        this.add(outputScroller);
        this.add(mainClassNameLabel);
        this.add(mainClassNameTextField);
        this.add(clearOutputButton);
        this.add(progressBar);
    }
    
    private void setComponents() {
        title.setFont(new java.awt.Font("Tahoma", 0, 24));
        title.setBounds(frame.WIDTH / 3 - 200, 20, 500, 40);
        
        sourceDirectoryButton.setBounds(COMPONENT_LEFT, 100, 150, COMPONENT_HEIGHT);
        sourceDirectoryButton.setToolTipText("The location of the parent directory containing all of the students source codes.");
        
        sourceDirectoryTextField.setBounds(COMPONENT_MIDDLE, 100, 400, COMPONENT_HEIGHT);
        sourceDirectoryTextField.setText(model.sourceCodeDirectory);
        
        compilePathButton.setBounds(COMPONENT_LEFT, 150, 150, COMPONENT_HEIGHT);
        compilePathButton.setToolTipText("The directory that you would like to compile the students code into");
        
        compilePathTextField.setBounds(COMPONENT_MIDDLE, 150, 400, COMPONENT_HEIGHT);
        compilePathTextField.setText(model.compilePathDirectory);
        
        commandLineArgsLabel.setBounds(COMPONENT_LEFT, 250, 300, COMPONENT_HEIGHT);
         
        cmdLnArg.setText(model.commandLineArguments);
        cmdLnArg.setBounds(COMPONENT_MIDDLE, 250, 300, COMPONENT_HEIGHT);
        
        scannerInput.setText(model.scannerInput);
        scannerInput.setBounds(COMPONENT_MIDDLE, 300, 300, COMPONENT_HEIGHT);
        
        scannerInputLabel.setBounds(COMPONENT_LEFT, 300, 300, COMPONENT_HEIGHT);
        
        expectedResultsLabel.setBounds(COMPONENT_LEFT, 350, 300, COMPONENT_HEIGHT);
        
        expectedOutput.setText(model.expectedTestOutput);
        expectedOutput.setBounds(COMPONENT_MIDDLE, 350, 300, COMPONENT_HEIGHT);
        
        compileAndTestButton.setBounds(COMPONENT_RIGHT, 415, 150, COMPONENT_HEIGHT);
        
        outputScroller.setVisible(true);
        outputScroller.setBounds(COMPONENT_LEFT, 415, 450, 250);
        
        mainClassNameLabel.setBounds(COMPONENT_LEFT, 200, 300, COMPONENT_HEIGHT);
        
        mainClassNameTextField.setText(model.mainClassName);
        mainClassNameTextField.setBounds(COMPONENT_MIDDLE, 200, 400, COMPONENT_HEIGHT);
        
        clearOutputButton.setBounds(COMPONENT_RIGHT, 450, 150, COMPONENT_HEIGHT);
        
        progressBar.setBounds(COMPONENT_LEFT, 675, 450, 10);
   }
    
     private void assignActionListeners() {
        clearOutputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FunctionsPanelController.clearOutputArea(textPanel);
            }
        });
        
        compileAndTestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runCompileActionPerformed(evt);
            }
        });
        
        compilePathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FunctionsPanelController.compilePathActionPerformed(self);
            }
        });
        
        sourceDirectoryButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FunctionsPanelController.sourceDirectoryActionPerformed(self);
            }
        });
    }

    /*
     The action listener when the compile button is pressed
     */
    private void runCompileActionPerformed(ActionEvent evt) {
        model.mainClassName = mainClassNameTextField.getText();
        model.commandLineArguments = cmdLnArg.getText();
        model.scannerInput = scannerInput.getText();
        model.expectedTestOutput = expectedOutput.getText();
        
        // update the progress bar min / max
        progressBar.setMinimum(0);
        progressBar.setMaximum(frame.batchGUI.getSelectedStudents().size());
        progressBar.setValue(0);
        
        FunctionsPanelController.compileActionPerformed(self, model, frame.batchGUI.getSelectedStudents());
    }
    
    public void appendToTextArea(String message) {
        appendToTextArea(message, false, null);
    }
    
    public void appendToTextArea(String message, boolean error) {
        appendToTextArea(message, error, "No error message was provided :(");
    }
    
    /**
     * Wrapper for adding a label to the text panel.
     * If there is an error, a mouse listener will be applied to the label and will show
     * a popup containing an error message (TBD)
     * @param message
     * @param error if true, output will be red
     */
    public void appendToTextArea(String message, boolean error, final String errorMessage) {
        JLabel text = new JLabel(message + "\n");
        
        if (error) {
            // add a mouse listener for on click
            text.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            text.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent me) {
                   frame.showPopup(errorMessage);
                }

                @Override
                public void mousePressed(MouseEvent me) {}

                @Override
                public void mouseReleased(MouseEvent me) {}

                @Override
                public void mouseEntered(MouseEvent me) {}

                @Override
                public void mouseExited(MouseEvent me) {}
            });
        }
        
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