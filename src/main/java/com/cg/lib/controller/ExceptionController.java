package com.cg.lib.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.lib.dto.ExceptionResponse;
import com.cg.lib.exception.AlreadyExistsException;
import com.cg.lib.exception.BookNotFoundException;
import com.cg.lib.exception.InvalidArgumentException;
import com.cg.lib.exception.InvalidCredentialsException;
import com.cg.lib.exception.UserNotFoundException;


/*Implemented(Overriden) methods in this class are present in 
 * ResponseEntityExceptionHandler class. This class must extend it.
 */

@RestControllerAdvice
@CrossOrigin("http://localhost:4200")
public class ExceptionController {
	
	
	
	@ExceptionHandler(value = {BookNotFoundException.class,
			AlreadyExistsException.class, UserNotFoundException.class})
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionResponse handleNotFoundException(Exception ex, HttpServletRequest request) {
		
		return new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage(), request.getRequestURI());
		
	}
	
	@ExceptionHandler(value= {InvalidArgumentException.class, 
			InvalidCredentialsException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleMethodArgumentNotValid(Exception ex, 
			HttpServletRequest request) {
		
		return new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage(), request.getRequestURI());
		
	}
	
	@ExceptionHandler(value = {Exception.class})
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse handleOtherException(Exception ex, HttpServletRequest request) {
		
		return new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND.value(), 
				HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage(), request.getRequestURI());
		
	}

}
