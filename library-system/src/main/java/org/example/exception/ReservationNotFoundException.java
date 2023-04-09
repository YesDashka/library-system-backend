package org.example.exception;


public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(String reservationId) {
        super("Reservation not found with id: " + reservationId);
    }

}
