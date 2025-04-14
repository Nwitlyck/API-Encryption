package edu.ulatina.APIEncryption.api.controller;

import edu.ulatina.APIEncryption.api.model.EncrypResponse;
import edu.ulatina.APIEncryption.service.AESEncryptionDecryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RestController
public class EncryptionController {

    private AESEncryptionDecryption aes;

    @Autowired
    public EncryptionController(AESEncryptionDecryption aes) {
        this.aes = aes;
    }

    @GetMapping("/encryp")
    public ResponseEntity<EncrypResponse> getEncryp(@RequestParam String text) {
        try {
            var futureResponse = aes.encrypt(text);

            while (!futureResponse.isDone()) {
                TimeUnit.MILLISECONDS.sleep(100);
            }

            return new ResponseEntity<>(new EncrypResponse(futureResponse.get()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dencryp")
    public ResponseEntity<EncrypResponse> getDencryp(@RequestParam String text) {
        try {
            final Future<String> futureResponse = aes.decrypt(text);

            while (!futureResponse.isDone()) {
                TimeUnit.MILLISECONDS.sleep(100);
            }

            return new ResponseEntity<>(new EncrypResponse(futureResponse.get()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
