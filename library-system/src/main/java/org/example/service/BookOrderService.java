package org.example.service;

import lombok.SneakyThrows;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.ReservationNotFoundException;

public interface BookOrderService {

    int order(long bookId, int count) throws BookNotFoundException, NoSuchCopiesAvailableException;

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    int order(long reservationId) throws ReservationNotFoundException, BookNotFoundException;
}
