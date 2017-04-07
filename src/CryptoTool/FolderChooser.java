package CryptoTool;

import javax.swing.JComponent;
import javax.swing.JFileChooser;

/**
 *
 * @author Ryan Diese Klasse ermöglicht es in einem Fenster einen Ordner
 * auszuwählen.
 */
public class FolderChooser extends JComponent {

    //Diese Methode startet Das Fenster, lässt den Benutzer einen Ordner auswälen und gibt den Pfad als String zurück.
    public String FolderChooser() {
        String Path = ("");
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            Path = fc.getSelectedFile().getPath();
        }
        return Path;
    }
}
