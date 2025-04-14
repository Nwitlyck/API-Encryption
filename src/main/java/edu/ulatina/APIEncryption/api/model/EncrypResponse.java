package edu.ulatina.APIEncryption.api.model;

public class EncrypResponse {
    private String text;

    public EncrypResponse(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
