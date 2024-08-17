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
public class AuthorDetailsDTO {

	private Long authorId;
	
	private String authorName;

	public AuthorDetailsDTO(String authorName) {
		super();
		this.authorName = authorName;
	}
	
	
}
