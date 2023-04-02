package org.example;

import org.example.entity.Book;
import org.example.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrarySystemApplication implements CommandLineRunner {

	private final BookRepository bookRepository;

	public LibrarySystemApplication(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(LibrarySystemApplication.class, args);
	}

	@Override
	public void run(String... args) {

		Book book1  = new Book();
		book1.setTitle("Title1");
		book1.setAuthor("Author1");
		book1.setDescription("Description1");
		book1.setCategories("Category1");
		book1.setCopies(150);
		book1.setCopiesAvailable(50);
		bookRepository.save(book1);


		Book book2  = new Book();
		book2.setTitle("Title2");
		book2.setAuthor("Author2");
		book2.setDescription("Description2");
		book2.setCategories("Category2,Category3");
		book2.setCopies(170);
		book2.setCopiesAvailable(70);
		bookRepository.save(book2);

		Book book3  = new Book();
		book3.setTitle("Title3");
		book3.setAuthor("Author3");
		book3.setDescription("Description3");
		book3.setCategories("Category3");
		book3.setCopies(160);
		book3.setCopiesAvailable(60);
		System.out.println(book3);
		bookRepository.save(book3);

//		Category category1 = new Category();
//		category1.setName("Love");
//		categoryRepository.save(category1);
//
//		Category category2 = new Category();
//		category2.setName("Detective");
//		categoryRepository.save(category2);
//
//
//		Category category3 = new Category();
//		category3.setName("Horror");
//		categoryRepository.save(category3);
//
//
//		Category category4 = new Category();
//		category4.setName("Classic");
//		categoryRepository.save(category4);

	}
}
