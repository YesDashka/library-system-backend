package org.example.dto;

import lombok.Builder;
import org.example.entity.Book;

import java.util.*;

@Builder
public class BookDto {

    private String title;
    private String author;
    private String description;
    private Set<String> categories;
    private int copiesAvailable;
    private String image;
    private double price;

    public BookDto() {
    }

    public BookDto(String title, String author, String description, Set<String> categories, int copiesAvailable,
                   String image, double price
    ) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.categories = categories;
        this.copiesAvailable = copiesAvailable;
        this.image = image;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }


    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static Book convertToBook(BookDto bookDto){
        return Book.builder().
                title(bookDto.title)
                .author(bookDto.author)
                .description(bookDto.description)
                .categories(bookDto.categories)
                .copiesAvailable(bookDto.copiesAvailable)
                .image(bookDto.image)
                .price(bookDto.price)
                .build();
    }
    public static BookDto convertToBookDto(Book book) {
        return BookDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .categories(book.getCategories())
                .copiesAvailable(book.getCopiesAvailable())
                .image(book.getImage())
                .price(book.getPrice())
                .build();
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", categories=" + categories +
                ", copiesAvailable=" + copiesAvailable +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
