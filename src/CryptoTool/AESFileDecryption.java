package CryptoTool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
//Dies ist die Klasse in der die Entschlüsselung stadt findet!
//Diese Klasse ist Teilweise aus dem Internet.
//Quelle: http://javapapers.com/java/java-file-encryption-decryption-using-aes-password-based-encryption-pbe/

public class AESFileDecryption {

    public static void AESFileDecryption(String DateiDE, String SchlusselSalt, String SchlusselIv, String PfadDE, String format) throws Exception {

        String password = "javapapers";

        //Überprüft den Salt-Schlüssel
        FileInputStream saltFis = new FileInputStream(SchlusselSalt);
        byte[] salt = new byte[8];
        saltFis.read(salt);
        saltFis.close();

        // Überprüft den Iv-Schlüssel
        FileInputStream ivFis = new FileInputStream(SchlusselIv);
        byte[] iv = new byte[16];
        ivFis.read(iv);
        ivFis.close();

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKey tmp = factory.generateSecret(keySpec);
        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

        // entschlüssselt die Datei
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
        FileInputStream fis = new FileInputStream(DateiDE);
        FileOutputStream fos = new FileOutputStream(PfadDE + "\\" + "decrypted." + format);
        byte[] in = new byte[64];
        int read;
        while ((read = fis.read(in)) != -1) {
            byte[] output = cipher.update(in, 0, read);
            if (output != null) {
                fos.write(output);
            }
        }

        byte[] output = cipher.doFinal();
        if (output != null) {
            fos.write(output);
        }

        fis.close();
        fos.flush();
        fos.close();
    }
}
