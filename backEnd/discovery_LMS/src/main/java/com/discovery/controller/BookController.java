package com.discovery.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.discovery.custom_exceptions.ApiException;
import com.discovery.dto.AddBookDTO;
import com.discovery.dto.ApiResponse;
import com.discovery.dto.BookDetailsDTO;
import com.discovery.dto.UpdateBookDTO;
import com.discovery.entities.Book;
import com.discovery.service.BookServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookServiceImpl bookService;
	
	@GetMapping
	@Operation(description = "get list of Books")
	public ResponseEntity<?> listAllBooks() {
//		System.out.println("in list");
		List<BookDetailsDTO> list = bookService.getBookList();
		
		if(list.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse("List is empty", "failure"));
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	
	@GetMapping("/info/{id}")
	@Operation(description = "get book details by id")
	public ResponseEntity<?> getBookDetails(@PathVariable Long id){
		System.out.println("in book info ");
				
					
		try {
			return ResponseEntity.ok(
							bookService.getBookDetails(id));
		} catch (RuntimeException e) {
						
			System.out.println(e);
						
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ApiResponse(e.getMessage(), "failure"));
								
		}
	}
	
	
	
	
	@GetMapping("/find/{category}")
	@Operation(description = "get book details by category")
	public ResponseEntity<?> getBookDetails(@PathVariable String category){
		System.out.println("in book info by category " + category);
				
					
		try {
			return ResponseEntity.ok(
							bookService.getBookDetails(category));
		} catch (RuntimeException e) {
						
			System.out.println(e);
						
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ApiResponse(e.getMessage(), "failure"));
								
		}
	}
	
	
	@PostMapping("/add")
	@Operation(description = "add New Book")
	public ResponseEntity<?> addBook(@RequestBody AddBookDTO book) {
		System.out.println("in add book " + book);
		
		try {
			// invoke service layer
			return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addNewBook(book));
		} catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage(), "failure"));
		}
	}
	
	
	@PatchMapping("/update")
	@Operation(description = "update book")
	public ResponseEntity<?> updateBook(@RequestBody UpdateBookDTO book){
		System.out.println("in book update "+ book);
		
		try {
			return ResponseEntity.ok(bookService.updateExistingBook(book));
		}catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage(), "failure"));
		}
	}
	
	
	@DeleteMapping("/delete/{id}")
	@Operation(description = "delete book by id")
	public ResponseEntity<?> deleteBook(@PathVariable Long id ){
		System.out.println("in book delete" + id);
		
		try {
			return ResponseEntity.ok(bookService.deleteBookRecord(id));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage(), "failure"));
		}
	}
	
	

}
