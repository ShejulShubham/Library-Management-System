package com.discovery.dto;



import javax.validation.constraints.NotBlank;

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
public class AddBookDTO {
	
	@NotBlank(message = "title required")
	private String title;
	
//	private LocalDate publishDate;
	
	private int quantityAvailable;
	
//	@NotBlank(message = "Description required")
	private String description;
	
	
	private Long categoryId;
	
	private String categoryName;
	
	private Long authorId;
	
	private String authorName;
	
	private String bookImagePath;
}
