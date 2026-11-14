package com.alpha.theaterservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.theaterservice.dto.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TheaterAlreadyExistWithContactException.class)
	public ResponseEntity<ResponseStructure<String>> handleTheaterAlreadyExistWithContactException() {
		ResponseStructure<String> responsestructure=new ResponseStructure<String>();
		responsestructure.setStatuscode(HttpStatus.FOUND.value());
		responsestructure.setMessage("Theater Already Present");
		responsestructure.setData("Already Exist");
		return new ResponseEntity<ResponseStructure<String>>(responsestructure,HttpStatus.FOUND);
	}
}
