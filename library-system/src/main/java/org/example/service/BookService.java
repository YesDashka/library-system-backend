package org.example.service;



import org.example.dto.BookDto;
import org.example.entity.Book;

import java.util.List;

public interface BookService {
    Book createBook(BookDto bookDto);
    Book updateBook(long bookId, Book bookDetails);
    void deleteBookById(long bookId);
    Book getBookById(long bookId);
    List<Book> getAllBooks();
}
