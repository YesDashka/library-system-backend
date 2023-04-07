package org.example.exception;

import java.io.IOException;

public class NoSuchCopiesAvailableException extends IOException {



    public NoSuchCopiesAvailableException(String message) {
        super(message);

    }

}
