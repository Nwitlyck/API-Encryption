package edu.ulatina.APIEncryption;

import edu.ulatina.APIEncryption.service.AESEncryptionDecryption;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ApiEncryptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiEncryptionApplication.class, args);
    }

}
