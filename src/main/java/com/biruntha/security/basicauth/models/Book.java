package com.biruntha.security.basicauth.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Book")
public class Book {
	
	@Id
	private Integer id;
	
	@NotEmpty(message = "Title must not be empty")
	private String title;
	
	@NotEmpty(message = "Author must not be empty")
	private String author;
	
	@NotEmpty(message = "Cover photo URL must not be empty")
	private String coverPhotoURL;
	
	@NotEmpty(message = "ISBN Number must not be empty")
	private Long isbnNumber;
	
	@NotEmpty(message = "Price must not be empty")
	private Double price;
	
	@NotEmpty(message = "Language must not be empty")
	private String language;

	@NotEmpty(message = "Genre must not be empty")
	private String genre;

	public Book() {
		super();
	}

	public Book(Integer id, @NotNull String title, @NotNull String author, @NotNull String coverPhotoURL, @NotNull Long isbnNumber,
			@NotNull Double price, @NotNull String language, @NotNull String genre) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.coverPhotoURL = coverPhotoURL;
		this.isbnNumber = isbnNumber;
		this.price = price;
		this.language = language;
		this.genre = genre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCoverPhotoURL() {
		return coverPhotoURL;
	}

	public void setCoverPhotoURL(String coverPhotoURL) {
		this.coverPhotoURL = coverPhotoURL;
	}

	public Long getIsbnNumber() {
		return isbnNumber;
	}

	public void setIsbnNumber(Long isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", coverPhotoURL=" + coverPhotoURL + ", isbnNumber="
				+ isbnNumber + ", price=" + price + ", language=" + language + ", genre=" + genre + "]";
	}
}

