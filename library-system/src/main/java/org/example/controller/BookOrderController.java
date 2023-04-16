//package org.example.controller;
//
//import org.example.controller.request.BookOrderEntry;
//import org.example.exception.NoSuchCopiesAvailableException;
//import org.example.exception.PaymentFailedException;
//import org.example.exception.ReservationNotAvailableException;
//import org.example.service.BookOrderService;
//import org.example.service.order.BookOrderInfo;
//import org.example.service.order.BookOrderResult;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/books")
//public class BookOrderController {
//
//    private final BookOrderService bookOrderService;
//
//    public BookOrderController(BookOrderService bookOrderService) {
//        this.bookOrderService = bookOrderService;
//    }
//
////    @PutMapping("/order")
////    public ResponseEntity<BookOrderResult> orderBook(@RequestBody BookOrderEntry entry) {
////        try {
////            BookOrderInfo bookOrderInfo = new BookOrderInfo(entry.getBookId(), entry.getCount());
////            BookOrderResult bookOrder = bookOrderService.order(bookOrderInfo);
////            return new ResponseEntity<>(bookOrder, HttpStatus.CREATED);
////        } catch (ReservationNotAvailableException | NoSuchCopiesAvailableException e) {
//////            DefaultErrorMessage response = new DefaultErrorMessage(e.getMessage());
////            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
////        } catch (PaymentFailedException e) {
////            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
//
////    @PutMapping("/order/{reservationId}")
////    public ResponseEntity<BookOrderResult> orderReservedBook(@PathVariable("reservationId") String id) {
////        try {
////            BookOrderResult bookOrder = bookOrderService.orderReserved(id);
//////            DefaultHttpResponse response = new BookOrderResponse(bookOrder.getId(), "Successfully ordered the reserved book");
////            return new ResponseEntity<>(bookOrder, HttpStatus.CREATED);
////        } catch (ReservationNotAvailableException e) {
//////            DefaultErrorMessage response = new DefaultErrorMessage(e.getMessage());
////            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
//}
