package com.discovery.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.discovery.custom_exceptions.ApiException;
import com.discovery.dto.AddAuthorDTO;
import com.discovery.dto.AddCategoryDTO;
import com.discovery.dto.ApiResponse;
import com.discovery.dto.AuthorDetailsDTO;
import com.discovery.entities.Author;
import com.discovery.entities.Category;
import com.discovery.service.AuthorServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/authors")
public class AuthorController {

	@Autowired
	private AuthorServiceImpl authorService;
	
	@GetMapping
	@Operation(description = "get list of Author")
	public ResponseEntity<?> listAllAuthors() {
//		System.out.println("in list");
		List<AuthorDetailsDTO> list = authorService.getAuthorList();
		
		if(list.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiException("List is empty"));
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	
	@PostMapping
	@Operation(description = "add New Author")
	public ResponseEntity<?> addAuthor(@RequestBody @Valid AddAuthorDTO author) {
		System.out.println("in add Author " + author);
		
		try {
			// invoke service layer
			return ResponseEntity.status(HttpStatus.CREATED).body(authorService.addAuthor(author));
		} catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
}
