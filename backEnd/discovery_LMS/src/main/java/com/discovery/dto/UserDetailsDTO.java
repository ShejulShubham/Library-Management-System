package com.discovery.dto;

import com.discovery.entities.BorrowStatus;
import com.discovery.entities.Role;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDetailsDTO {
	
	
	private Long id;

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Role role;
	
	private String status;
	
	public UserDetailsDTO(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public UserDetailsDTO(String firstName, Long id, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.email = email;
	}
	
	
	
}
