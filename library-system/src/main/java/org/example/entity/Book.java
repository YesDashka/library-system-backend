package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "book")
@Builder
public class Book {

    private static final String CATEGORIES_SEPARATOR = ",";

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

    @Column(name = "categories")
    @ElementCollection
    private Set<String> categories;

    @Column(name = "copies")
    private int copies;

    @Column(name = "copies_available")
    private int copiesAvailable;

    @Column(name = "image")
    private String image;

    public Book() {
    }

    public Book(long id, String title, String author, String description, Set<String> categories, int copies, int copiesAvailable, String image) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.categories = categories;
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

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }
    public void setCategories(String categories) {
        this.categories = new HashSet<>(Arrays.asList(categories.split(CATEGORIES_SEPARATOR)));
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && copies == book.copies && copiesAvailable == book.copiesAvailable && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(description, book.description) && Objects.equals(categories, book.categories) && Objects.equals(image, book.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, description, categories, copies, copiesAvailable, image);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", categories=" + categories +
                ", copies=" + copies +
                ", copiesAvailable=" + copiesAvailable +
                ", image='" + image + '\'' +
                '}';
    }
}
