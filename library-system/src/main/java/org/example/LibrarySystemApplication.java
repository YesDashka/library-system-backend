package org.example;

import org.example.entity.Category;
import org.example.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrarySystemApplication implements CommandLineRunner {

	CategoryRepository categoryRepository;

	public LibrarySystemApplication(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(LibrarySystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category category1 = new Category();
		category1.setName("Love");
		categoryRepository.save(category1);

		Category category2 = new Category();
		category2.setName("Detective");
		categoryRepository.save(category2);


		Category category3 = new Category();
		category3.setName("Horror");
		categoryRepository.save(category3);


		Category category4 = new Category();
		category4.setName("Classic");
		categoryRepository.save(category4);

	}
}
