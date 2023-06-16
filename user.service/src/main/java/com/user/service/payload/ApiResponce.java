package com.user.service.payload;

import org.springframework.http.HttpStatus;

import lombok.Builder;

@Builder
public class ApiResponce {
	
	private String message;
	private boolean success;
	private HttpStatus status;

}
