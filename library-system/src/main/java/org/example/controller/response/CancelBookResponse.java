package org.example.controller.response;
public class CancelBookResponse implements DefaultHttpResponse {
    protected String reservationId;
    protected String message;

    public CancelBookResponse() {
    }

    public CancelBookResponse(String message) {
        this.message = message;
    }

    public CancelBookResponse(String reservationId, String message) {
        this.reservationId = reservationId;
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
