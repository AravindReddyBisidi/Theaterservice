package com.alpha.theaterservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.theaterservice.dto.ResponseStructure;
import com.alpha.theaterservice.exception.TheaterAlreadyExistWithContactException;


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
	
	@ExceptionHandler(TheaterNOtFoundWithIdException.class)
	public ResponseEntity<ResponseStructure<String>> handleTheaterNOtFoundWithIdException() {
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Theater Not Found");
		responseStructure.setData(null);
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MovieAlreadyExistWithTitelException.class)
	public ResponseEntity<ResponseStructure<String>> handlerMovieAlreadyExistWithTitelException() {
		ResponseStructure<String> responsestructure=new ResponseStructure<String>();
		responsestructure.setStatuscode(HttpStatus.FOUND.value());
		responsestructure.setMessage("Movie Already present with title can you change movie title");
		responsestructure.setData("Movie Already present with title");
		return new ResponseEntity<ResponseStructure<String>>(responsestructure,HttpStatus.FOUND);
	}
	
	@ExceptionHandler(MovieNotFoundWithIdExpection.class)
	public ResponseEntity<ResponseStructure<String>> handlerMovieNotFoundWithIdExpection() {
		ResponseStructure<String> responsestructure=new ResponseStructure<String>();
		responsestructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		responsestructure.setMessage("Movie not found");
		responsestructure.setData(null);
		return new ResponseEntity<ResponseStructure<String>>(responsestructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ShowAlreadyExistException.class)
	public ResponseEntity<ResponseStructure<String>> handlerShowAlreadyExistException() {
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatuscode(HttpStatus.FOUND.value());
		responseStructure.setMessage("show already exist");
		responseStructure.setData("Already exist");
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.FOUND);
	}
	
	@ExceptionHandler(ShowNotFoundByIdException.class)
	public ResponseEntity<ResponseStructure<String>> handelrshowNotFoundByIdException() {
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("show Not Found");
		responseStructure.setData(null);
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MoviesNotAvailableException.class)
	public ResponseStructure<String> handelrMoviesNotAvailableException() {
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("movie Not Found");
		responseStructure.setData(null);
		return responseStructure;
	}
	
	@ExceptionHandler(SeatsNotFoundException.class)
	public ResponseStructure<String> handlerSeatsNotFoundException() {
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Seats Not found ");
		responseStructure.setData(null);
		return responseStructure;
	}
	
	@ExceptionHandler(SeatsAlreadyBookedException.class)
	public ResponseStructure<String> handlerSeatsAlreadyBookedException() {
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatuscode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Seats Already Exist ");
		responseStructure.setData(null);
		return responseStructure;
	}
}
