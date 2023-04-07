package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "reservation")
public class Reservation {

    private static final long RESERVATION_DAYS_PERIOD = 7;

    @Id
    @Column(name = "id")
    private final String id;

    @Column(name = "book_id")
    private final long bookId;

    @Column(name = "count")
    private final int count;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private final ReservationStatus status;

    @Column(name = "start_date")
    private final LocalDate startDate;

    @Column(name = "end_date")
    private final LocalDate endDate;

    protected Reservation() {
        this.id = "";
        this.bookId = 0;
        this.count = 0;
        this.status = null;
        this.startDate = null;
        this.endDate = null;
    }

    private Reservation(String id, long bookId, int count, ReservationStatus status, LocalDate startDate, LocalDate endDate) {
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
        String reservationId = UUID.randomUUID().toString();
        LocalDate now = LocalDate.now();

        return new Reservation(
                reservationId,
                bookId,
                count,
                ReservationStatus.NOT_RESERVED,
                now,
                now.plusDays(RESERVATION_DAYS_PERIOD)
        );
    }

    public static Reservation expiredReservation(Reservation reservation) {
        return create(reservation, ReservationStatus.EXPIRED);
    }

    public static Reservation cancelledReservation(Reservation reservation) {
        return create(reservation, ReservationStatus.CANCELLED);
    }

    public static Reservation committedReservation(Reservation reservation) {
        return create(reservation, ReservationStatus.COMMITTED);
    }

    public static Reservation reserved(Reservation reservation) {
        return create(reservation, ReservationStatus.RESERVED);
    }

    public static Reservation factory(Reservation reservation, ReservationStatus status) {
        return switch (status) {
            case RESERVED -> reserved(reservation);
            case EXPIRED -> expiredReservation(reservation);
            case CANCELLED -> cancelledReservation(reservation);
            case COMMITTED -> committedReservation(reservation);
            default -> throw new IllegalArgumentException();
        };
    }

    private static Reservation create(Reservation reservation, ReservationStatus reservationStatus) {
        return new Reservation(
                reservation.id,
                reservation.bookId,
                reservation.count,
                reservationStatus,
                reservation.startDate,
                reservation.endDate
        );
    }

    public long getBookId() {
        return bookId;
    }

    public int getCount() {
        return count;
    }

    private LocalDate getStartDate() {
        return startDate;
    }

    private LocalDate getEndDate() {
        return endDate;
    }

    public String getId() {
        return id;
    }

    public ReservationStatus getStatus() {
        return status;
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
