package org.example.service;

import lombok.SneakyThrows;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.PaymentFailedException;
import org.example.exception.ReservationNotAvailableException;
import org.example.exception.ReservationNotFoundException;
import org.example.service.order.BookOrderResult;

public interface BookOrderService {

    BookOrderResult order(long bookId, int count) throws BookNotFoundException, NoSuchCopiesAvailableException, ReservationNotAvailableException, PaymentFailedException;

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    BookOrderResult orderReserved(String reservationId) throws ReservationNotFoundException, BookNotFoundException, ReservationNotAvailableException;
}
