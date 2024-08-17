package com.discovery.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.discovery.dto.ApiResponse;
import com.discovery.dto.SignInRequest;
import com.discovery.dto.SignUp;
import com.discovery.dto.UpdateUser;
import com.discovery.service.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	@Autowired //byType
	private UserServiceImpl userService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllUserDetails(){
		System.out.println("in get all users ");
				
					
		try {
			return ResponseEntity.ok(
							userService.getAllUsers());
		} catch (RuntimeException e) {
						
			System.out.println(e);
						
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ApiResponse(e.getMessage(), "failure"));
								
		}
	}
	
	@PostMapping("/signin")
	@Operation(description = "Sign in User")
	public ResponseEntity<?> signInUser(@RequestBody SignInRequest request, BindingResult bindingResult) {
	    // Check for validation errors
	    if (bindingResult.hasErrors()) {
	        String errorMessage = bindingResult.getFieldError().getDefaultMessage();
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                             .body(new ApiResponse(errorMessage, "failure"));
	    }

	    try {
	        return ResponseEntity.ok(userService.authenticateUser(request));
	    } catch (RuntimeException e) {
	        // Log the exception and send a user-friendly message
	        System.out.println(e);
	        String userFriendlyMessage = "Invalid email or password. Please try again.";
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                             .body(new ApiResponse(userFriendlyMessage, "failure"));
	    }
	}
		
	
	
	
	@GetMapping("/info/{id}")
	@Operation(description = "get user details by id")
	public ResponseEntity<?> getUserDetails(@PathVariable Long id){
		System.out.println("in User info ");
				
					
		try {
			return ResponseEntity.ok(
							userService.getUserDetails(id));
		} catch (RuntimeException e) {
						
			System.out.println(e);
						
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ApiResponse(e.getMessage(), "failure"));
								
		}
	}
	
	
	@PostMapping("/signup")
	@Operation(description = "Sign Up User")
	public ResponseEntity<?> signUpUser
	(@RequestBody @Valid SignUp dto) {
		
		System.out.println("in add post "+dto);
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.signUpUser(dto));
		} catch (RuntimeException e) {
			
			System.out.println(e);
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
					
		}
	}
	
	
	//Add try catch block
	@PutMapping("/update")
	@Operation(description = "Update user details")
	public ResponseEntity<?> updateUser(@RequestBody @Valid UpdateUser dto) {
		System.out.println("in update user " + dto);

		try {
				return ResponseEntity.ok(userService.updateUser(dto));
		} catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						                             .body(new ApiResponse(e.getMessage()));
			}
	}					
						
	@DeleteMapping("/{userId}")
	@Operation(description = "Delete user details")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
		System.out.println("in delete " + userId);
		
		try {
			return ResponseEntity.ok(userService.deleteUser(userId));
			} catch (RuntimeException e) {
				
				System.out.println(e);
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ApiResponse(e.getMessage()));
						
			}
	}

}
