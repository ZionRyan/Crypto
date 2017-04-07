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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
//Dies ist die Klasse in der die Verschlüsselung stadt findet!
//Diese Klasse ist Teilweise aus dem Internet.
//Quelle: http://javapapers.com/java/java-file-encryption-decryption-using-aes-password-based-encryption-pbe/

public class AESFileEncryption {

    public static void AESFileEncryption(String Datei, String PfadEN, String format) throws Exception {
        // Datei die verschlüsselt werden soll
        FileInputStream inFile = new FileInputStream(Datei);

        // verschlüsselte Datei
        FileOutputStream outFile = new FileOutputStream(PfadEN + "\\" + format + "_encryptedfile.des");

        // passwort umd die datei zu verschlüsseln
        String password = "javapapers";

        //Salt-Schlüssel wird erstellt
        byte[] salt = new byte[8];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        FileOutputStream saltOutFile = new FileOutputStream(PfadEN + "\\Schlüssel_salt.enc");
        saltOutFile.write(salt);
        saltOutFile.close();

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKey secretKey = factory.generateSecret(keySpec);
        SecretKey secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        AlgorithmParameters params = cipher.getParameters();

        //Iv-Schlüssel wird erstellt
        FileOutputStream ivOutFile = new FileOutputStream(PfadEN + "\\Schlüssel_iv.enc");
        byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
        ivOutFile.write(iv);
        ivOutFile.close();

        // Datei wird verschlüsselt
        byte[] input = new byte[64];
        int bytesRead;

        while ((bytesRead = inFile.read(input)) != -1) {
            byte[] output = cipher.update(input, 0, bytesRead);
            if (output != null) {
                outFile.write(output);
            }
        }

        byte[] output = cipher.doFinal();
        if (output != null) {
            outFile.write(output);
        }

        inFile.close();
        outFile.flush();
        outFile.close();

    }
}
