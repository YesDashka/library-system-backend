package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.entity.BookOrderStatus;
import org.example.entity.ReservationStatus;
import org.example.entity.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
    @Modifying
    @Transactional
    @Query("update BookOrder set status = ?2 where id = ?1")
    void updateOrderStatus(long orderId, BookOrderStatus status);
}
