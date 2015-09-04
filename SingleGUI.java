
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
@author Feek <feek@psu.edu>
**/


public class SingleGUI extends JPanel {
    private JButton backButton;
    private JTextField classChooser;
    private JButton compilePath;
    private JButton fileChooser;
    private JLabel jLabel1;
    private JButton runCompile;
    private Frame frame;
    
    public SingleGUI(Frame frame) {
        this.frame = frame;
        this.setSize(frame.WIDTH, frame.HEIGHT);
        initComponents();
        this.setLayout(null); // yolo  ¯\_(ツ)_/¯ 
        this.setVisible(true);
    }
    
    private void initComponents() {
        this.backButton = new JButton();
        this.classChooser = new JTextField();
        this.compilePath = new JButton();
        this.fileChooser = new JButton();
        this.jLabel1 = new JLabel();
        this.runCompile = new JButton();
        
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

        compilePath.setText("Compile Path..");
        compilePath.setBounds(10, 250, 100, 30);
        this.add(compilePath);
        compilePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compilePathActionPerformed(evt);
            }
        });
    }
    
    private void backButtonActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void fileChooserActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void runCompileActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void compilePathActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
