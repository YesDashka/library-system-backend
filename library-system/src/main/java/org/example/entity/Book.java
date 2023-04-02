package org.example.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.*;

@Entity
@Table(name = "book")
@Builder
public class Book {
    private static final String CATEGORIES_SEPARATOR = ",";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "categories", nullable = false)
    @ElementCollection
    private Set<String> categories;

    @Column(name = "copies")
    private int copies;

    @Column(name = "copies_available", nullable = false)
    private int copiesAvailable;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "price", nullable = false)
    private double price;

    public Book() {
    }

    public Book(long id, String title, String author, String description, Set<String> categories, int copies,
                int copiesAvailable, String image, double price
    ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.categories = categories;
        this.copies = copies;
        this.copiesAvailable = copiesAvailable;
        this.image = image;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && copies == book.copies && copiesAvailable == book.copiesAvailable
                && Double.compare(book.price, price) == 0 && Objects.equals(title, book.title)
                && Objects.equals(author, book.author) && Objects.equals(description, book.description)
                && Objects.equals(categories, book.categories) && Objects.equals(image, book.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, description, categories, copies, copiesAvailable, image, price);
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
                ", price=" + price +
                '}';
    }
}
