package com.discovery.dto;

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
public class CategoryDetailsDTO {

	private Long categoryId;
	
	private String categoryName;

	public CategoryDetailsDTO(String categoryName) {
		super();
		this.categoryName = categoryName;
	}
	
	
}
