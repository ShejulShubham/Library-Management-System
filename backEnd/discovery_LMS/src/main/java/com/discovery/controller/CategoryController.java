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
import com.discovery.dto.AddCategoryDTO;
import com.discovery.dto.ApiResponse;
import com.discovery.dto.CategoryDetailsDTO;
import com.discovery.entities.Category;
import com.discovery.service.CategoryServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryService;
	
	
	@GetMapping("/getAll")
	@Operation(description = "get list of Category")
	public ResponseEntity<?> listAllCategories() {
		
//		System.out.println("in list");
		List<CategoryDetailsDTO> list = categoryService.getCategoryList();
		
		
		if(list.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiException("List is empty"));
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	
	@PostMapping
	@Operation(description = "add New Category")
	public ResponseEntity<?> addCategory(@RequestBody @Valid AddCategoryDTO newCategory) {
		System.out.println("in add category " + newCategory);
		
		try {
			// invoke service layer
			return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(newCategory));
		} catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), "success"));
		}
	}
}
