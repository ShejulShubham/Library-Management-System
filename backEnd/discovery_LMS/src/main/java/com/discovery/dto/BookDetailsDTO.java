package com.discovery.dto;

import java.util.Collection;
import java.util.List;

import com.discovery.entities.Author;
import com.discovery.entities.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookDetailsDTO {

	private Long BookId;
	
	private String bookTitle;
	
	private String description;
	
	private int quantity;
	
	private CategoryDetailsDTO category;
//	
	private List<AuthorDetailsDTO> authors;
	
	private List<String> authorString;
//
	public BookDetailsDTO(Long bookId, String bookTitle, String description, int quantity) {
		super();
		BookId = bookId;
		this.bookTitle = bookTitle;
		this.description = description;
		this.quantity = quantity;
	}
	
	public BookDetailsDTO(Long bookId, String bookTitle, int quantity,
			List<AuthorDetailsDTO>authors) {
		super();
		BookId = bookId;
		this.bookTitle = bookTitle;
		this.quantity = quantity;
		this.authors = authors;
	}
	
	
	public BookDetailsDTO(Long bookId, String bookTitle, String description, int quantity,
			List<AuthorDetailsDTO>authors) {
		super();
		BookId = bookId;
		this.bookTitle = bookTitle;
		this.description = description;
		this.quantity = quantity;
		this.authors = authors;
	}

	public BookDetailsDTO(Long bookId, String bookTitle, String description, int quantity, CategoryDetailsDTO category,
			List<AuthorDetailsDTO> authors) {
		super();
		BookId = bookId;
		this.bookTitle = bookTitle;
		this.description = description;
		this.quantity = quantity;
		this.category = category;
		this.authors = authors;
	}
	
	
	
}
