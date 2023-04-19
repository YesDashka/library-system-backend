package org.example.entity;

public enum ReservationStatus {
    NOT_RESERVED(1),
    RESERVED(2),
    ERROR(3), EXPIRED(3), CANCELLED(3),
    COMMITTED(4);

    private final int stage;

    ReservationStatus(int stage) {
        this.stage = stage;
    }

    public boolean isMoreThan(ReservationStatus reservationStatus) {
        return this.stage > reservationStatus.stage;
    }
}
