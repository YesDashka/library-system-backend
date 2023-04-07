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
    public BookOrder order(long bookId, int count) throws BookNotFoundException, NoSuchCopiesAvailableException, ReservationNotAvailableException {
        Reservation newReservation = reservationEntryService.createNewReservation(bookId, count);
        BookOrder bookOrder = BookOrder.newOrder(newReservation);
        bookOrderRepository.save(bookOrder);
//        todo write call to payment service here
        reservationEntryService.updateReservation(newReservation, ReservationStatus.COMMITTED);
        return bookOrder;
    }

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    @Override
    public BookOrder orderReserved(String reservationId) throws ReservationNotFoundException, BookNotFoundException, ReservationNotAvailableException {
        Reservation reservation = reserveBookRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);
        BookOrder newOrder = BookOrder.newOrder(reservation);
        newOrder = bookOrderRepository.save(newOrder);
        System.out.println(newOrder);
//        todo write call to payment service here
        reservationEntryService.updateReservation(reservation, ReservationStatus.COMMITTED);
        return newOrder;
    }

}
