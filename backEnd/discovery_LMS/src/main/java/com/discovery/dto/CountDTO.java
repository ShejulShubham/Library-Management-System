package com.discovery.dto;



import java.time.LocalDate;
import java.util.List;

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
public class CountDTO {
	
	
	private Long bookCount;
	
	private Long userCount;
	
	private Long borrowCount;
	
	private Long defaulterCount;
	
	private List<UserDetailsDTO> users;
	
	private List<BookDetailsDTO> books;
	 
}
