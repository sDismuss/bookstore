package com.dismuss.bookstore;

import com.dismuss.bookstore.web.BookController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookstoreApplicationTests {

    @Autowired
    BookController bookController;

    @Test
    @DisplayName("First example test case")
    void contextLoads() {
        assertThat(bookController).isNotNull();
    }

}
