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
        // only allow text file to be selected
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        this.setFileFilter(filter);
        this.setDialogTitle("Please select the text file containing students");
    }
        
}
