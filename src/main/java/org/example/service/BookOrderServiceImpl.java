package org.example.service;

import lombok.SneakyThrows;
import org.example.dto.ReservationEntryDto;
import org.example.entity.Reservation;
import org.example.entity.ReservationEntry;
import org.example.entity.ReservationStatus;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.PaymentFailedException;
import org.example.exception.ReservationNotFoundException;
import org.example.exception.ResponseErrorException;
import org.example.repository.ReserveBookRepository;
import org.example.service.order.BookOrderRequest;
import org.example.service.order.BookOrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
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
            ReserveBookRepository reserveBookRepository, ReservationEntryService reservationEntryService,
            HttpRequestHandler requestHandler
    ) {
        this.reserveBookRepository = reserveBookRepository;
        this.reservationEntryService = reservationEntryService;
        this.requestHandler = requestHandler;
    }

    @Override
    public BookOrderResponse order(List<ReservationEntryDto> dtoEntries) throws NoSuchCopiesAvailableException, BookNotFoundException, PaymentFailedException {
        List<ReservationEntry> reservationEntries = ReservationEntryDto.convertToReservationEntry(dtoEntries);
        Reservation newReservation = reservationEntryService.createNewReservation(reservationEntries);
        return sendOrder(dtoEntries, newReservation);
    }

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    @Override
    public BookOrderResponse orderReserved(String reservationId) throws ReservationNotFoundException, BookNotFoundException, PaymentFailedException {
        Reservation reservation = reserveBookRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));
        List<ReservationEntryDto> dtoEntries = ReservationEntryDto
                .convertToReservationEntryDto(reservation.getEntries());
        return sendOrder(dtoEntries, reservation);
    }

    private BookOrderResponse sendOrder(List<ReservationEntryDto> dtoEntries, Reservation newReservation) throws NoSuchCopiesAvailableException, PaymentFailedException {
        //create a bookOrderRequest obj to represent the entire order
        BookOrderRequest request = BookOrderRequest.newRequest(dtoEntries);

        try {
            //send the order request to the service and get the result
            BookOrderResponse result = requestHandler.post(
                    request,
                    BookOrderResponse.class,
                    ORDER_SERVICE_URL
            );
            logger.info("successfully create payment for order with such bookOrderInfos: {}", dtoEntries);

            //update the reservations to assign that the order has been committed
            reservationEntryService.updateReservation(newReservation, ReservationStatus.COMMITTED);
            return result;
        } catch (ResponseErrorException e) {
            reservationEntryService.updateReservation(newReservation, ReservationStatus.ERROR);
            logger.error("exception during payment for order with bookOrderInfos: {}", dtoEntries, e);
            throw new PaymentFailedException("payment failed");
        }
    }

}
