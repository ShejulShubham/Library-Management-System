package com.discovery.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignInRequest {
	
	
	@NotEmpty(message = "Email can't be blank")
	 @Email(message = "Email ID is incorrect")
	private String email;
	
	@NotEmpty
	@Length(min = 6,max=20,message = "Invalid password length")
	private String password;
}
