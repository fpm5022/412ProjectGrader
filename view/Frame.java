package view;

import controller.XMLReader;
import controller.XMLSaver;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.XMLObject;

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
        initializeFrameSettings();
        XMLObject xmlObject = initializeXMLObject();
        initializeAndAddBatchGUI(xmlObject);
    }
    
    /**
     * Creates an instance of batch gui and adds it to the frame
     * @param xmlObject 
     */
    private void initializeAndAddBatchGUI(XMLObject xmlObject) {
        this.batchGUI = new BatchGUI(this, xmlObject);
        add(batchGUI);
        validate();
        repaint();
    }
    
    /**
     * Initialize the xml reader / saver and load saved settings into the xml object
     * 
     * will return an instance of XMLObject containing all of the previously saved settings
     */
    private XMLObject initializeXMLObject() {
        String xmlFileLocation = new File("").getAbsolutePath();
        this.xmlReader = new XMLReader(xmlFileLocation + "/paths.xml");
        XMLObject xmlObject = xmlReader.getObject();
        this.xmlSaver = new XMLSaver(xmlFileLocation, xmlObject);
        Runtime.getRuntime().addShutdownHook(new Thread(this.xmlSaver));
        
        return xmlObject;
    }
    
    /**
     * Sets settings on the frame like size etc.
     */
    private void initializeFrameSettings() {
        setLayoutFeel("Nimbus");
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    /**
     * Swaps out what panel is visible on the frame
     * @param remove
     * @param add 
     */
    public void swap(JPanel remove, JPanel add) {
        this.remove(remove);
        this.add(add);
        this.revalidate();
        this.repaint();
    }
    
    /**
     * Changes the swing look and feel, falls back to cross platform
     * @param s 
     */
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

    void showPopup(String message) {
        JTextArea area = new JTextArea(message);
        area.setColumns(50);
        area.setLineWrap( true );
        area.setWrapStyleWord( true );
        area.setEditable(false);
        JOptionPane.showMessageDialog(this, area, "Actual Output", 1);
    }
}
