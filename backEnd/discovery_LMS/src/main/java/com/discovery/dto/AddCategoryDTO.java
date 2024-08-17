package com.discovery.dto;

import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddCategoryDTO {
	
	@NotBlank(message = "category name required")
	private String categoryName;
}
