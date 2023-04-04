package org.example.controller;

import org.example.controller.response.CancelBookResponse;
import org.example.controller.response.ReserveBookResponse;
import org.example.exception.BookNotAvailableException;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.ReservationNotFoundException;
import org.example.service.BookReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookReservationController {

    private final BookReservationService reservationService;

    public BookReservationController(BookReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PutMapping("/{bookId}/reserve")
    public ResponseEntity<ReserveBookResponse> reserveBook(
            @PathVariable("bookId") long id,
            @RequestParam(value = "count", defaultValue = "1") int count
    ) {
        try {
            int copiesLeft = reservationService.reserve(id, count);
            ReserveBookResponse reserveBookResponse = new ReserveBookResponse(copiesLeft, "Successfully reserved the book by id %d".formatted(id));
            return new ResponseEntity<>(reserveBookResponse, HttpStatus.OK);
        } catch (BookNotAvailableException | BookNotFoundException e) {
            ReserveBookResponse response = new ReserveBookResponse(0, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoSuchCopiesAvailableException e) {
            ReserveBookResponse response = new ReserveBookResponse(e.getCopiesLeft(), e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{reservationId}/cancel")
    public ResponseEntity<CancelBookResponse> cancelReservation(
            @PathVariable("reservationId") long reservationId
    ) {
        try {
            int copiesLeft = reservationService.cancelReservation(reservationId);
            CancelBookResponse cancelBookResponse = new CancelBookResponse(copiesLeft,
                    "Successfully cancel the reservation by id %d".formatted(reservationId));
            return new ResponseEntity<>(cancelBookResponse, HttpStatus.OK);
        } catch (ReservationNotFoundException | BookNotAvailableException e) {
            CancelBookResponse response = new CancelBookResponse(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
