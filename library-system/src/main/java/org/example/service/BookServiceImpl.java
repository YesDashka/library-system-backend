package org.example.service;

import org.example.dto.BookDto;
import org.example.entity.Book;
import org.example.exception.BookNotFoundException;
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
    public Book updateBook(long bookId, Book book) {
//        Book updatedBook = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException());
//        updatedBook.setAuthor(book.getAuthor());
//        updatedBook.setCategories(book.getCategories());
//        updatedBook.setDescription(book.getDescription());
//        updatedBook.setCopies(book.getCopies());
//        bookRepository.save(updatedBook);
//        return updatedBook;
        return null;
    }

    @Override
    public void deleteBookById(long bookId) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        bookRepository.delete(book);
    }

    @Override
    public Book getBookById(long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

}
