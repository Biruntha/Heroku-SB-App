package com.biruntha.security.basicauth.controllers;


import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

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

import com.biruntha.security.basicauth.models.Book;
import com.biruntha.security.basicauth.services.BookService;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/search/{searchText}")
	public ResponseEntity<Page<Book>> searchBooks(@PathVariable String searchText,
			@RequestParam(name = "pageNo", defaultValue = "0") int pageNo, 
    		@RequestParam(name = "pageSize", defaultValue = "5") int pageSize, 
    		@RequestParam(name = "sortBy", defaultValue = "price") String sortBy) {
		return bookService.searchBooks(searchText, pageNo, pageSize, sortBy);
	}
	
	@GetMapping
    public ResponseEntity<Page<Book>> getAllBooksByPage(
		@RequestParam(name = "pageNo", defaultValue = "0") int pageNo, 
		@RequestParam(name = "pageSize", defaultValue = "5") int pageSize, 
		@RequestParam(name = "sortBy", defaultValue = "price") String sortBy,
		@RequestParam(name = "sortDir", defaultValue = "ASC") String sortDir) {
	return bookService.getAllBooksByPage(pageNo, pageSize, sortBy, sortDir);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> findByBookId(@PathVariable Integer id) {
		return bookService.findByBookId(id);
	}
	
	@PostMapping
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		return bookService.createBook(book);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") Integer id, @RequestBody Book book) {
		return bookService.updateBook(id, book);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteBookById(@PathVariable("id") Integer id) {
		return bookService.deleteBookById(id);
    }
	
	@GetMapping("/languages")
	public  ResponseEntity<Set<String>> findAllLanguages() {
        return new ResponseEntity<>(new TreeSet<>(Arrays.asList("French", "Portuguese", "English", "Russian", "Hindi", "Arabic", "Spanish", "Chinese")), HttpStatus.OK);
	}
	
	@GetMapping("/genres")
    public  ResponseEntity<Set<String>> findAllGenres() {
        return new ResponseEntity<>(new TreeSet<>(Arrays.asList("Technology", "Science", "History", "Fantasy", "Biography", "Horror", "Romance")), HttpStatus.OK);
    }
}
