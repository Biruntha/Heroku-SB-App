package com.biruntha.security.basicauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biruntha.security.basicauth.models.User;
import com.biruntha.security.basicauth.services.UserService;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
    public ResponseEntity<Page<User>> getAllUsersByPage(
		@RequestParam(name = "pageNo", defaultValue = "0") int pageNo, 
		@RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
		return userService.getAllUsersByPage(pageNo, pageSize);
	}
	
	@GetMapping("/search/{searchText}")
	public ResponseEntity<Page<User>> searchBooks(@PathVariable String searchText,
			@RequestParam(name = "pageNo", defaultValue = "0") int pageNo, 
    		@RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
		return userService.searchUsers(searchText, pageNo, pageSize);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findByUserId(@PathVariable String id) {
		return userService.findByUserId(id);
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
		return userService.updateUser(id, user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") String id) {
		return userService.deleteUserById(id);
    }
}
