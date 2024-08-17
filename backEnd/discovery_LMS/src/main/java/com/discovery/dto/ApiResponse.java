package com.discovery.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
	private String status;
	private String message;
	private LocalDateTime timeStamp;

	public ApiResponse(String message) {
		super();
		this.message = message;
		this.timeStamp = LocalDateTime.now();
	}
	
	public ApiResponse(String message, String status) {
		this.status = status;
		this.message = message;
		this.timeStamp = LocalDateTime.now();
	}
}
