package com.biruntha.security.basicauth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.biruntha.security.basicauth.models.ERole;
import com.biruntha.security.basicauth.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
