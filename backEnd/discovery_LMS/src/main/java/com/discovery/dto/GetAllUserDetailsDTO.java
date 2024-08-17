package com.discovery.dto;

import com.discovery.entities.BorrowStatus;
import com.discovery.entities.UserRole;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetAllUserDetailsDTO {
	
	private Long id;

	private String firstName;
	
	private String lastName;
	

	private String email;
	
	private UserRole role;
	
	private String book;
	
	private BorrowStatus bStatus;
	
	private String status;
	
	public GetAllUserDetailsDTO(Long id, String firstName, String email, String status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.email = email;
		this.status = status;
	}
	
	
}
