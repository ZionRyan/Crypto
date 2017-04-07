package CryptoTool;
// Diese Klasse wird benutzt um einen Teil eines Strings in einen neuen String zu Speichern.

class StringUtils {

    /**
     * Hier wird der Pfad der Datei angesehen die Verschlüsselt werden soll und
     * die Zeichen nach dem letzten Punkt werden in einen neuen String
     * gespeichert. Bps. C:\\Users\\Ryan\\Desktop\\Beispiel.txt = txt Dies wird
     * benötigt damit das Dateiformat im Verschlüsselungsprozess nicht verloren
     * geht.
     */
    public static String substringAfter(String Datei, String delimiter) {
        int pos = Datei.lastIndexOf(delimiter);

        return pos >= 0 ? Datei.substring(pos + delimiter.length()) : "";

    }

    /**
     * Hier wird der Pfad der Datei angesehen die Entschlüsselt werden soll und
     * die Zeichen zwieschen dem letzen \\ und dem ersten _ werden in einen
     * neuen String gespeichert. Dies wird benötigt um das Dateinformat der
     * Entschlüsselten Datei zu bestimmen.
     */
    public static String substringBefor(String Datei, String delimiter) {
        int pos = Datei.lastIndexOf(delimiter);

        return pos >= 0 ? Datei.substring(Datei.lastIndexOf("\\") + 1, pos) : "";
    }
}
