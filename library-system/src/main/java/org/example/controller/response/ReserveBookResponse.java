package org.example.controller.response;

public class ReserveBookResponse implements DefaultHttpResponse{
    protected String reservationId;
    protected String message;

    public ReserveBookResponse() {
    }

    public ReserveBookResponse(String reservationId, String message) {
        this.reservationId = reservationId;
        this.message = message;
    }

    public ReserveBookResponse(String message) {
        this.message = message;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
