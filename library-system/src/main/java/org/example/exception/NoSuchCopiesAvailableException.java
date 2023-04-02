package org.example.exception;

import java.io.IOException;

public class NoSuchCopiesAvailableException extends IOException {

    private final int copiesLeft;

    public NoSuchCopiesAvailableException(String message, int copiesLeft) {
        super(message);
        this.copiesLeft = copiesLeft;
    }

    public int getCopiesLeft() {
        return copiesLeft;
    }
}
