package org.example.controller.response;

public class DefaultErrorMessage implements DefaultHttpResponse {

    private final String message;

    public DefaultErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
