package org.example.repository;

import org.example.entity.ReservationBookCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationBookCountRepository extends JpaRepository<ReservationBookCount, Long> {
}
