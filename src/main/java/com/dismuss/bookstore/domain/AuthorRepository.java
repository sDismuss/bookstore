package com.dismuss.bookstore.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigInteger;
import java.util.Optional;

@RepositoryRestResource
public interface AuthorRepository extends CrudRepository<Author, BigInteger> {
    Optional<Author> findByFirstName(@Param("firstName") String firstName);
}
