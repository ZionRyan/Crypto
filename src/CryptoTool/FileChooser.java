package CryptoTool;

import javax.swing.JFileChooser;
import javax.swing.JComponent;

/**
 *
 * @author Ryan Diese Klasse ermöglicht es in einem Fenster eine Datein
 * auszuwählen.
 */
public class FileChooser extends JComponent {

    //Diese Methode startet Das Fenster, lässt den Benutzer eine Datei auswälen und gibt den Pfad als String zurück.
    public String FileChooser() {
        String Path1 = "";
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            Path1 = chooser.getSelectedFile().getPath();
        }
        return Path1;
    }
}
