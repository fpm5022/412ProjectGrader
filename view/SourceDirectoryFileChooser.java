/**
@author Feek <feek@psu.edu>
**/

package view;

import javax.swing.JFileChooser;

public class SourceDirectoryFileChooser extends JFileChooser {
    public SourceDirectoryFileChooser() {
        setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        setDialogType(JFileChooser.OPEN_DIALOG);
        setDialogTitle("Please select the directory containing all students source codes");
    }
}
