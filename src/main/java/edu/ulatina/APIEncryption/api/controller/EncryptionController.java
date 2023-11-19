package edu.ulatina.APIEncryption.api.controller;

import edu.ulatina.APIEncryption.api.model.EncrypResponse;
import edu.ulatina.APIEncryption.api.model.ErrorResponse;
import edu.ulatina.APIEncryption.service.AESEncryptionDecryption;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncryptionController {
    @GetMapping("/encryp")
    public EncrypResponse getEncryp(@RequestParam String text){
        EncrypResponse response = new EncrypResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        try {
            response.setEncryp(new AESEncryptionDecryption().encrypt(text));
            errorResponse.setErrorMessage("Contraseña encriptada exitosamente");
            errorResponse.setErrorCode(0);
        }
        catch (Exception e){
            response.setEncryp("");
            errorResponse.setErrorMessage("Error al encriptar texto");
            errorResponse.setErrorCode(-1);
        }
        response.setErrorResponse(errorResponse);
        return response;
    }

    @GetMapping("/dencryp")
    public String getDencryp(@RequestParam String s){
        return new AESEncryptionDecryption().decrypt(s);
    }
}
