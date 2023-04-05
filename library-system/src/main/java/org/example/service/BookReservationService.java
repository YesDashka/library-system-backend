package org.example.service;

import lombok.SneakyThrows;
import org.example.entity.Book;
import org.example.entity.Reservation;
import org.example.exception.BookNotAvailableException;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.ReservationNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public interface BookReservationService {

    int reserve(long bookId, int count) throws BookNotAvailableException, NoSuchCopiesAvailableException, BookNotFoundException;

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    @Transactional
    int expireReservation(Reservation reservation) throws BookNotFoundException;

    int cancelReservation(long reservationId) throws ReservationNotFoundException, BookNotAvailableException, BookNotFoundException;

}
