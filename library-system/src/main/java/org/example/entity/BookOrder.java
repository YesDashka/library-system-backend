package org.example.entity;

import jakarta.persistence.*;
import org.example.exception.ReservationNotAvailableException;

import java.time.LocalDate;
import java.util.UUID;

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
        this.id = "";
        this.reservationId = "";
        this.date = null;
    }

    private BookOrder(String id, String reservationId, LocalDate date) {
        this.id = id;
        this.reservationId = reservationId;
        this.date = date;
    }

    public static BookOrder newOrder(Reservation reservation) throws ReservationNotAvailableException {
        if (reservation.getStatus() != ReservationStatus.RESERVED) {
            throw new ReservationNotAvailableException("reservation must have status %s to make an order".formatted(ReservationStatus.RESERVED.name()));
        }
        String id = UUID.randomUUID().toString();
        LocalDate date = LocalDate.now();
        return new BookOrder(
                id,
                reservation.getId(),
                date
        );
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

}
