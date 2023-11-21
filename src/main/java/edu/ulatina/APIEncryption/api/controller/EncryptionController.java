package edu.ulatina.APIEncryption.api.controller;

import edu.ulatina.APIEncryption.api.model.EncrypResponse;
import edu.ulatina.APIEncryption.api.model.ErrorResponse;
import edu.ulatina.APIEncryption.service.AESEncryptionDecryption;
import org.springframework.beans.factory.annotation.Autowired;
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
    public EncrypResponse getEncryp(@RequestParam String text) {
        var response = new EncrypResponse();
        var errorResponse = new ErrorResponse();
        try {
            var futureResponse = aes.encrypt(text);

            while (!futureResponse.isDone()) {
                TimeUnit.MILLISECONDS.sleep(100);
            }

            response.setEncryp(futureResponse.get());

            errorResponse.setErrorMessage("Contraseña encriptada exitosamente");
            errorResponse.setErrorCode(0);
        } catch (Exception e) {
            response.setEncryp("");
            errorResponse.setErrorMessage("Error al encriptar texto");
            errorResponse.setErrorCode(-1);
        }
        response.setErrorResponse(errorResponse);
        return response;
    }

    @GetMapping("/dencryp")
    public String getDencryp(@RequestParam String s) {
        try {
            Future<String> futureResponse = aes.decrypt(s);

            while (!futureResponse.isDone()) {
                TimeUnit.SECONDS.sleep(1);
            }
            return futureResponse.get();

        } catch (Exception e) {
            return "";
        }
    }
}
