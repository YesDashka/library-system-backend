package org.example.service;

import org.example.entity.Book;
import org.example.entity.Reservation;
import org.example.entity.ReservationStatus;
import org.example.exception.BookNotAvailableException;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.ReservationNotFoundException;
import org.example.repository.BookRepository;
import org.example.repository.ReserveBookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookReservationServiceImpl implements BookReservationService{

    private final BookRepository bookRepository;
    private final ReserveBookRepository reserveBookRepository;

    public BookReservationServiceImpl(BookRepository bookRepository, ReserveBookRepository reserveBookRepository) {
        this.bookRepository = bookRepository;
        this.reserveBookRepository = reserveBookRepository;
    }

    @Override
    public int reserve(long id, int count) throws BookNotAvailableException, NoSuchCopiesAvailableException, BookNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        if (book.getCopiesAvailable() == 0) {
            throw new BookNotAvailableException("No copies available");
        }
        int copiesLeft;
        if ((copiesLeft = book.getCopiesAvailable() - count) < 0) {
            throw new NoSuchCopiesAvailableException("No such copies available: missing %d books".formatted(Math.abs(copiesLeft)), book.getCopiesAvailable());
        }
        Reservation reservation = Reservation.newReservation(id, count);
        reserveBookRepository.save(reservation);
        bookRepository.updateCopiesAvailable(book.getId(), copiesLeft);
        return copiesLeft;
    }


    @Override
    public int cancelReservation(long reservationId) throws ReservationNotFoundException {
        Reservation reservation = reserveBookRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);
        Book reservedBook = bookRepository.findById(reservation.getBookId()).get();
        int copiesLeft = reservedBook.getCopiesAvailable() + reservation.getCount();
        reserveBookRepository.updateReservationStatus(reservation.getId(), ReservationStatus.CANCELLED);
        bookRepository.updateCopiesAvailable(reservation.getBookId(), copiesLeft);

        return copiesLeft;
    }
}

