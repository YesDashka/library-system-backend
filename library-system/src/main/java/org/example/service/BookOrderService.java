package org.example.service;

import lombok.SneakyThrows;
import org.example.entity.Reservation;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.ReservationNotAvailableException;
import org.example.exception.ReservationNotFoundException;

public interface BookOrderService {

    Reservation order(long bookId, int count) throws BookNotFoundException, NoSuchCopiesAvailableException;

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    Reservation orderReserved(String reservationId) throws ReservationNotFoundException, BookNotFoundException, ReservationNotAvailableException;
}
