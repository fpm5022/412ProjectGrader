package view;


import controller.Compiler;
import controller.TestRunner;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Student;

public class FunctionsPanel extends JPanel {
    private final int Y_INCREMENT = 20; // space between boxes
    private int y = 10;        
    private int HEIGHT; // adjusted when students are added

        
    private Frame frame;
    private String compilePathDirectory; // directory to compile code into TODO: PULL OUT OF CLASS VARIABLE
    private String sourceCodeDirectory; // directory source code resides TODO: PULL OUT OF CLASS VARIABLE
    private JButton compilePathButton;
    private JButton sourceDirectoryButton;
    private JTextField sourceDirectoryTextField;
    private JLabel jLabel1;
    private JButton compileButton;
    private JButton testButton;
    private JTextField cmdLnArg;
    private JTextField expectedOutput;
    private JScrollPane outputScroller;
    private JLabel outputText;
    private JTextField compilePathTextField;
    private JTextField mainClassNameTextField;
    private TextPanel textPanel;
    XMLEncoder savePaths;
    XMLDecoder readPaths;
    int numOfOutputLines;

    public FunctionsPanel(Frame frame) {
        this.frame = frame;
        this.compilePathDirectory = "";
        this.sourceCodeDirectory = "";
        initComponents();
        pathReader();
        this.setLayout(null); // yolo  Â¯\_(ãƒ„)_/Â¯ 
        this.setVisible(true);
        this.setBackground(Color.pink);
    }

