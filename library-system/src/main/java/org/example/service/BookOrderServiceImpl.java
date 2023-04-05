package org.example.service;

import lombok.SneakyThrows;
import org.example.entity.BookOrder;
import org.example.entity.Reservation;
import org.example.entity.ReservationStatus;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.ReservationNotFoundException;
import org.example.repository.BookOrderRepository;
import org.example.repository.BookRepository;
import org.example.repository.ReserveBookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookOrderServiceImpl implements BookOrderService{

    private final BookRepository bookRepository;
    private final BookOrderRepository bookOrderRepository;
    private final ReserveBookRepository reserveBookRepository;
    private final ReservationEntryService reservationEntryService;

    public BookOrderServiceImpl(
            BookRepository bookRepository,
            BookOrderRepository bookOrderRepository,
            ReserveBookRepository reserveBookRepository,
            ReservationEntryService reservationEntryService
    ) {
        this.bookRepository = bookRepository;
        this.bookOrderRepository = bookOrderRepository;
        this.reserveBookRepository = reserveBookRepository;
        this.reservationEntryService = reservationEntryService;
    }

    @Override
    public int order(long bookId, int count) throws BookNotFoundException, NoSuchCopiesAvailableException {
        Reservation reservation = reserveBookRepository.save(Reservation.newReservation(bookId, count));
        BookOrder bookOrder = BookOrder.newOrder(reservation.getId());
        bookOrderRepository.save(bookOrder);

        return reservationEntryService.updateReservation(reservation, ReservationStatus.COMMITTED);
    }

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    @Override
    public int order(long reservationId) throws ReservationNotFoundException, BookNotFoundException {
        Reservation reservation = reserveBookRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);
        BookOrder newOrder = BookOrder.newOrder(reservation.getId());
        bookOrderRepository.save(newOrder);
        reservationEntryService.updateReservation(reservation, ReservationStatus.COMMITTED);
        return 0;
    }

}
