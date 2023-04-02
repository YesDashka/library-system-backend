package org.example.controller;

import org.example.controller.response.ReserveBookResponse;
import org.example.exception.BookNotAvailableException;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
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
            int reserve = reservationService.reserve(id, count);
            ReserveBookResponse response = new ReserveBookResponse(reserve, "Successfully reserved the book by id %d".formatted(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BookNotAvailableException | BookNotFoundException e) {
            ReserveBookResponse response = new ReserveBookResponse(0, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoSuchCopiesAvailableException e) {
            ReserveBookResponse response = new ReserveBookResponse(e.getCopiesLeft(), e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{reservationId}/cancel")
    public ResponseEntity<ReserveBookResponse> cancelReservation(
            @PathVariable("reservationId") long reservationId,
            @RequestParam(value = "count", defaultValue = "1") int count
    ) {
        return ResponseEntity.ok(null);
    }
}