    private void initComponents() {
        this.compilePathButton = new JButton();
        this.sourceDirectoryButton = new JButton();
        this.sourceDirectoryTextField = new JTextField();
        this.jLabel1 = new JLabel();
        this.compileButton = new JButton();
        this.testButton = new JButton();
        this.cmdLnArg = new JTextField();
        this.expectedOutput = new JTextField();
        this.textPanel = new TextPanel();
        this.outputText = new JLabel();
        this.outputScroller = new JScrollPane();
        
        numOfOutputLines = 0;

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel1.setText("Batch Tester");
        jLabel1.setBounds(frame.WIDTH / 3 - 70, 10, 200, 40);
        this.add(jLabel1);

        sourceDirectoryButton.setText("Source Directory");
        sourceDirectoryButton.setBounds(10, 100, 150, 30);
        this.add(sourceDirectoryButton);
        sourceDirectoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceDirectoryActionPerformed(evt);
            }
        });

        sourceDirectoryTextField = new JTextField();
        sourceDirectoryTextField.setBounds(230, 100, 400, 30);
        sourceDirectoryTextField.setEditable(false);
        sourceDirectoryTextField.setText("Location of parent directory holding all students source codes");
        this.add(sourceDirectoryTextField);

        compilePathButton.setText("Compile Path..");
        compilePathButton.setBounds(10, 150, 150, 30);
        this.add(compilePathButton);
        compilePathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compilePathActionPerformed(evt);
            }
        });

        compilePathTextField = new JTextField();
        compilePathTextField.setBounds(230, 150, 400, 30);
        compilePathTextField.setEditable(false);
        compilePathTextField.setText("Directory to compile into");
        this.add(compilePathTextField);

        cmdLnArg.setText("Command Line Arguments");
        cmdLnArg.setBounds(10, 250, 300, 30);
        cmdLnArg.setEnabled(false);
        this.add(cmdLnArg);

        //expectedOutput.setText("Expected Output");
        expectedOutput.setText("1n = 1; range = 1; average = 1.0; stdDev = 0.0");
        expectedOutput.setBounds(10, 300, 300, 30);
        this.add(expectedOutput);

        compileButton.setText("Compile");
        compileButton.setBounds(500, 365, 150, 30);
        this.add(compileButton);
        compileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runCompileActionPerformed(evt);
            }
        });

        testButton.setText("Test");
        testButton.setBounds(500, 415, 150, 30);
        testButton.setEnabled(true);
        this.add(testButton);
        testButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runTestActionPerformed(evt);
            }
        });
        
        outputScroller.setVisible(true);
        outputScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        outputScroller.setPreferredSize(new Dimension(10,365));
        outputScroller.setMinimumSize(new Dimension(10,365));
        outputScroller.setViewportView(textPanel);
        outputScroller.setBounds(10,365,450, 300);
        this.add(outputScroller);
        
        mainClassNameTextField = new JTextField("Name of java class to compile (include .java)");
        mainClassNameTextField.setBounds(230, 200, 400, 30);
        add(mainClassNameTextField);
    }

    /*
     The action listener for setting the source directory
     */
    private void sourceDirectoryActionPerformed(ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setDialogTitle("Please select the directory containing all students source codes");
        this.add(chooser);

        int val = chooser.showOpenDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
            this.sourceCodeDirectory = chooser.getSelectedFile().getAbsolutePath();
            this.sourceDirectoryTextField.setText(this.sourceCodeDirectory);
        }
    }

    /*
     The action listener when the compile button is pressed
     */
    private void runCompileActionPerformed(ActionEvent evt) {
        String mainClassName = mainClassNameTextField.getText();

        // loop through selected students and start compiling
        for (Student s : frame.batchGUI.getSelectedStudents()) {
            String studentName = s.getName();
            String compilePath = compilePathDirectory + studentName; // directory to compile into
            String sourcePath = sourceCodeDirectory + File.separator + studentName + File.separator + mainClassName; // what to compile

            Compiler compiler = new Compiler(compilePathDirectory, compilePath, sourcePath);
            int success = compiler.compileJava();

            if (success != 0) {
                appendToTextArea(s.getInfo() + " compile failed: " + success, true);
            } else {
                appendToTextArea(s.getInfo() + " compile success", false);
                
                // for now, since it compiled lets test it. 
                // TO DO: do all compiling at once and store those that passed in a list.
                // to be tested afterwards
                String commandLineArguments = cmdLnArg.getText();
                String expectedTestOutput = expectedOutput.getText();
                
                // command line args should be a CSV. We need to parse that into an array.
                // this will split on zero or more whitespace, a literal comma, zero or more whitespace
                String[] splitCommandLineArgs = commandLineArguments.split("\\s*,\\s*");
                String[] scannerInput = {"1", "1"}; // to do
                
                // remove the .java from the class name
                String mainClassNameWithoutFileType = mainClassName.substring(0, mainClassName.length() - 5);
                
                TestRunner testRunner = new TestRunner(compilePath, compilePath, mainClassNameWithoutFileType, splitCommandLineArgs, scannerInput, expectedTestOutput);
                
                double similarity = testRunner.testJava();
                boolean failed = (similarity != 100);
                
                appendToTextArea(studentName + " " + similarity + "% similar to expected output", failed);
            }
        }
    }

    /*
     The action listener which sets the path to compile files into
     */
    private void compilePathActionPerformed(ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setDialogTitle("Please select the class path");
        this.add(chooser);

        int val = chooser.showOpenDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
            this.compilePathDirectory = chooser.getSelectedFile().getAbsolutePath() + File.separator; // append trailing slash
            this.compilePathTextField.setText(this.compilePathDirectory);
        }
    }

    private void runTestActionPerformed(ActionEvent evt) {
        try {
            savePaths = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(compilePathDirectory + "/paths.xml")));
            //System.out.println("File created");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FunctionsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            savePaths.writeObject(sourceCodeDirectory);
            savePaths.writeObject(compilePathDirectory);
            savePaths.writeObject(frame.batchGUI.getStudentPanel().getStudentFileLocationAbsolutePath());
            savePaths.writeObject(mainClassNameTextField.getText());
        } catch(Exception xx) {xx.printStackTrace();}
        try{
            savePaths.close();
        } catch(Exception xx) {xx.printStackTrace();}
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void pathReader(){
//        try{
//            readPaths = new XMLDecoder(new BufferedInputStream(new FileInputStream("/paths.xml")));
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(FunctionsPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try{
//            sourceCodeDirectory = (String)readPaths.readObject();
//            compilePathDirectory = (String)readPaths.readObject();
//            frame.batchGUI.getStudentPanel().setStudentFileLocationAbsolutePath((String)readPaths.readObject());
//            mainClassNameTextField.setText((String)readPaths.readObject());
//            
//        } catch (Exception xx) {xx.printStackTrace();}
    }
    // if error, output will be red
    public void appendToTextArea(String message, boolean error) {
        numOfOutputLines++;
        JLabel text = new JLabel(message + "\n");
        if (error) {
            text.setForeground(Color.red);
        } else {
            text.setForeground(Color.black);
        }
        textPanel.add(text);
        y = numOfOutputLines * Y_INCREMENT;
        HEIGHT = y; // update height of panel so scrolling can happen
        outputScroller.setPreferredSize(new Dimension(450,300));

        
        this.textPanel.setSize(new Dimension(450, y));
        this.textPanel.setPreferredSize(new Dimension(450, y)); 
            
        textPanel.repaint();
        textPanel.revalidate();
    }
}
