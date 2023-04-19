package org.example.exception;

import java.io.IOException;

public class NoSuchCopiesAvailableException extends IOException {

    public NoSuchCopiesAvailableException(int copies) {
        super("No such copies available: missing %d books".formatted(Math.abs(copies)));

    }

}
