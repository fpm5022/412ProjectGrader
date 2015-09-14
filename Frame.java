
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
@author Feek <feek@psu.edu>
**/

public class Frame extends JFrame {
    
    public final int WIDTH = 720;
    public final int HEIGHT = 480;
    public SingleGUI singleGUI;
    public SplashScreen splash;
    public BatchGUI batchGUI;
    
    public Frame() {
        super("Project Grader");
        
        setLayoutFeel("Nimbus");
        
        setLayout(null);
        
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        this.singleGUI = new SingleGUI(this);
        this.splash = new SplashScreen(this);
        this.batchGUI = new BatchGUI(this);
        add(splash);
        
        validate();
        repaint();
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
