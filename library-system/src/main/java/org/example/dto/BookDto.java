package org.example.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import org.example.entity.Book;
import org.example.entity.Category;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Builder
public class BookDto {

    private String title;

    private String author;

    private String description;

    private String categoryName;

    private int copiesAvailable;

    private String image;

    public BookDto() {
    }

    public BookDto(String title, String author, String description, String categoryName, int copiesAvailable, String image) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.categoryName = categoryName;
        this.copiesAvailable = copiesAvailable;
        this.image = image;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public static Book convertToBook(BookDto bookDto){
        return Book.builder().
                title(bookDto.title)
                .author(bookDto.author)
                .description(bookDto.description)
                .category(bookDto.categoryName)
                .copiesAvailable(bookDto.copiesAvailable)
                .image(bookDto.image)
                .build();
    }
    public static BookDto convert(Book book) {
        return BookDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .categoryName(book.getCategory())
                .copiesAvailable(book.getCopiesAvailable())
                .image(book.getImage())
                .build();
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", categoryName=" + categoryName +
                ", copiesAvailable='" + copiesAvailable + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
