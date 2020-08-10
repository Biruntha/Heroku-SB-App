package com.biruntha.security.basicauth.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.biruntha.security.basicauth.models.User;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Query("{'$or':[ {'username': {$regex : ?0, $options: 'i'}}, {'email': {$regex : ?0, $options: 'i'}}]}")
  Page<User> searchUsers(Pageable pageable, String searchText);
}
