package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "book")
@Builder
public class Book {

    private static final String CATEGORIES_SEPARATOR = ",";

    @Id
    @Column(name = "id", nullable = false)
    private final String id;

    @Column(name = "title", nullable = false)
    private final String title;

    @Column(name = "author", nullable = false)
    private final String author;

    @Column(name = "description")
    private final String description;

    @Column(name = "categories", nullable = false)
    @ElementCollection
    private final Set<String> categories;

    @Column(name = "copies")
    private final int copies;

    @Column(name = "copies_available", nullable = false)
    private final int copiesAvailable;

    @Column(name = "image")
    private final String image;

    @Column(name = "price", nullable = false)
    private final double price;

    public Book() {
        this.id = null;
        this.title = null;
        this.author = null;
        this.description = null;
        this.categories = null;
        this.copies = 0;
        this.copiesAvailable = 0;
        this.image = null;
        this.price = 0;
    }

    public Book(
            String id,
            String title,
            String author,
            String description,
            Set<String> categories,
            int copies,
            int copiesAvailable,
            String image,
            double price
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

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return copies == book.copies && copiesAvailable == book.copiesAvailable && Double.compare(book.price, price) == 0 && Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(description, book.description) && Objects.equals(categories, book.categories) && Objects.equals(image, book.image);
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
