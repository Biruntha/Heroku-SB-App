package com.biruntha.security.basicauth.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.biruntha.security.basicauth.models.Book;

public interface BookRepository extends MongoRepository<Book, Integer>{

    @Query("{'$or':[ {'title': {$regex : ?0, $options: 'i'}}, {'author': {$regex : ?0, $options: 'i'}}, {'language': {$regex : ?0, $options: 'i'}}, {'genre': {$regex : ?0, $options: 'i'}}]}")
	Page<Book> searchBooks(Pageable pageable, String searchText);

}
