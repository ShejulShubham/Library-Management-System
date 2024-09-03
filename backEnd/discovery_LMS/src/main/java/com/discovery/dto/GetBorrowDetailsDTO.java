package com.discovery.dto;



import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.discovery.entities.Book;
import com.discovery.entities.BorrowStatus;
import com.discovery.entities.User_Account;

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
public class GetBorrowDetailsDTO {
	
	
	 private Long userId;
	 
	 private Long bookId;
	 
	 
}
