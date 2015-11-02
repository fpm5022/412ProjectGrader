package view;


import controller.XMLReader;
import controller.XMLSaver;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
@author Feek <feek@psu.edu>
**/

public class Frame extends JFrame {
    
    public final int WIDTH = 1080;
    public final int HEIGHT = 720;
    public BatchGUI batchGUI;
    public XMLSaver xmlSaver;
    public XMLReader xmlReader;
    
    public Frame() {
        super("Project Grader");
        
        setLayoutFeel("Nimbus");
        
        setLayout(null);
        
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        this.batchGUI = new BatchGUI(this);
        add(batchGUI);
        
        validate();
        repaint();
        
        this.xmlReader = new XMLReader("/Users/Feek/Desktop/paths.xml");// temp, get from runtime / classpath
        xmlReader.getObject();
        this.xmlSaver = new XMLSaver("/Users/Feek/Desktop"); // temp, get from runtime / classpath
        Runtime.getRuntime().addShutdownHook(new Thread(this.xmlSaver));
    }
    
    public void swap(JPanel remove, JPanel add) {
        this.remove(remove);
        this.add(add);
        this.revalidate();
        this.repaint();
    }
    
    private void setLayoutFeel(String s) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (s.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // If Nimbus is not available, fall back to cross-platform
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            }
        }
    }
}
