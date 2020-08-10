package com.biruntha.security.basicauth.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biruntha.security.basicauth.repository.BookRepository;
import com.biruntha.security.basicauth.repository.BookRepositoryCustom;
import com.biruntha.security.basicauth.models.Book;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookRepositoryCustom bookRepositoryCustom;

	public ResponseEntity<Page<Book>> searchBooks(String searchText, int pageNo, int pageSize, String sortBy) {
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
			Page<Book> bookPages = bookRepository.searchBooks(pageable, ".*" + searchText + ".*");
//		    if (bookPages.isEmpty()) {
//		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		    }
//		
//		    Map<String, Object> response = new HashMap<String, Object>();
//			response.put("data", bookPages.getContent());
//		    response.put("Total no of pages", bookPages.getTotalPages());
//		    response.put("Total no of elements", bookPages.getTotalElements());
//		    response.put("Current page no", bookPages.getNumber());
		    
		    return new ResponseEntity<>(bookPages, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Page<Book>> getAllBooksByPage(int pageNo, int pageSize, String sortBy,
			String sortDir) {
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, 
					sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
			Page<Book> page = bookRepository.findAll(pageable);
			
//			Map<String, Object> response = new HashMap<String, Object>();
//			response.put("data", page.getContent());
//		    response.put("Total no of pages", page.getTotalPages());
//		    response.put("Total no of elements", page.getTotalElements());
//		    response.put("Current page no", page.getNumber());

		    return new ResponseEntity<>(page, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Book> findByBookId(Integer id) {
		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			return new ResponseEntity<>(book.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Book> createBook(Book book) {
		try {
			Integer id = bookRepositoryCustom.getMaxBookId() + 1;
			Book bookNew = bookRepository.save(new Book(id, book.getTitle(), book.getAuthor(), 
					book.getCoverPhotoURL(), book.getIsbnNumber(), book.getPrice(), book.getLanguage(), book.getGenre()));
		    return new ResponseEntity<>(bookNew, HttpStatus.CREATED);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	public ResponseEntity<Book> updateBook(Integer id, Book book) {
		Optional<Book> bookData = bookRepository.findById(id);

		if (bookData.isPresent()) {
			Book bookOld = bookData.get();
			bookOld.setTitle(book.getTitle());
			bookOld.setAuthor(book.getAuthor());
			bookOld.setCoverPhotoURL(book.getCoverPhotoURL());
			bookOld.setIsbnNumber(book.getIsbnNumber());
			bookOld.setPrice(book.getPrice());
			bookOld.setLanguage(book.getLanguage());
			bookOld.setGenre(book.getGenre());
		    return new ResponseEntity<>(bookRepository.save(bookOld), HttpStatus.OK);
		} else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<HttpStatus> deleteBookById(Integer id) {
		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			bookRepository.delete(book.get());
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
