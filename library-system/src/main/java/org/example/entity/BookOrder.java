package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "book_order")
public class BookOrder {

    @Id
    @Column(name = "id")
    private final String id;

    @Column(name = "reservation_id")
    private final String reservationId;

    @Column(name = "date")
    private final LocalDate date;

    protected BookOrder() {
        this.reservationId = "";
        this.id = "";
        this.date = null;
    }

    public BookOrder(String reservationId) {
        this.id = "";
        this.reservationId = reservationId;
        this.date = LocalDate.now();
    }

    public static BookOrder newOrder(Reservation reservation) {
        if (reservation.getStatus() != ReservationStatus.COMMITTED) {
            throw new IllegalArgumentException("Cannot convert non-committed reservation to book order");
        }
        return new BookOrder(reservation.getId());
    }

    public String getId() {
        return id;
    }

    public String getReservationId() {
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
