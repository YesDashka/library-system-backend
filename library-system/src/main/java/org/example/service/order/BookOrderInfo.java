package org.example.service.order;

import java.io.Serializable;

public class BookOrderInfo implements Serializable {

    private long bookId;
    private int count;

    public BookOrderInfo(long bookId, int count) {
        this.bookId = bookId;
        this.count = count;
    }

    public BookOrderInfo() {
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BookIdCount{" +
                "bookId=" + bookId +
                ", count=" + count +
                '}';
    }
}