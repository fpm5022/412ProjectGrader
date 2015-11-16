/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author fpm5022
 */
public class StudentFileChooser extends JFileChooser {
    
    public StudentFileChooser() {
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.setDialogType(JFileChooser.OPEN_DIALOG);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        this.setFileFilter(filter);
        this.setDialogTitle("Please select the text file containing students");
    }
    
    /**
     * can override this to change where on the screen the chooser is placed
     * @param parent
     * @return
     * @throws HeadlessException 
     */
    /*
    protected JDialog createDialog(Component parent)
                throws HeadlessException {
            JDialog dlg = super.createDialog(parent);
            dlg.setLocation(20, 20);
            return dlg;
        }
        */
}
