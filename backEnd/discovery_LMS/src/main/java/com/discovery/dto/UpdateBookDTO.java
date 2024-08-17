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
public class UpdateBookDTO {

	private Long BookId;
	
	private String bookTitle;
	
	private String description;
	
	private int quantity;
	
	private CategoryDetailsDTO category;
//	
	private List<AuthorDetailsDTO> authors;
//
	public UpdateBookDTO(Long bookId, String bookTitle, String description, int quantity) {
		super();
		BookId = bookId;
		this.bookTitle = bookTitle;
		this.description = description;
		this.quantity = quantity;
	}
	
	public UpdateBookDTO(Long bookId, String bookTitle, int quantity,
			List<AuthorDetailsDTO>authors) {
		super();
		BookId = bookId;
		this.bookTitle = bookTitle;
		this.quantity = quantity;
		this.authors = authors;
	}
	
	
	public UpdateBookDTO(Long bookId, String bookTitle, String description, int quantity,
			List<AuthorDetailsDTO>authors) {
		super();
		BookId = bookId;
		this.bookTitle = bookTitle;
		this.description = description;
		this.quantity = quantity;
		this.authors = authors;
	}
	
	
	
	
}
