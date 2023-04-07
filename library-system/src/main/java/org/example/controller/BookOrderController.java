package org.example.controller;

import org.example.controller.response.BookOrderResponse;
import org.example.controller.response.DefaultErrorMessage;
import org.example.controller.response.DefaultHttpResponse;
import org.example.entity.BookOrder;
import org.example.entity.Reservation;
import org.example.exception.BookNotFoundException;
import org.example.exception.ReservationNotAvailableException;
import org.example.exception.ReservationNotFoundException;
import org.example.service.BookOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookOrderController {
    BookOrderService bookOrderService;

    public BookOrderController(BookOrderService bookOrderService) {
        this.bookOrderService = bookOrderService;
    }

    @PutMapping("/order/{reservationId}")
    public ResponseEntity<DefaultHttpResponse> orderReservedBook(@PathVariable("reservationId") String id) {
        try {
            BookOrder bookOrder = bookOrderService.orderReserved(id);
            System.out.println(bookOrder);
            DefaultHttpResponse response = new BookOrderResponse(bookOrder.getId(), "Successfully ordered the reserved book");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ReservationNotFoundException | ReservationNotAvailableException | BookNotFoundException e) {
            DefaultErrorMessage response = new DefaultErrorMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
