package org.example.service;

import lombok.SneakyThrows;
import org.example.dto.ReservationEntryDto;
import org.example.entity.Reservation;
import org.example.entity.ReservationEntry;
import org.example.entity.ReservationStatus;
import org.example.exception.BookNotAvailableException;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;
import org.example.exception.ReservationNotFoundException;

import java.util.List;
import java.util.Map;

public interface BookReservationService {

    Reservation reserve(List<ReservationEntryDto> entries) throws BookNotAvailableException, NoSuchCopiesAvailableException, BookNotFoundException;

    @SneakyThrows(value = NoSuchCopiesAvailableException.class)
    Reservation expireReservation(Reservation reservation);

    Reservation cancelReservation(String reservationId) throws BookNotAvailableException;

}
