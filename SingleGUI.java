import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
@author Feek <feek@psu.edu>
**/


public class SingleGUI extends JPanel 
{
    // COMPONENTS
    private JButton backButton;
    private JTextField nameOfClassTextField;
    private JButton compilePathButton;
    private JTextField compilePathTextField;
    private JButton classPathSelect;
    private JTextField classPathTextField;
    private JButton testInputLocationButton;
    private JTextField testInputLocationField;
    private JLabel programLabel;
    private JButton compileButton;
    private Frame frame;
    private JTextField expectedOutput;
    private JLabel nameOfClassLabel;
    private JButton testButton;
    private JTextArea outputText;
    private JScrollPane outputScroller;
    private BufferedReader reader;
    private File outputFile;
    
    // fields
    private String compilePathDirectory;
    private String classPathDirectory;
    private String className;
    private String testFileLocation;
    String commandLineArguments;
    String expectedTestOutput;

    int runNumber;
    private String studentName; // TO DO: pull from class to compile
    private String studentHandle;
    private String compilePath;
    private String sourcePath;
    private String studentPath;
    private String outputFileName = "output.txt";
    private String mainClassName = "ArrayLoops.java"; // TO DO: pull from class to compile
    
    public SingleGUI(Frame frame) 
    {
        this.frame = frame;
        this.compilePathDirectory = "";
        this.classPathDirectory = "";
        this.setSize(frame.WIDTH, frame.HEIGHT);
        initComponents();
        this.setLayout(null); // yolo  ¯\_(ツ)_/¯ 
        this.setVisible(true);
    }
    
    private void initComponents() {
        this.backButton = new JButton();
        this.nameOfClassTextField = new JTextField();
        this.nameOfClassLabel = new JLabel();
        this.compilePathButton = new JButton();
        this.classPathSelect = new JButton();
        this.classPathTextField = new JTextField();
        this.programLabel = new JLabel();
        this.compileButton = new JButton();
        this.testButton = new JButton();
        this.testInputLocationButton = new JButton();
        this.testInputLocationField = new JTextField();
        this.expectedOutput = new JTextField();
        this.outputText = new JTextArea();
        this.outputScroller = new JScrollPane(outputText);
        this.className = "412";
        
        programLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        programLabel.setText("Single Tester");
        programLabel.setBounds(frame.WIDTH / 2 - 70, 10, 200, 40);
        this.add(programLabel);

        backButton.setText("<- Back");
        backButton.setBounds(10, 20, 100, 30);
        this.add(backButton);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        classPathSelect.setText("Class To Compile");
        classPathSelect.setBounds(10, 100, 150, 30);
//        fileChooser.setEnabled(false);
        this.add(classPathSelect);
        classPathSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classChooserActionPerformed(evt);
            }
        });
        
        classPathTextField = new JTextField();
        classPathTextField.setBounds(250, 100, 250, 30);
        classPathTextField.setEditable(false);
        classPathTextField.setText("location of class");
        this.add(classPathTextField);

        nameOfClassLabel.setText("Class Name:");
        nameOfClassLabel.setBounds(10, 150, 100, 30);
        this.add(nameOfClassLabel);
        
        nameOfClassTextField.setText(className); // hard coded for now
        nameOfClassTextField.setBounds(250, 150, 100, 30);
        nameOfClassTextField.setEditable(false);
        this.add(nameOfClassTextField);

        compilePathButton.setText("Compile Path..");
        compilePathButton.setBounds(10, 200, 100, 30);
        this.add(compilePathButton);
        compilePathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compilePathActionPerformed(evt);
            }
        });
        
        compilePathTextField = new JTextField();
        compilePathTextField.setBounds(250, 200, 200, 30);
        compilePathTextField.setEditable(false);
        compilePathTextField.setText("directory to compile into");
        this.add(compilePathTextField);
        
        testInputLocationButton.setText("Test Input Location");
        testInputLocationButton.setBounds(10, 250, 150, 30);
//        fileChooser.setEnabled(false);
        this.add(testInputLocationButton);
        testInputLocationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testFileLocationChooser(evt);
            }
        });
        
        testInputLocationField.setText("Test File Location (fornow)");
        testInputLocationField.setBounds(250, 250, 300, 30);
//        testInputLocationField.setEnabled(false);
        this.add(testInputLocationField);
        
        expectedOutput.setText("Expected Output");
        expectedOutput.setBounds(10, 300, 300, 30);
        expectedOutput.setEnabled(false);
        this.add(expectedOutput);
        
        compileButton.setText("Compile");
        compileButton.setBounds(frame.WIDTH / 2 - 50, 400, 100, 30);
        this.add(compileButton);
        compileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runCompileActionPerformed(evt);
            }
        });
        
        testButton.setText("Test");
        testButton.setBounds(frame.WIDTH / 2 + 50, 400, 100, 30);
