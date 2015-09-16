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

/**
@author Feek <feek@psu.edu>
**/


public class SingleGUI extends JPanel {
    // COMPONENTS
    private JButton backButton;
    private JTextField nameOfClassTextField;
    private JButton compilePath;
    private JTextField compilePathTextField;
    private JButton classPathSelect;
    private JLabel jLabel1;
    private JButton compileButton;
    private Frame frame;
    private JTextField cmdLnArg;
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
    private JTextField classPathTextField;
    
    public SingleGUI(Frame frame) {
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
        this.compilePath = new JButton();
        this.classPathSelect = new JButton();
        this.classPathTextField = new JTextField();
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
        
        nameOfClassTextField.setText("412"); // hard coded for now
        nameOfClassTextField.setBounds(100, 150, 100, 30);
        nameOfClassTextField.setEditable(false);
        this.add(nameOfClassTextField);

        compilePath.setText("Compile Path..");
        compilePath.setBounds(10, 200, 100, 30);
        this.add(compilePath);
        compilePath.addActionListener(new java.awt.event.ActionListener() {
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
        frame.swap(this, frame.splash);
    }
    
    private void compileChooserActionPerformed(ActionEvent evt) {
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
        String commandLineArguments = cmdLnArg.getText();
        String expectedTestOutput = expectedOutput.getText();
        String className = nameOfClassTextField.getText();
        
        int runNumber = 1;
        String studentName = "";
        String studentHandle = "";
        String compilePath = compilePathDirectory + className + studentName;
        String sourcePath = classPathDirectory; // hard coded for now. #14 will fix
        String studentPath = sourcePath;
        String outputFileName = "output.txt";
        String mainClassName = "ArrayLoops.java"; // hard coded for now. #14 will fix
        
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
        } else {
            System.out.println("compile success");
            readOutputFile();
            outputText.setForeground(Color.black);
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
    
        private void classPathActionPerformed(ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setDialogTitle("Please select the class path");
        this.add(chooser);
        
        int val = chooser.showOpenDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
            this.classPathDirectory = chooser.getSelectedFile().getAbsolutePath() + File.separator; // append trailing slash
            this.classPathTextField.setText(this.classPathDirectory);
        }
    }
    
    private void runTestActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void readOutputFile(){
        Path file = FileSystems.getDefault().getPath("output.txt");  //Output file path - ("Whatever Folder has file", "Filename.txt")
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
