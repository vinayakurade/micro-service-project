package com.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super("Resourse Not Found on Server");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