//        testButton.setEnabled(false);
        this.add(testButton);
        testButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runTestActionPerformed(evt);
            }
        });
        
        outputText.setFont(new java.awt.Font("Tahoma", 0, 14));
        outputText.setLineWrap(true);
        outputText.setWrapStyleWord(true);
        outputText.setBounds(0, 450, frame.WIDTH, 225);
        outputText.setEditable(false);
        
        outputScroller.setVisible(true);
        outputScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        outputScroller.setBounds(0, 450, frame.WIDTH, 225);
        
        this.add(outputScroller);
    }
    
    private void backButtonActionPerformed(ActionEvent evt) 
    {
        frame.swap(this, frame.splash);
    }
    
    private void compileChooserActionPerformed(ActionEvent evt) 
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setDialogTitle("Please select the Compile Location");
        this.add(chooser);
        
        int val = chooser.showOpenDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
            this.compilePathDirectory = chooser.getSelectedFile().getAbsolutePath() + File.separator; // append trailing slash
            this.compilePathTextField.setText(this.compilePathDirectory);
        }
    }
    
        private void classChooserActionPerformed(ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setDialogTitle("Please select the class path");
        this.add(chooser);
        
        int val = chooser.showOpenDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
            this.classPathDirectory = chooser.getSelectedFile().getAbsolutePath();// append trailing slash
            this.classPathTextField.setText(this.classPathDirectory);
        }
    }
        
    private void runCompileActionPerformed(ActionEvent evt) {
        commandLineArguments = testInputLocationField.getText();
        expectedTestOutput = expectedOutput.getText();
        className = nameOfClassTextField.getText();
        
        int runNumber = 1;
        studentName = "feek"; // TO DO: pull from class to compile
        studentHandle = "";
        compilePath = compilePathDirectory + className + studentName;
        sourcePath = classPathDirectory;
        studentPath = sourcePath;
//        outputFileName = "output.txt";
//        mainClassName = "ArrayLoops.java"; // TO DO: pull from class to compile
        
        Compiler compiler = new Compiler(runNumber, studentName, studentHandle, compilePathDirectory, compilePath, sourcePath, studentPath, outputFileName, mainClassName);
        int success = compiler.compileJava();
        
//        System.out.println(compilePathDirectory);
//        System.out.println(compilePath);
//        System.out.println(sourcePath);
//        System.out.println(studentPath);
        
        if (success != 0 ) {
            System.err.println("compile failed: " + success);
            readOutputFile();
            outputText.setForeground(Color.red);
            outputText.setText("Your Compile has Failed. Please check all values and try again.");
        } else {
            System.out.println("compile success");
            readOutputFile();
            outputText.setForeground(Color.black);
            outputText.setText("Your Compile Has Succeeded.");
        }
    }
    
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
    
        private void testFileLocationChooser(ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setDialogTitle("Please Select the Test Input File");
        this.add(chooser);
        
        int val = chooser.showOpenDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
            this.testFileLocation = chooser.getSelectedFile().getAbsolutePath() + File.separator; // append trailing slash
            this.testInputLocationField.setText(this.testFileLocation);
        }
    }
    
    private void runTestActionPerformed(ActionEvent evt) 
    {
        String testDataPath = sourcePath;
        String testInputFileName = sourcePath + "/TestInput.txt";
        String argsFileName = testDataPath + "/args.txt";
        String inputFileStub = studentPath + "/input";
        outputFileName = "/output-" + studentName + ".txt";
        SingleTester st = new SingleTester(sourcePath,compilePath,testFileLocation);
        st.runJava();
//        TestRunner r = new TestRunner(runNumber, studentName, studentHandle, compilePath, classPathDirectory, sourcePath, studentPath,  testDataPath, argsFileName, testInputFileName, inputFileStub, outputFileName);
//        r.runJava();
    }
    
    public void readOutputFile(){
        Path file = FileSystems.getDefault().getPath(compilePathDirectory,"output.txt");  //Output file path - ("Whatever Folder has file", "Filename.txt")
            try(InputStream in = Files.newInputStream(file);
                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(in))){
                String line = null;
                while((line = reader.readLine()) != null){
                    outputText.append(line + "\n");;
                }
            } catch (IOException x){
                System.err.println(x);
            } 
    }
}
