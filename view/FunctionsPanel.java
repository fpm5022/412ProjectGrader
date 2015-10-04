package view;


import controller.Compiler;
import controller.TestRunner;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Student;

public class FunctionsPanel extends JPanel {
    private Frame frame;
    private String compilePathDirectory; // directory to compile code into TODO: PULL OUT OF CLASS VARIABLE
    private String sourceCodeDirectory; // directory source code resides TODO: PULL OUT OF CLASS VARIABLE
    private JButton backButton;
    private JButton compilePathButton;
    private JButton sourceDirectoryButton;
    private JTextField sourceDirectoryTextField;
    private JLabel jLabel1;
    private JButton compileButton;
    private JButton testButton;
    private JTextField cmdLnArg;
    private JTextField expectedOutput;
    private JScrollPane outputScroller;
    private JTextArea outputText;
    private JTextField compilePathTextField;
    private JTextField mainClassNameTextField;

    public FunctionsPanel(Frame frame) {
        this.frame = frame;
        this.compilePathDirectory = "";
        this.sourceCodeDirectory = "";
        initComponents();
        this.setLayout(null); // yolo  ¯\_(ツ)_/¯ 
        this.setVisible(true);
    }

    private void initComponents() {
        this.backButton = new JButton();
        this.compilePathButton = new JButton();
        this.sourceDirectoryButton = new JButton();
        this.sourceDirectoryTextField = new JTextField();
        this.jLabel1 = new JLabel();
        this.compileButton = new JButton();
        this.testButton = new JButton();
        this.cmdLnArg = new JTextField();
        this.expectedOutput = new JTextField();
        this.outputText = new JTextArea();
        this.outputScroller = new JScrollPane(outputText);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel1.setText("Single Tester");
        jLabel1.setBounds(frame.WIDTH / 2 - 70, 10, 200, 40);
        this.add(jLabel1);

        backButton.setText("<- Back");
        backButton.setBounds(10, 20, 100, 30);
        this.add(backButton);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        sourceDirectoryButton.setText("Source Directory");
        sourceDirectoryButton.setBounds(10, 100, 150, 30);
        this.add(sourceDirectoryButton);
        sourceDirectoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceDirectoryActionPerformed(evt);
            }
        });

        sourceDirectoryTextField = new JTextField();
        sourceDirectoryTextField.setBounds(250, 100, 250, 30);
        sourceDirectoryTextField.setEditable(false);
        sourceDirectoryTextField.setText("Location of parent directory holding all students source codes");
        this.add(sourceDirectoryTextField);

        compilePathButton.setText("Compile Path..");
        compilePathButton.setBounds(10, 200, 100, 30);
        this.add(compilePathButton);
        compilePathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compilePathActionPerformed(evt);
            }
        });

        compilePathTextField = new JTextField();
        compilePathTextField.setBounds(125, 200, 200, 30);
        compilePathTextField.setEditable(false);
        compilePathTextField.setText("directory to compile into");
        this.add(compilePathTextField);

        cmdLnArg.setText("Command Line Arguments");
        cmdLnArg.setBounds(10, 250, 300, 30);
        cmdLnArg.setEnabled(false);
        this.add(cmdLnArg);

        //expectedOutput.setText("Expected Output");
        expectedOutput.setText("n = 1; range = 1; average = 1.0; stdDev = 0.0");
        expectedOutput.setBounds(10, 300, 300, 30);
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
        testButton.setEnabled(false);
        this.add(testButton);
        testButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runTestActionPerformed(evt);
            }
        });
        outputText.setFont(new java.awt.Font("Tahoma", 0, 14));
        outputText.setLineWrap(true);
        outputText.setWrapStyleWord(true);
        outputText.setBounds(frame.WIDTH / 2, frame.HEIGHT / 2 - 100, (frame.WIDTH / 2) - 50, (frame.HEIGHT / 2) - 50);
        outputText.setEditable(false);
        outputScroller.setVisible(true);
        outputScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        outputScroller.setBounds(frame.WIDTH / 2, frame.HEIGHT / 2 - 100, (frame.WIDTH / 2) - 50, (frame.HEIGHT / 2) - 50);

        this.add(outputScroller);
        
        mainClassNameTextField = new JTextField("Name of java class to compile (include .java)");
        mainClassNameTextField.setBounds(10, 150, 300, 30);
        add(mainClassNameTextField);
    }

    private void backButtonActionPerformed(ActionEvent evt) {
        frame.swap(frame.batchGUI, frame.splash);
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
                boolean passed = testRunner.testJava();
                appendToTextArea(studentName + " test passed: " + passed, !passed);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // NEED TO FIX THIS!
    /*
    not being used right now
    public void readOutputFile() {
        Path file = FileSystems.getDefault().getPath("output.txt");  //Output file path - ("Whatever Folder has file", "Filename.txt")
        try (InputStream in = Files.newInputStream(file);
                BufferedReader reader
                = new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                outputText.append(line + "\n");;
            }
        } catch (IOException x) {
            //System.err.println(x); TEMP COMMENT OUT CAUSE ITS ANNOYING. NEED TO FIX THIS!
        }
    }
    */
    
    // if error, output will be red
    public void appendToTextArea(String message, boolean error) {
        if (error) {
            outputText.setForeground(Color.red);
        } else {
            outputText.setForeground(Color.black);
        }
        outputText.append(message + "\n");
    }
}
