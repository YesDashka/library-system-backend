package org.example.exception;

import java.io.IOException;

public class BookNotFoundException extends IOException {

    public BookNotFoundException() {
        super("Book not found");
    }

}
