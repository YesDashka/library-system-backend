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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookOrderServiceImpl implements BookOrderService {
    @Value("${order.service.url}")
    private URI ORDER_SERVICE_URL;
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
    public BookOrderResult order(BookOrderInfo... bookOrderInfos) throws NoSuchCopiesAvailableException, BookNotFoundException, PaymentFailedException {
        List<Reservation> reservations = new ArrayList<>();
        for(BookOrderInfo bookOrderInfo: bookOrderInfos) {
            Reservation newReservation = reservationEntryService.createNewReservation(bookOrderInfo.getBookId(), bookOrderInfo.getCount());
            logger.info("create new reservation for: bookId={}, count={}, reservationStatus={}", bookOrderInfo.getBookId(),  bookOrderInfo.getCount(), newReservation.getStatus());
            reservations.add(newReservation);
        }

        //create a bookOrderRequest obj to represent the entire order
        BookOrderRequest request = new BookOrderRequest(Arrays.asList(bookOrderInfos));

        try {
            //send the order request to the service and get the result
            BookOrderResult result = requestHandler.post(
                    request,
                    BookOrderResult.class,
                    ORDER_SERVICE_URL
            );
            logger.info("successfully create payment for order with such bookOrderInfos: {}", Arrays.toString(bookOrderInfos));

            //update the reservations to assign that the order has been committed
            for (Reservation newReservation: reservations) {
                reservationEntryService.updateReservation(newReservation, ReservationStatus.COMMITTED);
            }
            return result;
        } catch (ResponseErrorException e) {
            for (Reservation newReservation: reservations) {
                reservationEntryService.updateReservation(newReservation, ReservationStatus.ERROR);
            }
            logger.error("exception during payment for order with bookOrderInfos: {}", Arrays.toString(bookOrderInfos), e);
            throw new PaymentFailedException("payment failed");
        }
    }

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    @Override
//    todo order should have many books
    public BookOrderResult orderReserved(String reservationId) throws ReservationNotFoundException, BookNotFoundException, ReservationNotAvailableException {
        Reservation reservation = reserveBookRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));
        List<BookOrderInfo> bookOrderInfos = new ArrayList<>();
//        todo write call to payment service here
        //not enough information, need to add a customer to finish this method
        reservationEntryService.updateReservation(reservation, ReservationStatus.COMMITTED);
        return null;
    }

}
