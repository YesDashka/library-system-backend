package org.example.service;

import lombok.SneakyThrows;
import org.example.entity.Reservation;
import org.example.exception.BookNotAvailableException;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.ReservationNotFoundException;

public interface BookReservationService {

    Reservation reserve(long bookId, int count) throws BookNotAvailableException, NoSuchCopiesAvailableException, BookNotFoundException;

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    Reservation expireReservation(Reservation reservation) throws BookNotFoundException;

    Reservation cancelReservation(String reservationId) throws ReservationNotFoundException, BookNotAvailableException, BookNotFoundException;

}
