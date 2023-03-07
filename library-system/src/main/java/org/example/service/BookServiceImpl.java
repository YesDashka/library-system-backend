package org.example.service;

import org.example.dto.BookDto;
import org.example.entity.Book;
import org.example.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(BookDto bookDto) {
        Book book = BookDto.convertToBook(bookDto);
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(long bookId, Book bookDetails) {
        Book updatedBook = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException());
        updatedBook.setAuthor(bookDetails.getAuthor());
        updatedBook.setAuthor(bookDetails.getAuthor());
        updatedBook.setCategory(bookDetails.getCategory());
        updatedBook.setDescription(bookDetails.getDescription());
        updatedBook.setCopies(bookDetails.getCopies());
        bookRepository.save(updatedBook);
        return updatedBook;
    }

    @Override
    public void deleteBookById(long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException());
        bookRepository.delete(book);
    }

    @Override
    public Book getBookById(long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
