package org.example.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final long id;

    @ManyToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private final Reservation reservation;

    public User() {
        this.id = 0L;
        this.reservation = null;
    }

    public User(long id, Reservation reservation) {
        this.id = id;
        this.reservation = reservation;
    }

    public long getId() {
        return id;
    }

    public Reservation getReservation() {
        return reservation;
    }
}
