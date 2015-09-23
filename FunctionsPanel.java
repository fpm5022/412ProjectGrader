
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FunctionsPanel extends JPanel {

    private Frame frame;
    private String compilePathDirectory;
    private String sourceCodeDirectory;
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
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
            
            System.out.println(sourceCodeDirectory);
        }
    }

    /*
    The action listener when the compile button is pressed
    */
    private void runCompileActionPerformed(ActionEvent evt) {
        String commandLineArguments = cmdLnArg.getText();
        String expectedTestOutput = expectedOutput.getText();
        int runNumber = 1;
       
        String outputFileName = "output.txt";
        String mainClassName = "ArrayLoops.java"; // TO DO: pull from class to compile
        
        // loop through selected students and start compiling
        for (Student s : frame.batchGUI.getSelectedStudents()) {
            String studentName = s.getName();
            String compilePath = compilePathDirectory + studentName; // directory to compile into
            String sourcePath = sourceCodeDirectory + File.separator + studentName + File.separator + mainClassName; // what to compile
            
            Compiler compiler = new Compiler(compilePathDirectory, compilePath, sourcePath);
            int success = compiler.compileJava();

            if (success != 0) {
                System.err.println("compile failed: " + success);
                readOutputFile();
                outputText.setForeground(Color.red);
            } else {
                System.out.println("compile success");
                readOutputFile();
                outputText.setForeground(Color.black);
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
            System.err.println(x);
        }
    }
}
