package view;


import controller.Compiler;
import controller.XMLSaver;
import controller.TestRunnerController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.beans.XMLDecoder;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.Student;
import model.TestRunnerModel;
import model.XMLObject;

public class FunctionsPanel extends JPanel {
    private Frame frame;
    private String compilePathDirectory; // directory to compile code into TODO: PULL OUT OF CLASS VARIABLE
    private String sourceCodeDirectory; // directory source code resides TODO: PULL OUT OF CLASS VARIABLE
    private JButton compilePathButton;
    private JButton sourceDirectoryButton;
    private JTextField sourceDirectoryTextField;
    private JLabel jLabel1;
    private JButton compileAndTestButton;
    private JTextField cmdLnArg;
    private JTextField expectedOutput;
    private JScrollPane outputScroller;
    private JTextField compilePathTextField;
    private JTextField mainClassNameTextField;
    private TextPanel textPanel;
    XMLDecoder readPaths;
    int numOfOutputLines;
    private XMLObject xmlObject;
    
    public FunctionsPanel(Frame frame, XMLObject xmlObject) {
        this.frame = frame;
        this.xmlObject = xmlObject;
        this.compilePathDirectory = "";
        this.sourceCodeDirectory =  "";
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
        this.compileAndTestButton = new JButton();
        this.cmdLnArg = new JTextField();
        this.expectedOutput = new JTextField();
        this.textPanel = new TextPanel();
        this.outputScroller = new JScrollPane(textPanel);
        
//        for(int i = 0; i < 100; i++) {
//            JLabel label = new JLabel("hey");
//            this.textPanel.addLabel(label);
//        }
        
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
        if (xmlObject.sourceCodeDirectory != null) {
            sourceDirectoryTextField.setText(xmlObject.sourceCodeDirectory);
            this.sourceCodeDirectory = xmlObject.sourceCodeDirectory;
        } else {
            sourceDirectoryTextField.setText("Location of parent directory holding all students source codes");
        }
        
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
        
        if (xmlObject.compilePathDirectory != null) {
            compilePathTextField.setText(xmlObject.compilePathDirectory);
            compilePathDirectory = xmlObject.compilePathDirectory;
        } else {
            compilePathTextField.setText("Directory to compile into");
        }
        
        this.add(compilePathTextField);

        cmdLnArg.setText("Command Line Arguments");
        cmdLnArg.setBounds(10, 250, 300, 30);
        cmdLnArg.setEnabled(false);
        this.add(cmdLnArg);
        
        if (xmlObject.expectedOutput != null) {
            expectedOutput.setText(xmlObject.expectedOutput);
        } else {
            expectedOutput.setText("Expected Output");
        }
        //expectedOutput.setText("1n = 1; range = 1; average = 1.0; stdDev = 0.0");
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
        //outputScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //outputScroller.setPreferredSize(new Dimension(10,365));
        //outputScroller.setMinimumSize(new Dimension(10,365));
        outputScroller.setBounds(10,365,450, 300);
        this.add(outputScroller);
        
        mainClassNameTextField = new JTextField("Name of java class to compile (include .java)");
        if (xmlObject.mainClassName != null) {
            mainClassNameTextField.setText(xmlObject.mainClassName);
        }
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
                String commandLineArguments = cmdLnArg.getText();
                String expectedTestOutput = expectedOutput.getText();
                
                // command line args should be a CSV. We need to parse that into an array.
                // this will split on zero or more whitespace, a literal comma, zero or more whitespace
                String[] splitCommandLineArgs = commandLineArguments.split("\\s*,\\s*");
                String[] scannerInput = {"1", "1"}; // to do
                
                // remove the .java from the class name
                String mainClassNameWithoutFileType = mainClassName.substring(0, mainClassName.length() - 5);
                
                TestRunnerModel testRunnerModel = new TestRunnerModel(compilePath, compilePath, mainClassNameWithoutFileType, splitCommandLineArgs, scannerInput, expectedTestOutput);
                
                double similarity = TestRunnerController.runAndTestJava(testRunnerModel);
                boolean failed = (similarity != 100);
                
                appendToTextArea(studentName + " " + similarity + "% similar to expected output", failed);
            }
        }
        
        // save the settings...
        frame.xmlSaver.addValueToWrite("mainClassName", mainClassName);
        frame.xmlSaver.addValueToWrite("compilePathDirectory", compilePathDirectory);
        frame.xmlSaver.addValueToWrite("sourceCodeDirectory", sourceCodeDirectory);
        frame.xmlSaver.addValueToWrite("expectedOutput", expectedOutput.getText());
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
        JLabel text = new JLabel(message + "\n");
        textPanel.addLabel(text,error);
    }
}
