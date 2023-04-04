package org.example.controller.response;

public class ReserveBookResponse {
    protected int copiesLeft;
    protected String message;

    public ReserveBookResponse() {
    }

    public ReserveBookResponse(int copiesLeft, String message) {
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
