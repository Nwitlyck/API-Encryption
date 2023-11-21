package edu.ulatina.APIEncryption.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.*;

@Service
public class AESEncryptionDecryption{

    private SecretKeySpec secretKey;
    private final String ALGORITHM = "AES";

    @Async
    private void prepareSecreteKey() throws Exception {
        String myKey = "electrotrucha";
        byte[] key;
        MessageDigest sha = null;
        key = myKey.getBytes(StandardCharsets.UTF_8);
        sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        secretKey = new SecretKeySpec(key, ALGORITHM);
    }

    @Async
    public Future<String> encrypt(String strToEncrypt) throws Exception {
        prepareSecreteKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        String encrypText = Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        return Executors.newSingleThreadExecutor().submit(() -> {return encrypText;});
    }

    @Async
    public Future<String> decrypt(String strToDecrypt) throws Exception {
        prepareSecreteKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        String dencrypText = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        return Executors.newSingleThreadExecutor().submit(() -> {return dencrypText;});
    }

}
