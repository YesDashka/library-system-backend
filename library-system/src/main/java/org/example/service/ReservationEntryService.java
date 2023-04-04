package org.example.service;

import org.example.entity.Book;
import org.example.entity.Reservation;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ReservationEntryService {

    private final BookRepository bookRepository;

    public ReservationEntryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public int commitReservation(Book book, Reservation reservation) throws NoSuchCopiesAvailableException {
        final int copiesAvailable = book.getCopiesAvailable();
        int reservationCopies = switch (reservation.getStatus()) {
            case RESERVED -> -reservation.getCount();
            case EXPIRED, CANCELLED -> reservation.getCount();
        };
        int copies = copiesAvailable - reservationCopies;
        if (copies < 0) {
            throw new NoSuchCopiesAvailableException("No such copies available: missing %d books"
                    .formatted(Math.abs(copies)), book.getCopiesAvailable());
        }
        bookRepository.updateCopiesAvailable(book.getId(), copies);
        return copies;
    }
}
