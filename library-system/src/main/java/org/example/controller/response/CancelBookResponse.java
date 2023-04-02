package org.example.controller.response;
public class CancelBookResponse extends BookResponse {
    public CancelBookResponse() {
    }

    public CancelBookResponse(int copiesLeft, String message) {
        super(copiesLeft, message);
    }
    public CancelBookResponse(String message) {
        this.message = message;
    }
}
