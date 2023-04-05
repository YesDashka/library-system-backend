package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "order")
public class BookOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final long id;

    @Column(name = "book_id")
    private final long bookId;

    @Column(name = "count")
    private final int count;

    @Column(name = "is_reserved")
    private final boolean isReserved;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private final BookOrderStatus status;

    @Column(name = "date")
    private final LocalDate date;

    public BookOrder() {
        this.id = 0;
        this.bookId = 0;
        this.count = 0;
        this.isReserved = false;
        this.status = null;
        this.date = null;
    }

    public long getId() {
        return id;
    }

    public long getBookId() {
        return bookId;
    }

    public int getCount() {
        return count;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public BookOrderStatus getStatus() {
        return status;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "BookOrder{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", count=" + count +
                ", isReserved=" + isReserved +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
