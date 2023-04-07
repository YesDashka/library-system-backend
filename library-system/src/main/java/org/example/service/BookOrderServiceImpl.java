package org.example.service;

import lombok.SneakyThrows;
import org.example.entity.BookOrder;
import org.example.entity.Reservation;
import org.example.entity.ReservationStatus;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.ReservationNotAvailableException;
import org.example.exception.ReservationNotFoundException;
import org.example.repository.BookOrderRepository;
import org.example.repository.ReserveBookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookOrderServiceImpl implements BookOrderService{

    private final BookOrderRepository bookOrderRepository;
    private final ReserveBookRepository reserveBookRepository;
    private final ReservationEntryService reservationEntryService;

    public BookOrderServiceImpl(
            BookOrderRepository bookOrderRepository,
            ReserveBookRepository reserveBookRepository,
            ReservationEntryService reservationEntryService
    ) {
        this.bookOrderRepository = bookOrderRepository;
        this.reserveBookRepository = reserveBookRepository;
        this.reservationEntryService = reservationEntryService;
    }

    @Override
    public Reservation order(long bookId, int count) throws BookNotFoundException, NoSuchCopiesAvailableException {
        Reservation reservation = reserveBookRepository.save(Reservation.newReservation(bookId, count));
        BookOrder bookOrder = BookOrder.newOrder(reservation);
        bookOrderRepository.save(bookOrder);

        return reservationEntryService.updateReservation(reservation, ReservationStatus.COMMITTED);
    }

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    @Override
    public Reservation orderReserved(String reservationId) throws ReservationNotFoundException, BookNotFoundException, ReservationNotAvailableException {
        Reservation reservation = reserveBookRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);
        if (reservation.getStatus() != ReservationStatus.RESERVED) {
            throw new ReservationNotAvailableException("reservation must have status %s to make an order".formatted(ReservationStatus.RESERVED.name()));
        }
        BookOrder newOrder = BookOrder.newOrder(reservation);
        bookOrderRepository.save(newOrder);
        return reservationEntryService.updateReservation(reservation, ReservationStatus.COMMITTED);
    }

}
