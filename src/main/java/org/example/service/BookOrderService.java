package org.example.service;

import lombok.SneakyThrows;
import org.example.dto.ReservationEntryDto;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.PaymentFailedException;
import org.example.exception.ReservationNotAvailableException;
import org.example.exception.ReservationNotFoundException;
import org.example.service.order.BookOrderResponse;

import java.util.List;

public interface BookOrderService {

    BookOrderResponse order(List<ReservationEntryDto> entries) throws BookNotFoundException, NoSuchCopiesAvailableException, ReservationNotAvailableException, PaymentFailedException;

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    BookOrderResponse orderReserved(String reservationId) throws ReservationNotFoundException, BookNotFoundException, ReservationNotAvailableException, PaymentFailedException;
}
