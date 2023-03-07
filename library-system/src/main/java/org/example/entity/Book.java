package org.example.entity;

import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;

@Entity
@Table(name = "book")
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "copies")
    private int copies;

    @Column(name = "copies_available")
    private String copiesAvailable;

    @Column(name = "image")
    private String image;

    public Book() {
    }

    public Book(long id, String title, String author, String description, String category, int copies, String copiesAvailable, String image) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.category = category;
        this.copies = copies;
        this.copiesAvailable = copiesAvailable;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && copies == book.copies && title.equals(book.title) && author.equals(book.author) && description.equals(book.description) && category.equals(book.category) && copiesAvailable.equals(book.copiesAvailable) && image.equals(book.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, description, category, copies, copiesAvailable, image);
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", copies=" + copies +
                ", copiesAvailable='" + copiesAvailable + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
