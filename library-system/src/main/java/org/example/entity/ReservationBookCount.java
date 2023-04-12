package org.example.entity;

import jakarta.persistence.*;

/**
 * This class represents the many-to-many relationship between Reservation and Book
 */

@Entity
@Table(name = "reservation_books_count")
public class ReservationBookCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private final Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private final Book book;
    @Column(name = "count")
    private final int count;

    protected ReservationBookCount() {
        this.id = 0;
        this.book = null;
        this.count = 0;
        this.reservation = null;
    }

    public ReservationBookCount(Reservation reservation, Book book, int count) {
        this.id = 0;
        this.reservation = reservation;
        this.book = book;
        this.count = count;
    }

    public ReservationBookCount(long id, Reservation reservation, Book book, int count) {
        this.id = id;
        this.reservation = reservation;
        this.book = book;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public int getCount() {
        return count;
    }

    public Reservation getReservation() {
        return reservation;
    }

    @Override
    public String toString() {
        return "ReservationBookCount{" +
                "id=" + id +
                ", reservation=" + reservation +
                ", book=" + book +
                ", count=" + count +
                '}';
    }
}
