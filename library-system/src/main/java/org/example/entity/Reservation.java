package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "reservation")
public class Reservation {

    private static final long RESERVATION_DAYS_PERIOD = 7;
    private static final int MAX_BOOKS_IN_ORDER = 10;

    @Id
    @Column(name = "id")
    private final String id;

    @ElementCollection
    @CollectionTable(name = "reservation_books_count", joinColumns = @JoinColumn(name = "reservation_id"))
    @MapKeyColumn(name = "book_id")
    @Column(name = "count")
    private final Map<Long, Integer> booksCount;

//    @Column(name = "count")
//    private final int count;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private final ReservationStatus status;

    @Column(name = "start_date")
    private final LocalDate startDate;

    @Column(name = "end_date")
    private final LocalDate endDate;

    protected Reservation() {
        this.id = "";
        this.booksCount = null;
        this.status = null;
        this.startDate = null;
        this.endDate = null;
    }

    private Reservation(String id, Map<Long, Integer> booksCount, ReservationStatus status, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.booksCount = booksCount;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Reservation newReservation(Map<Long, Integer> booksCount) {
        if(booksCount.isEmpty()) {
            throw new RuntimeException("Invalid books count");
        }
        int totalBookCount = booksCount.values().stream().mapToInt(Integer::intValue).sum();

        if(totalBookCount > MAX_BOOKS_IN_ORDER) {
            throw new RuntimeException("The order contains too many books");
        }

        String reservationId = UUID.randomUUID().toString();
        LocalDate now = LocalDate.now();

        return new Reservation(
                reservationId,
                booksCount,
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
                reservation.booksCount,
                reservationStatus,
                reservation.startDate,
                reservation.endDate
        );
    }

    public Map<Long, Integer> getBooksCount() {
        return booksCount;
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
                "id='" + id + '\'' +
                ", booksCount=" + booksCount +
                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
