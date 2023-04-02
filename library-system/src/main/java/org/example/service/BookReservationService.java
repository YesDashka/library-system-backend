package org.example.service;

import org.example.exception.BookNotAvailableException;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.ReservationNotFoundException;

public interface BookReservationService {

    int reserve(long id, int count) throws BookNotAvailableException, NoSuchCopiesAvailableException, BookNotFoundException;

    int cancelReservation(long reservationId) throws ReservationNotFoundException;
}
