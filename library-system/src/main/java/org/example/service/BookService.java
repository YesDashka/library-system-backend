package org.example.service;



import org.example.dto.BookDto;
import org.example.entity.Book;
import org.example.exception.BookNotAvailableException;
import org.example.exception.BookNotFoundException;
import org.example.exception.NoSuchCopiesAvailableException;

import java.util.List;

public interface BookService {

    Book createBook(BookDto bookDto);

    Book updateBook(long bookId, Book bookDetails);

    void deleteBookById(long bookId) throws BookNotFoundException;

    Book getBookById(long bookId) throws BookNotFoundException;

    List<Book> getAllBooks();

}
