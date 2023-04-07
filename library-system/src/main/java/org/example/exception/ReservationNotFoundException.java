package org.example.exception;

import java.io.IOException;

public class ReservationNotFoundException extends IOException {

    public ReservationNotFoundException() {
        super("Reservation not found");
    }

}
