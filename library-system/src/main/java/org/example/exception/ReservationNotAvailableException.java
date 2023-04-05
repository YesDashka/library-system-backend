package org.example.exception;

import java.io.IOException;

public class ReservationNotAvailableException extends IOException {
    public ReservationNotAvailableException(String message) {
        super(message);
    }
}
