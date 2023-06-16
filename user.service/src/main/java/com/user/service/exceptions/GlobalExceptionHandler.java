package com.user.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.user.service.payload.ApiResponce;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> handlerResourceNotFoundException(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponce response = ApiResponce.builder().message(message).success(true).status(HttpStatus.NOT_FOUND)
				.build();
		return new ResponseEntity<ApiResponce>(response, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponce> handlerIllegalArgumentException(IllegalArgumentException ex) {
		String message = ex.getMessage();
		ApiResponce response = ApiResponce.builder().message(message).success(true).status(HttpStatus.BAD_REQUEST)
				.build();
		return new ResponseEntity<ApiResponce>(response, HttpStatus.BAD_REQUEST);

	}
//
//	@ExceptionHandler(Throwable.class)
//	public ResponseEntity<ApiResponce> handlerGlobalException(Throwable ex) {
//		String message = ex.getMessage();
//		ApiResponce response = ApiResponce.builder().message(message).success(true).status(HttpStatus.INTERNAL_SERVER_ERROR)
//				.build();
//		return new ResponseEntity<ApiResponce>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//
//	}

}
