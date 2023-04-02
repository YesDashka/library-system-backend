package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class Reservation {

    private static final long RESERVATION_DAYS_PERIOD = 7;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private long bookId;
    private int count;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReservationStatus status;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    protected Reservation() {
    }

    private Reservation(long bookId, int count, ReservationStatus status, LocalDate startDate, LocalDate endDate) {
        this.bookId = bookId;
        this.count = count;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    private Reservation(long id, long bookId, int count, ReservationStatus status, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.bookId = bookId;
        this.count = count;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Reservation newReservation(long bookId, int count) {
        if (bookId < 0L) {
            throw new RuntimeException("Invalid bookId");
        }
        if (count < 0L) {
            throw new RuntimeException("Invalid count");
        }
        LocalDate now = LocalDate.now();
        return new Reservation(
                bookId,
                count,
                ReservationStatus.RESERVED,
                now,
                now.plusDays(RESERVATION_DAYS_PERIOD)
        );
    }

    public static Reservation expiredReservation(Reservation reservation) {
        return new Reservation(
                reservation.id,
                reservation.bookId,
                reservation.count,
                ReservationStatus.EXPIRED,
                reservation.startDate,
                reservation.endDate
        );
    }

    public long getBookId() {
        return bookId;
    }

    private void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getCount() {
        return count;
    }

    private void setCount(int count) {
        this.count = count;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    private void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    private void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    private void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", count=" + count +
                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
