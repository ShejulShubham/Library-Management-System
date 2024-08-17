package com.discovery.dto;



import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.discovery.entities.Book;
import com.discovery.entities.BorrowStatus;
import com.discovery.entities.User;

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
public class AddBorrowDTO {
	
	
	 private Long id;
	 
	 
	 private Long bookId;
	 
	 private Long userId;
	 
//	 @NotBlank(message = "BorrowDate required")
	 private BorrowStatus status;
	 
//	 @NotBlank(message = "BorrowDate required")
	 private LocalDate borrowDate;
	 
//	 @NotBlank(message = "ReturnDate required")
	 private LocalDate dueDate;
}
