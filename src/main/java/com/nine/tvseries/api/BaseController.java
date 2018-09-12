package com.nine.tvseries.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nine.tvseries.api.exception.BadRequestException;
import com.nine.tvseries.api.exception.ResourceNotFoundException;
import com.nine.tvseries.api.exception.ValidationError;
import com.nine.tvseries.api.exception.ValidationErrorBuilder;
import com.nine.tvseries.api.messages.ErrorResponse;

public class BaseController {

	private static final Logger log = LoggerFactory.getLogger(BaseController.class);

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ValidationError handleException(MethodArgumentNotValidException exception) {
		log.error("Handle Bad Request exception - " + exception.getMessage());
		exception.printStackTrace();
		return createValidationError(exception);
	}

	private ValidationError createValidationError(MethodArgumentNotValidException exception) {
		return ValidationErrorBuilder.fromBindingErrors(exception.getBindingResult());
	}

	@ExceptionHandler(value = { BadRequestException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleException(BadRequestException exception) {
		log.error("Handle BadRequestException - " + exception.getMessage());
		exception.printStackTrace();
		return new ErrorResponse(exception.getMessage());
	}

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse handleException(ResourceNotFoundException exception) {
		log.error("Handle ResourceNotFoundException - " + exception.getMessage());
		exception.printStackTrace();
		return new ErrorResponse("The requested resource could not be found");
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleException(Exception exception) {
		log.error("Handle all other exception - " + exception.getMessage());
		exception.printStackTrace();
		return new ErrorResponse("Could not decode request: JSON parsing failed");
	}

}
