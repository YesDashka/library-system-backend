package org.example.entity;

import jakarta.persistence.*;
import org.example.exception.ReservationNotAvailableException;

import java.time.LocalDate;

@Entity
@Table(name = "order")
public class BookOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final long id;
    @Column(name = "reservation_id")
    private final long reservationId;
    @Column(name = "date")
    private final LocalDate date;

    protected BookOrder() {
        this.reservationId = 0L;
        this.id = 0;
        this.date = null;
    }

    public BookOrder(long reservationId) {
        this.id = 0L;
        this.reservationId = reservationId;
        this.date = LocalDate.now();
    }

    public static BookOrder newOrder(long reservationId) {
        return new BookOrder(reservationId);
    }

    public long getId() {
        return id;
    }

    public long getReservationId() {
        return reservationId;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "BookOrder{" +
                "id=" + id +
                ", reservationId=" + reservationId +
                ", date=" + date +
                '}';
    }
}
