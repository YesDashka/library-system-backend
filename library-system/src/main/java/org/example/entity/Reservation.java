package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "reservation")
public class Reservation {

    private static final long RESERVATION_DAYS_PERIOD = 7;
    private static final int MAX_BOOKS_IN_ORDER = 10;

    @Id
    @Column(name = "id")
    private final String id;

    @Column(name = "entries")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private final List<ReservationEntry> entries;


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private final ReservationStatus status;

    @Column(name = "start_date")
    private final LocalDate startDate;

    @Column(name = "end_date")
    private final LocalDate endDate;

    protected Reservation() {
        this.id = "";
        this.entries = new ArrayList<>();
        this.status = null;
        this.startDate = null;
        this.endDate = null;
    }

    private Reservation(String id, List<ReservationEntry> entries, ReservationStatus status, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.entries = entries;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Reservation newReservation(List<ReservationEntry> entries) {
        if(entries.isEmpty()) {
            throw new RuntimeException("There are no book for reservation");
        }
        
        int totalBookCount = entries.stream()
                .mapToInt(ReservationEntry::getCount)
                .sum();

        if(totalBookCount > MAX_BOOKS_IN_ORDER) {
            throw new RuntimeException("The order contains too many books");
        }

        // TODO: 15.04.23 custom class for Unique string identifier MLUID
        String reservationId = IDGenerator.generateID();
        LocalDate now = LocalDate.now();

        return new Reservation(
                reservationId,
                entries,
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
                reservation.entries,
                reservationStatus,
                reservation.startDate,
                reservation.endDate
        );
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

    public List<ReservationEntry> getEntries() {
        return entries;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", entries=" + entries +
                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
