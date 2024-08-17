package com.discovery.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.discovery.custom_exceptions.ApiException;
import com.discovery.dto.AddBookDTO;
import com.discovery.dto.AddBorrowDTO;
import com.discovery.dto.ApiResponse;
import com.discovery.dto.BookDetailsDTO;
import com.discovery.dto.BorrowDetailsDTO;
import com.discovery.dto.CountDTO;
import com.discovery.dto.GetBorrowDetailsDTO;
import com.discovery.entities.Book;
import com.discovery.service.BookServiceImpl;
import com.discovery.service.BorrowServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/borrow")
public class BorrowController {
	
	@Autowired
	private BorrowServiceImpl borrowService;
	
	@GetMapping
	@Operation(description = "get list of Borrows")
	public ResponseEntity<?> listBorrowList() {
		System.out.println("in list");
		List<BorrowDetailsDTO> list = borrowService.getBorrowList();
		
		if(list.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiException("List is empty"));
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@GetMapping("/list")
	@Operation(description = "get count of book, user, borrow and defaulter")
	public ResponseEntity<?> getAllCount(){
		System.out.println("in count");
		
		try {
			
			return ResponseEntity.ok(borrowService.getCountDetails());
			
		}catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage(), "failure"));
		}		
		
		
	}
	
	
	
	
	
	@GetMapping("/info/{id}")
	public ResponseEntity<?> getBorrowDetail(@PathVariable String id){
		System.out.println("in get borrow detail " + id);
		
		Long userId = Long.parseLong(id);
		
		try {
		
			return ResponseEntity.ok(borrowService.getBorrowDetailByUserIdAndBookId(userId));
			
		}catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage(), "failure"));
		}
	}
	
	@PostMapping("/return")
	public ResponseEntity<?> returnBorrowedBook(@RequestBody GetBorrowDetailsDTO dto){
		System.out.println("in return book " + dto.getUserId() +" "+ dto.getBookId());
		
		try {
		
			return ResponseEntity.ok(borrowService.returnBook(dto.getUserId(), dto.getBookId()));
			
		}catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage(), "failure"));
		}
	}
	
	
	@PostMapping("/issue")
	@Operation(description = "add New Borrow")
	public ResponseEntity<?> addBorrow(@RequestBody AddBorrowDTO borrow) {
		System.out.println("in add borrow " + borrow);
		
		try {
			// invoke service layer
			return ResponseEntity.status(HttpStatus.CREATED).body(borrowService.addNewBorrow(borrow));
		} catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@GetMapping("/defaulters")
	@Operation(description = "get defaulters list")
	public ResponseEntity<?> getDefaultersList(){
		System.out.println("in Defaulter list");
		
		try {
			return ResponseEntity.ok(borrowService.getDefaulterList());
		}catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}

}
