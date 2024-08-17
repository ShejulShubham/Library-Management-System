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
public class BorrowDetailsDTO {
	
	
	 private Long id;
	 
	 private Long bookId;
	 
	 private String bookName;
	 
	 private Long userId;
	 
	 private String userName;

	 private BorrowStatus status;
	 
	 private LocalDate borrowDate;
	 
	 private LocalDate returnDate;
	 
	 private LocalDate DueDate;
	 
	 public BorrowDetailsDTO(Long id, String bookName, String userName, BorrowStatus status, LocalDate borrowDate,
				LocalDate dueDate) {
			super();
			this.id = id;
			this.bookName = bookName;
			this.userName = userName;
			this.status = status;
			this.borrowDate = borrowDate;
			this.DueDate = dueDate;
		}
	 
	 
}
