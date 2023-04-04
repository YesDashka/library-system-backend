package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "order")
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "book_id")
    private long bookId;
    private int count;
    private boolean isReserved;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookOrderStatus status;
    @Column(name = "date")
    private LocalDate date;

    public BookOrder() {
    }

    public BookOrder(long id, long bookId, int count, boolean isReserved, BookOrderStatus status, LocalDate date) {
        this.id = id;
        this.bookId = bookId;
        this.count = count;
        this.isReserved = isReserved;
        this.status = status;
        this.date = date;
    }

    public BookOrder(long bookId, int count, boolean isReserved, BookOrderStatus status, LocalDate date) {
        this.bookId = bookId;
        this.count = count;
        this.isReserved = isReserved;
        this.status = status;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
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

    public boolean isReserved() {
        return isReserved;
    }

    private void setReserved(boolean reserved) {
        
        isReserved = reserved;
    }

    public BookOrderStatus getStatus() {
        return status;
    }

    private void setStatus(BookOrderStatus status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    private void setDate(LocalDate date) {
        this.date = date;
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
