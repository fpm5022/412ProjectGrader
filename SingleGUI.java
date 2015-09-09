
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    private JButton fileChooser;
    private JLabel jLabel1;
    private JButton runCompile;
    private Frame frame;
    
    // fields
    private String compilePathDirectory;
    private JTextField cmdLnArg;
    private JTextField expectedOutput;
    private JLabel nameOfClassLabel;
    
    
    public SingleGUI(Frame frame) {
        this.frame = frame;
        this.compilePathDirectory = "";
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
        this.fileChooser = new JButton();
        this.jLabel1 = new JLabel();
        this.runCompile = new JButton();
        this.cmdLnArg = new JTextField();
        this.expectedOutput = new JTextField();
        
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Single Tester");
        jLabel1.setBounds(frame.WIDTH / 2 - 70, 10, 200, 40);
        this.add(jLabel1);

        backButton.setText("<- Back");
        backButton.setBounds(10, 10, 100, 30);
        this.add(backButton);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        fileChooser.setText("Class To Compile");
        fileChooser.setBounds(10, 100, 150, 30);
        fileChooser.setEnabled(false);
        this.add(fileChooser);
        fileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserActionPerformed(evt);
            }
        });

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
        
        runCompile.setText("Compile");
        runCompile.setBounds(frame.WIDTH / 2 - 50, 350, 100, 30);
        this.add(runCompile);
        runCompile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runCompileActionPerformed(evt);
            }
        });
    }
    
    private void backButtonActionPerformed(ActionEvent evt) {
        frame.swap(this, frame.splash);
    }
    
    private void fileChooserActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void runCompileActionPerformed(ActionEvent evt) {
        String commandLineArguments = cmdLnArg.getText();
        String expectedTestOutput = expectedOutput.getText();
        String className = nameOfClassTextField.getText();
        
        int runNumber = 1;
        String studentName = "smithjq";
        String studentHandle = "fpm5022";
        String classPath = compilePathDirectory + className + studentName;
        String sourcePath = "/Users/Feek/repos/412ProjectGrader/src"; // hard coded for now. #14 will fix
        String studentPath = sourcePath + "/" + studentName;
        String outputFileName = "output.txt";
        String mainClassName = "ArrayLoops.java"; // hard coded for now. #14 will fix
        
        Compiler compiler = new Compiler(runNumber, studentName, studentHandle, compilePathDirectory, classPath, sourcePath, studentPath, outputFileName, mainClassName);
        int success = compiler.compileJava();
        
        System.out.println(compilePathDirectory);
        System.out.println(classPath);
        System.out.println(sourcePath);
        System.out.println(studentPath);
        
        if (success != 0 ) {
            System.err.println("compile failed: " + success);
        } else {
            System.out.println("compile success");
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
}
