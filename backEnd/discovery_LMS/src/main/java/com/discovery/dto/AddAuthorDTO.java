package com.discovery.dto;

import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddAuthorDTO {
	
	@NotBlank(message = "author is required")
	private String AuthorName;
	
}
