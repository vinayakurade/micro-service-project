package com.hotel.service.exception;

public class ResourseNotFoundException extends RuntimeException {
	
	public ResourseNotFoundException (String s) {
		super(s);
	}
	
	public ResourseNotFoundException () {
		super("Resource not found !!");
	}

}
