package com.dismuss.bookstore.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigInteger;
import java.util.List;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book, BigInteger> {
    List<Book> findByTitle(@Param("title") String title);

    List<Book> findByPublisher(@Param("publisher") String publisher);
}
