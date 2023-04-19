package org.example.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Optional;

@Entity
@Table(name = "reservation_entry")
public class ReservationEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    @Column(name = "book_id")
    private final String bookId;

    @Column(name = "count")
    private final int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private final Reservation reservation;

    protected ReservationEntry() {
        this.id = 0L;
        this.bookId = null;
        this.count = 0;
        this.reservation = null;
    }

    public ReservationEntry(long id, String bookId, int count, Reservation reservation) {
        this.id = id;
        this.bookId = bookId;
        this.count = count;
        this.reservation = reservation;
    }

    private ReservationEntry(String bookId, int count) {
        this.id = 0L;
        this.bookId = bookId;
        this.count = count;
        this.reservation = null;
    }

    public static ReservationEntry newEntry(String  bookId, int count){
        return new ReservationEntry(bookId, count);
    }

    public long getId() {
        return id;
    }

    public String getBookId() {
        return bookId;
    }

    public int getCount() {
        return count;
    }

    public Reservation getReservation() {
        return reservation;
    }

    @Override
    public String toString() {
        return "ReservationEntry{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", count=" + count +
                ", reservationId=" + Optional.ofNullable(reservation).map(Reservation::getId).orElse(null) +
                '}';
    }
}
