package org.example.controller;

import org.example.controller.response.CancelBookResponse;
import org.example.controller.response.DefaultErrorMessage;
import org.example.controller.response.DefaultHttpResponse;
import org.example.controller.response.ReserveBookResponse;
import org.example.dto.ReservationEntryDto;
import org.example.entity.Reservation;
import org.example.exception.BookNotAvailableException;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.ReservationNotFoundException;
import org.example.service.BookReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookReservationController {

    private static final Logger logger = LoggerFactory.getLogger(BookReservationController.class);
    private final BookReservationService reservationService;

    public BookReservationController(BookReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PutMapping("/reserve")
    public ResponseEntity<DefaultHttpResponse> reserveBook(
            @RequestBody List<ReservationEntryDto> entries
            ) {
        try {
            Reservation reservation = reservationService.reserve(entries);
            ReserveBookResponse reserveBookResponse = new ReserveBookResponse(reservation.getId(), "Successfully reserved books");
            return new ResponseEntity<>(reserveBookResponse, HttpStatus.OK);
        } catch (BookNotAvailableException | BookNotFoundException e) {
//            logger.warn("book not available or not found for: bookId={}, count={}, errorMessage={}", id, count, e.getMessage());
            DefaultHttpResponse response = new DefaultErrorMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoSuchCopiesAvailableException e) {
//            logger.warn("out of copies for: bookId={}, count={}, errorMessage={}", id, count, e.getMessage());
            DefaultErrorMessage response = new DefaultErrorMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{reservationId}/cancel")
    public ResponseEntity<DefaultHttpResponse> cancelReservation(
            @PathVariable("reservationId") String reservationId
    ) {
        try {
            Reservation reservation = reservationService.cancelReservation(reservationId);
            CancelBookResponse cancelBookResponse = new CancelBookResponse(reservation.getId(),
                    "Successfully cancel the reservation by id %s".formatted(reservationId));
            return new ResponseEntity<>(cancelBookResponse, HttpStatus.OK);
        } catch (ReservationNotFoundException | BookNotAvailableException | BookNotFoundException e) {
            DefaultErrorMessage response = new DefaultErrorMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
