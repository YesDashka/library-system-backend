package org.example.service;

import lombok.SneakyThrows;
import org.example.entity.Reservation;
import org.example.entity.ReservationStatus;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.ReservationNotFoundException;
import org.example.repository.ReserveBookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BookReservationServiceImpl implements BookReservationService {

    private final ReserveBookRepository reserveBookRepository;
    private final ReservationEntryService reservationEntryService;

    public BookReservationServiceImpl(
            ReserveBookRepository reserveBookRepository,
            ReservationEntryService reservationEntryService
    ) {
        this.reserveBookRepository = reserveBookRepository;
        this.reservationEntryService = reservationEntryService;
    }

    @Override
    public Reservation reserve(long bookId, int count) throws NoSuchCopiesAvailableException, BookNotFoundException {
        Reservation reservation = Reservation.newReservation(bookId, count);
        return reservationEntryService.updateReservation(reservation, ReservationStatus.RESERVED);
    }

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    @Override
    public Reservation expireReservation(Reservation reservation) throws BookNotFoundException {
        return reservationEntryService.updateReservation(reservation, ReservationStatus.EXPIRED);
    }

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    @Transactional
    @Override
    public Reservation cancelReservation(String reservationId) throws ReservationNotFoundException, BookNotFoundException {
        final Reservation reservation = reserveBookRepository
                .findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);
        return reservationEntryService.updateReservation(reservation, ReservationStatus.CANCELLED);
    }
}

