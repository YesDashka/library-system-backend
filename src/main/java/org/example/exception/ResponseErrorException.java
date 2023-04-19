package org.example.exception;

import java.io.IOException;

public class ResponseErrorException extends IOException {

    public ResponseErrorException(int status) {
        super(String.valueOf(status));
    }
}
