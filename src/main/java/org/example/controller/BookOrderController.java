package org.example.controller;

import org.example.controller.response.DefaultErrorMessage;
import org.example.controller.response.DefaultHttpResponse;
import org.example.dto.ReservationEntryDto;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.PaymentFailedException;
import org.example.exception.ReservationNotAvailableException;
import org.example.service.BookOrderService;
import org.example.service.order.BookOrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookOrderController {

    private final BookOrderService bookOrderService;

    public BookOrderController(BookOrderService bookOrderService) {
        this.bookOrderService = bookOrderService;
    }

    @PutMapping("/order")
    public ResponseEntity<DefaultHttpResponse> orderBook(@RequestBody List<ReservationEntryDto> entries) {
        try {
            return new ResponseEntity<>(bookOrderService.order(entries), HttpStatus.CREATED);
        } catch (ReservationNotAvailableException | NoSuchCopiesAvailableException | PaymentFailedException e) {
            DefaultErrorMessage response = new DefaultErrorMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/order/{reservationId}")
    public ResponseEntity<DefaultHttpResponse> orderReservedBook(@PathVariable("reservationId") String id) {
        try {
            BookOrderResponse bookOrder = bookOrderService.orderReserved(id);
            return new ResponseEntity<>(bookOrder, HttpStatus.CREATED);
        } catch (ReservationNotAvailableException  | PaymentFailedException e) {
            DefaultErrorMessage response = new DefaultErrorMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
