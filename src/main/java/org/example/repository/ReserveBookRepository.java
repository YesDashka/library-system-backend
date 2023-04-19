package org.example.repository;

import org.example.entity.Reservation;
import org.example.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReserveBookRepository extends JpaRepository<Reservation, String> {
    List<Reservation> findAllByStatusAndEndDateBefore(ReservationStatus status, LocalDate endDate);

}
