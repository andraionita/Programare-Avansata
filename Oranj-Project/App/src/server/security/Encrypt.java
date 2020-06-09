package server.security;
/**
 * @author Ionita Andra 2A7
 * Clasa care face criptarea parolei in baza de date cu sha 256
 */

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encrypt {
    public static String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
        String encrypted = Base64.getEncoder().encodeToString(hash);
        return encrypted;
    }
}
