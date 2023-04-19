package org.example.service.order;

import org.example.dto.ReservationEntryDto;

import java.util.List;

public class BookOrderRequest {

    private List<ReservationEntryDto> books;

    public BookOrderRequest() {
    }

    private BookOrderRequest(List<ReservationEntryDto> books) {
        this.books = books;
    }

    public static BookOrderRequest newRequest(List<ReservationEntryDto> books) {
        return new BookOrderRequest(books);
    }

    public List<ReservationEntryDto> getBooks() {
        return books;
    }

    public void setBooks(List<ReservationEntryDto> books) {
        this.books = books;
    }


    @Override
    public String toString() {
        return "BookOrderRequest{" +
                "books=" + books +
                '}';
    }
}
