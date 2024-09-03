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
@ToString
public class SignUp {
	
	@JsonProperty(access = Access.READ_ONLY) // this property only used during ser.
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
	
public SignUp(String firstName, String lastName,
		String email, String password) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.password = password;	}
	
	
}
