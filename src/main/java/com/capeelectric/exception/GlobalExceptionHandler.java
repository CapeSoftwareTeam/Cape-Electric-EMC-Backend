package com.capeelectric.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capeelectric.util.ErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ AuthenticationException.class })
	public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "404");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ ClientDetailsException.class })
	public ResponseEntity<ErrorMessage> handleClientDetailsException(ClientDetailsException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler({ FacilityDataException.class })
	public ResponseEntity<ErrorMessage> handleFacilityDataException(FacilityDataException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "404");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ PowerEarthingDataException.class })
	public ResponseEntity<ErrorMessage> handlePowerEarthingDataException(PowerEarthingDataException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "404");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ ElectromagneticCompatabilityException.class })
	public ResponseEntity<ErrorMessage> handleElectromagneticCompatabilityException(
			ElectromagneticCompatabilityException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "404");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
}
