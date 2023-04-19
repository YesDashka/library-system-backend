package org.example.service;

import org.example.entity.Book;
import org.example.entity.Reservation;
import org.example.entity.ReservationEntry;
import org.example.entity.ReservationStatus;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.repository.BookRepository;
import org.example.repository.ReserveBookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public Reservation createNewReservation(List<ReservationEntry> entries) throws BookNotFoundException, NoSuchCopiesAvailableException {
        List<ReservationEntry> zipEntries = zipEntries(entries).entrySet()
                .stream()
                .map(entry -> ReservationEntry.newEntry(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        Reservation reservation = Reservation.newReservation(zipEntries);
        return updateReservation(reservation, ReservationStatus.RESERVED);
    }

    @Transactional
    public Reservation updateReservation(Reservation reservation, ReservationStatus newStatus) throws NoSuchCopiesAvailableException, BookNotFoundException {
        Map<String, Integer> booksCount = zipEntries(reservation.getEntries());
        Map<String, Book> booksMap = bookRepository.findAllByIdIn(booksCount.keySet())
                .stream()
                .collect(Collectors.toMap(Book::getId, Function.identity()));

        for (Map.Entry<String, Integer> entry : booksCount.entrySet()) {
            String bookId = entry.getKey();
            int count = entry.getValue();
            Book book = booksMap.get(bookId);
            int copies = evaluateCopies(newStatus, count, book);
            bookRepository.updateCopiesAvailable(bookId, copies);
        }
        Reservation newReservation = Reservation.factory(reservation, newStatus);
        reserveBookRepository.save(newReservation);
        return newReservation;
    }

    private static int evaluateCopies(ReservationStatus newStatus, int count, Book book) throws NoSuchCopiesAvailableException {
        final int copiesAvailable = book.getCopiesAvailable();
        int reservationCopies = switch (newStatus) {
            case RESERVED -> -count;
            case EXPIRED, CANCELLED, ERROR -> count;
            case NOT_RESERVED, COMMITTED -> 0;
        };
        int copies = copiesAvailable + reservationCopies;
        if (copies < 0) {
            throw new NoSuchCopiesAvailableException(copies);
        }
        return copies;
    }

    //group by bookId and find general count of certain book
    private static Map<String, Integer> zipEntries(List<ReservationEntry> entries) {
        return entries.stream().collect(Collectors.groupingBy(
                ReservationEntry::getBookId,
                Collectors.summingInt(ReservationEntry::getCount))
        );
    }

}
