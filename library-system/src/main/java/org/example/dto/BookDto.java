package org.example.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.example.entity.Book;
import org.example.entity.Category;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

public class BookDto {

    private String title;

    private String author;

    private String description;

    private String categoryName;

    private String copiesAvailable;

    private String image;

    public BookDto() {
    }

    public BookDto(String title, String author, String description, String categoryName, String copiesAvailable, String image) {
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

    public String getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(String copiesAvailable) {
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
                .category(Category.builder().name(bookDto.categoryName).build())
                .copiesAvailable(bookDto.copiesAvailable)
                .image(bookDto.image)
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
