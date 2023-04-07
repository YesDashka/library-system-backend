package org.example.controller.response;

import org.example.entity.Reservation;

public class BookOrderResponse implements DefaultHttpResponse{
    private String reservationId;
    private String message;

    public BookOrderResponse() {
    }

    public BookOrderResponse(String reservationId, String message) {
        this.reservationId = reservationId;
        this.message = message;
    }

    public String  getReservation() {
        return reservationId;
    }

    public String getMessage() {
        return message;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
