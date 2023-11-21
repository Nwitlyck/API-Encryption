package edu.ulatina.APIEncryption.api.model;

public class EncrypResponse {
    private String encryp;
    private ErrorResponse errorResponse;

    public EncrypResponse() {
    }

    public EncrypResponse(String encryp, ErrorResponse errorResponse) {
        this.encryp = encryp;
        this.errorResponse = errorResponse;
    }

    public String getEncryp() {
        return encryp;
    }

    public void setEncryp(String encryp) {
        this.encryp = encryp;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
