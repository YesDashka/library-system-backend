package org.example.controller.response;
public class CancelBookResponse {
    protected int copiesLeft;
    protected String message;

    public CancelBookResponse() {
    }

    public CancelBookResponse(String message) {
        this.message = message;
    }

    public CancelBookResponse(int copiesLeft, String message) {
        this.copiesLeft = copiesLeft;
        this.message = message;
    }

    public int getCopiesLeft() {
        return copiesLeft;
    }

    public void setCopiesLeft(int copiesLeft) {
        this.copiesLeft = copiesLeft;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
