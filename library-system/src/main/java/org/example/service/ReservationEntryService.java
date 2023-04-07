package org.example.service;

import org.example.entity.Book;
import org.example.entity.Reservation;
import org.example.entity.ReservationStatus;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.repository.BookRepository;
import org.example.repository.ReserveBookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
class ReservationEntryService {

    private final BookRepository bookRepository;
    private final ReserveBookRepository reserveBookRepository;

    public ReservationEntryService(BookRepository bookRepository, ReserveBookRepository reserveBookRepository) {
        this.bookRepository = bookRepository;
        this.reserveBookRepository = reserveBookRepository;
    }

    @Transactional
    public Reservation updateReservation(Reservation reservation, ReservationStatus newStatus) throws NoSuchCopiesAvailableException, BookNotFoundException {
        Book book = bookRepository.findById(reservation.getBookId()).orElseThrow(BookNotFoundException::new);
        if (reservation.getStatus() == newStatus) {
            return reservation;
        }
        if (reservation.getStatus().isMoreThan(newStatus)) {
            throw new UnsupportedOperationException("Can't update reservation %s to previous %s stage.".formatted(reservation.getStatus(), newStatus));
        }

        final int copiesAvailable = book.getCopiesAvailable();
        int reservationCopies = switch (reservation.getStatus()) {
            case NOT_RESERVED -> -reservation.getCount();
            case EXPIRED, CANCELLED -> reservation.getCount();
            default -> 0;
        };
        int copies = copiesAvailable + reservationCopies;
        if (copies < 0) {
            throw new NoSuchCopiesAvailableException("No such copies available: missing %d books"
                    .formatted(Math.abs(copies)));
        }
        Reservation newReservation = Reservation.factory(reservation, newStatus);

        bookRepository.updateCopiesAvailable(book.getId(), copies);
        reserveBookRepository.save(newReservation);
        return newReservation;
    }

}
