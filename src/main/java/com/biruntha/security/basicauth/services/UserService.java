package com.biruntha.security.basicauth.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biruntha.security.basicauth.models.User;
import com.biruntha.security.basicauth.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public ResponseEntity<Page<User>> getAllUsersByPage(int pageNo, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);
			Page<User> page = userRepository.findAll(pageable);

		    return new ResponseEntity<>(page, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Page<User>> searchUsers(String searchText, int pageNo, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);
			Page<User> userPages = userRepository.searchUsers(pageable, ".*" + searchText + ".*");
		    
		    return new ResponseEntity<>(userPages, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<HttpStatus> deleteUserById(String id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.delete(user.get());
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<User> createUser(User user) {
		try {
			User userNew = userRepository.save(user);
		    return new ResponseEntity<>(userNew, HttpStatus.CREATED);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	public ResponseEntity<User> updateUser(String id, User user) {
		Optional<User> userData = userRepository.findById(id);
		
		if (userData.isPresent()) {
			User bookOld = userData.get();
			bookOld.setUsername(user.getUsername());
			bookOld.setEmail(user.getEmail());
			bookOld.setRoles(user.getRoles());
		    return new ResponseEntity<>(userRepository.save(bookOld), HttpStatus.OK);
		} else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<User> findByUserId(String id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
