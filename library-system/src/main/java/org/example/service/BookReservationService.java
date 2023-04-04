package org.example.service;

import org.example.entity.Book;
import org.example.entity.Reservation;
import org.example.exception.BookNotAvailableException;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.ReservationNotFoundException;

public interface BookReservationService {

    int reserve(long bookId, int count) throws BookNotAvailableException, NoSuchCopiesAvailableException, BookNotFoundException;

    int cancelReservation(long reservationId) throws ReservationNotFoundException, BookNotAvailableException;

    int expireReservation(Reservation reservation, Book book);
}
