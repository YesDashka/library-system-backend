package org.example.service;

import lombok.SneakyThrows;
import org.example.entity.BookOrder;
import org.example.entity.Reservation;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.ReservationNotAvailableException;
import org.example.exception.ReservationNotFoundException;

public interface BookOrderService {

    BookOrder order(long bookId, int count) throws BookNotFoundException, NoSuchCopiesAvailableException, ReservationNotAvailableException;

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    BookOrder orderReserved(String reservationId) throws ReservationNotFoundException, BookNotFoundException, ReservationNotAvailableException;
}
