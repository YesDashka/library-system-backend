package org.example.service;

import lombok.SneakyThrows;
import org.example.entity.Reservation;
import org.example.entity.ReservationStatus;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.PaymentFailedException;
import org.example.exception.ReservationNotAvailableException;
import org.example.exception.ReservationNotFoundException;
import org.example.exception.ResponseErrorException;
import org.example.repository.ReserveBookRepository;
import org.example.service.order.BookOrderInfo;
import org.example.service.order.BookOrderRequest;
import org.example.service.order.BookOrderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookOrderServiceImpl implements BookOrderService {

//    todo move to application.properties
    private static final URI ORDER_SERVICE_URL = URI.create("http://localhost:8050/api/order");
    private final ReserveBookRepository reserveBookRepository;
    private final ReservationEntryService reservationEntryService;

    private final HttpRequestHandler requestHandler;

    private static final Logger logger = LoggerFactory.getLogger(BookOrderServiceImpl.class);

    public BookOrderServiceImpl(
            ReserveBookRepository reserveBookRepository,
            ReservationEntryService reservationEntryService,
            HttpRequestHandler requestHandler
    ) {
        this.reserveBookRepository = reserveBookRepository;
        this.reservationEntryService = reservationEntryService;
        this.requestHandler = requestHandler;
    }

    @Override
//    todo order should have many books
    public BookOrderResult order(long bookId, int count) throws NoSuchCopiesAvailableException, BookNotFoundException, PaymentFailedException {
        Reservation newReservation = reservationEntryService.createNewReservation(bookId, count);
        logger.info("create new reservation for: bookId={}, count={}, reservationStatus={}", bookId, count, newReservation.getStatus());

        BookOrderInfo orderInfo = new BookOrderInfo(bookId, count);
        BookOrderRequest request = new BookOrderRequest(List.of(orderInfo));

        try {
            BookOrderResult result = requestHandler.post(
                    request,
                    BookOrderResult.class,
                    ORDER_SERVICE_URL
            );
            logger.info("successfully create payment for: bookId={}, count={}, orderStatus={}", bookId, count, result.status());
            reservationEntryService.updateReservation(newReservation, ReservationStatus.COMMITTED);
            return result;
        } catch (ResponseErrorException e) {
            reservationEntryService.updateReservation(newReservation, ReservationStatus.ERROR);
            logger.error("exception during payment for: bookId={}, count={}, errorMessage={}", bookId, count, e.getMessage());
            throw new PaymentFailedException("payment failed");
        }
    }

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    @Override
//    todo order should have many books
    public BookOrderResult orderReserved(String reservationId) throws ReservationNotFoundException, BookNotFoundException, ReservationNotAvailableException {
        Reservation reservation = reserveBookRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));
//        todo write call to payment service here
        reservationEntryService.updateReservation(reservation, ReservationStatus.COMMITTED);
        return null;
    }

}
