
import javax.swing.SwingUtilities;

/**
@author Feek <feek@psu.edu>
**/

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame frame = new Frame();
            }
        });
    }
}
