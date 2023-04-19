package org.example.exception;

import java.io.IOException;

public class BookNotAvailableException extends IOException {

    public BookNotAvailableException(String message) {
        super(message);
    }

}
