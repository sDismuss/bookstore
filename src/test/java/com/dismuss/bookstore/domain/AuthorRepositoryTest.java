package com.dismuss.bookstore.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;
    
    @Test
    public void saveAuthor() {
        String firstName = "Helga";
        String lastName = "Brown";
        authorRepository.save(new Author(firstName, lastName));
        assertThat(authorRepository.findByFirstName(firstName).isPresent()).isTrue();
        assertThat(authorRepository.findByFirstName(firstName).get().getLastName()).isEqualTo(lastName);
    }

    @Test
    void deleteOwners() {
        authorRepository.save(new Author("Lisa", "Morrison"));
        authorRepository.deleteAll();
        assertThat(authorRepository.count()).isEqualTo(0);
    }
}