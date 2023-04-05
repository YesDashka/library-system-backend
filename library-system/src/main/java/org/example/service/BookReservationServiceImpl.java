package org.example.service;

import lombok.SneakyThrows;
import org.example.entity.Book;
import org.example.entity.Reservation;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.ReservationNotFoundException;
import org.example.repository.BookRepository;
import org.example.repository.ReserveBookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BookReservationServiceImpl implements BookReservationService {

    private final BookRepository bookRepository;
    private final ReserveBookRepository reserveBookRepository;
    private final ReservationEntryService reservationEntryService;

    public BookReservationServiceImpl(
            BookRepository bookRepository,
            ReserveBookRepository reserveBookRepository,
            ReservationEntryService reservationEntryService
    ) {
        this.bookRepository = bookRepository;
        this.reserveBookRepository = reserveBookRepository;
        this.reservationEntryService = reservationEntryService;
    }

    @Transactional
    @Override
    public int reserve(long bookId, int count) throws NoSuchCopiesAvailableException, BookNotFoundException {
        Book book = bookRepository
                .findById(bookId)
                .orElseThrow(BookNotFoundException::new);
        Reservation reservation = reserveBookRepository.save(Reservation.newReservation(bookId, count));
        return reservationEntryService.commitReservation(book, reservation);
    }

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    @Transactional
    @Override
    public int expireReservation(Reservation reservation, Book book) {
        Reservation expiredReservation = reserveBookRepository.save(Reservation.expiredReservation(reservation));
        return reservationEntryService.commitReservation(book, expiredReservation);
    }

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    @Transactional
    @Override
    public int cancelReservation(long reservationId) throws ReservationNotFoundException {
        final Reservation reservation = reserveBookRepository
                .findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);
        Reservation cancelledReservation = reserveBookRepository.save(Reservation.cancelledReservation(reservation));
        Book reservedBook = bookRepository.findById(cancelledReservation.getBookId()).get();

        return reservationEntryService.commitReservation(reservedBook, cancelledReservation);
    }
}

