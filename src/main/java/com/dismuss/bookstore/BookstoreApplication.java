package com.dismuss.bookstore;

import com.dismuss.bookstore.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookstoreApplication.class);

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final UserRepository userRepository;

    public BookstoreApplication(BookRepository bookRepository, AuthorRepository authorRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
        LOGGER.info("Bookstore application started");
    }

    @Override
    public void run(String... args) throws Exception {
        Author author1 = new Author("John", "Doe");
        Author author2 = new Author("Jane", "Doe");

        authorRepository.saveAll(Arrays.asList(author1, author2));

        bookRepository.save(new Book("Book 1", author1, "Description 1", "Publisher 1", 12.0, 300));
        bookRepository.save(new Book("Book 2", author2, "Description 2", "Publisher 2", 24.0, 200));
        bookRepository.save(new Book("Book 3", author2, "Description 3", "Publisher 3", 36.0, 400));

        for (Book book : bookRepository.findAll()) {
            LOGGER.info("book: {}; author:", book.getTitle());
        }

        // Username: user, password: user
        userRepository.save(new User("user",
                "$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue",
                "USER"));
        // Username: admin, password: admin
        userRepository.save(new User("admin",
                "$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW",
                "ADMIN"));
    }
}
