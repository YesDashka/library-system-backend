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

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
class ReservationEntryService {

    private final BookRepository bookRepository;
    private final ReserveBookRepository reserveBookRepository;

    public ReservationEntryService(BookRepository bookRepository, ReserveBookRepository reserveBookRepository) {
        this.bookRepository = bookRepository;
        this.reserveBookRepository = reserveBookRepository;
    }

    @Transactional
    public Reservation createNewReservation(Map<Long, Integer> booksCount) throws BookNotFoundException, NoSuchCopiesAvailableException {
        Reservation reservation = Reservation.newReservation(booksCount);
        return updateReservation(reservation, ReservationStatus.RESERVED);

    }

    @Transactional
    public Reservation updateReservation(Reservation reservation, ReservationStatus newStatus) throws NoSuchCopiesAvailableException, BookNotFoundException {
        Map<Long, Integer> booksCount = reservation.getBooksCount();
        Map<Long, Book> booksMap = bookRepository.findAllByIdIn(booksCount.keySet())
                .stream()
                .collect(Collectors.toMap(
                        Book::getId,
                        Function.identity()
                        )
                );
        for(Map.Entry<Long, Integer> entry: booksCount.entrySet()) {
            long bookId = entry.getKey();
            int count = entry.getValue();
            Book book = booksMap.get(bookId);
            if(book == null) {
                throw new BookNotFoundException(bookId);
            }
            final int copiesAvailable = book.getCopiesAvailable();
            int reservationCopies = switch (reservation.getStatus()) {
                case NOT_RESERVED -> -count;
                case EXPIRED, CANCELLED -> count;
                default -> 0;
            };
            int copies = copiesAvailable + reservationCopies;
            if (copies < 0) {
                throw new NoSuchCopiesAvailableException("No such copies available: missing %d books"
                        .formatted(Math.abs(copies)));
            }
            bookRepository.updateCopiesAvailable(book.getId(), copies);
        }
//        if (reservation.getStatus() == newStatus) {
//            return reservation;
//        }
//        if (reservation.getStatus().isMoreThan(newStatus)) {
//            throw new UnsupportedOperationException("Can't update reservation %s to previous %s stage.".formatted(reservation.getStatus(), newStatus));
//        }

        Reservation newReservation = Reservation.factory(reservation, newStatus);
        reserveBookRepository.save(newReservation);
        return newReservation;
    }

}
