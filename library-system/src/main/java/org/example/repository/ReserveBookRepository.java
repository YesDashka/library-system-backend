package org.example.repository;

import org.example.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveBookRepository extends JpaRepository<Reservation, Long> {
}
