package org.example.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String bookId) {
        super("Book not found with id: " + bookId);
    }

}
