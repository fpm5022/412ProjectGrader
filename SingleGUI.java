
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
    private JTextField classChooser;
    private JButton compilePath;
    private JTextField compilePathTextField;
    private JButton fileChooser;
    private JLabel jLabel1;
    private JButton runCompile;
    private Frame frame;
    private JTextArea outputText;
    private JScrollPane outputScroller;
    private BufferedReader reader;
    private File outputFile;
    
    
    // fields
    private String compilePathDirectory;
    private JTextField cmdLnArg;
    private JTextField expectedOutput;
    
    
    public SingleGUI(Frame frame) {
        this.frame = frame;
        this.compilePathDirectory = "";
        this.setSize(frame.WIDTH, frame.HEIGHT);
        initComponents();
        this.setLayout(null); // yolo  Â¯\_(ãƒ„)_/Â¯ 
        this.setVisible(true);
    }
    
    private void initComponents() {
        this.backButton = new JButton();
        this.classChooser = new JTextField();
        this.compilePath = new JButton();
        this.fileChooser = new JButton();
        this.jLabel1 = new JLabel();
        this.runCompile = new JButton();
        this.cmdLnArg = new JTextField();
        this.expectedOutput = new JTextField();
        this.outputScroller = new JScrollPane(outputText);
        this.outputText = new JTextArea();
        
        
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Single Tester");
        jLabel1.setBounds(10, 10, 200, 40);
        this.add(jLabel1);

        backButton.setText("<- Back");
        backButton.setBounds(10, 50, 100, 30);
        this.add(backButton);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        fileChooser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fileChooser.setText("Choose File");
        fileChooser.setBounds(10, 100, 100, 30);
        this.add(fileChooser);
        fileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserActionPerformed(evt);
            }
        });

        classChooser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        classChooser.setText("Class Name");
        classChooser.setBounds(10, 150, 100, 30);
        this.add(classChooser);

        runCompile.setText("Compile");
        runCompile.setBounds(10, 200, 100, 30);
        this.add(runCompile);
        runCompile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runCompileActionPerformed(evt); 
            }
        });
        
        outputText.setFont(new java.awt.Font("Tahoma", 0, 14));
        outputText.setLineWrap(true);
        outputText.setWrapStyleWord(true);
        outputText.setBounds(200, 300, 800, 300);
        outputText.setEditable(false);
        outputScroller.setVisible(true);
        outputScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(outputText);
        this.add(outputScroller);
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
        
        
        compilePath.setText("Compile Path..");
        compilePath.setBounds(10, 250, 100, 30);
        this.add(compilePath);
        compilePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compilePathActionPerformed(evt);
            }
        });
        
        compilePathTextField = new JTextField();
        compilePathTextField.setBounds(125, 250, 300, 30);
        compilePathTextField.setEditable(false);
        this.add(compilePathTextField);
        
        cmdLnArg.setText("Command Line Arguments");
        cmdLnArg.setBounds(10, 300, 300, 30);
        this.add(cmdLnArg);
        
        expectedOutput.setText("Expected Output");
        expectedOutput.setBounds(10, 350, 300, 30);
        this.add(expectedOutput);
    }
    
    private void backButtonActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void fileChooserActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void runCompileActionPerformed(ActionEvent evt) {
        String commandLineArguments = cmdLnArg.getText();
        String expectedTestOutput = expectedOutput.getText();
        String className = classChooser.getText();
        
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
