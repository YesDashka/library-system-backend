package org.example.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(long bookId) {
        super("Book not found with id: " + bookId);
    }

}
