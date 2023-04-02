package org.example.controller.response;

public class ReserveBookResponse extends BookResponse{
    public ReserveBookResponse() {
    }

    public ReserveBookResponse(int copiesLeft, String message) {
        super(copiesLeft, message);
    }


}
