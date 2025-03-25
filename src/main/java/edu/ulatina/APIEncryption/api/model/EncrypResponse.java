package edu.ulatina.APIEncryption.api.model;

public class EncrypResponse {
    private String text;
    private ErrorResponse errorResponse;

    public EncrypResponse() {
    }

    public EncrypResponse(String encryp, ErrorResponse errorResponse) {
        this.text = encryp;
        this.errorResponse = errorResponse;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
