package com.discovery.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.discovery.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class UpdateUser {
	

	private Long id;

	@NotBlank(message = "First Name required")
	private String firstName;
	
	@NotBlank(message = "Last Name required")
	private String lastName;
	
	@Email(message = "Invalid Email!!!")
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	private Role role;
	
	
	private String status;
}
