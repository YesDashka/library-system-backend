package org.example.schedule;

import org.example.entity.Book;
import org.example.entity.Reservation;
import org.example.entity.ReservationStatus;
import org.example.exception.BookNotFoundException;
import org.example.repository.BookRepository;
import org.example.repository.ReserveBookRepository;
import org.example.service.BookReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UpdateExpiredReservationsTask {

    private static final Logger logger = LoggerFactory.getLogger(UpdateExpiredReservationsTask.class);
    private static final long UPDATE_EXPIRED_RESERVATIONS_TASK_PERIOD = 5000;

    private final ReserveBookRepository reservationRepository;
    private final BookReservationService reservationService;


    public UpdateExpiredReservationsTask(ReserveBookRepository repository, BookReservationService reservationService) {
        this.reservationRepository = repository;
        this.reservationService = reservationService;
    }

    @Scheduled(fixedRate = UPDATE_EXPIRED_RESERVATIONS_TASK_PERIOD)
    public void updateExpiredReservations() {
        logger.info("start updating expired reservations");
        List<Reservation> reservations = reservationRepository
                .findAllByStatusAndEndDateBefore(ReservationStatus.RESERVED, LocalDate.now())
                .stream()
                .collect(Collectors.toList());
        if (reservations.isEmpty()) {
            return;
        }
        logger.info("Fetch reservations to expire: count={}", reservations.size());

        List<Reservation> failedReservations = new ArrayList<>();
        reservations.forEach(reservation ->{
            try {
                reservationService.expireReservation(reservation);
            } catch (RuntimeException | BookNotFoundException e) {
                logger.error("Failed to expire reservation with id {}: {}", reservation.getId(), e.getMessage());
                failedReservations.add(reservation);
            }
        });

        if (!failedReservations.isEmpty()) {
            logger.warn("Failed to expire {} reservations", failedReservations.size());
            return;
        }
        logger.info("Successfully expired reservations reservations: count={}", reservations.size());
    }

}
