package com.acruent.mobile.globalexceptionhandle;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandle {

	@ExceptionHandler(MobileCreationException.class)
	public ResponseEntity<ExceptionMessage> handleMobileCreationException(
			MobileCreationException mobileCreationException, WebRequest webRequest) {
		ExceptionMessage message = new ExceptionMessage(new Date(), mobileCreationException.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MobileNotFoundException.class)
	public ResponseEntity<ExceptionMessage> handleMobileNotFoundException(
			MobileNotFoundException mobileNotFoundException, WebRequest webRequest) {
		ExceptionMessage message = new ExceptionMessage(new Date(), mobileNotFoundException.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MobileUpdateException.class)
	public ResponseEntity<ExceptionMessage> handleMobileUpdateException(MobileUpdateException mobileUpdateException,
			WebRequest webRequest) {
		ExceptionMessage message = new ExceptionMessage(new Date(), mobileUpdateException.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MobileDeletionException.class)
	public ResponseEntity<ExceptionMessage> handleMobileDeletionException(
			MobileDeletionException mobileDeletionException, WebRequest webRequest) {
		ExceptionMessage message = new ExceptionMessage(new Date(), mobileDeletionException.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SimCreationException.class)
	public ResponseEntity<ExceptionMessage> handleSimCreationException(SimCreationException simCreationException,
			WebRequest webRequest) {
		ExceptionMessage message = new ExceptionMessage(new Date(), simCreationException.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SimNotFoundException.class)
	public ResponseEntity<ExceptionMessage> handleSimNotFoundException(SimNotFoundException simNotFoundException,
			WebRequest webRequest) {
		ExceptionMessage message = new ExceptionMessage(new Date(), simNotFoundException.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SimUpdateException.class)
	public ResponseEntity<ExceptionMessage> handleSimUpdateException(SimUpdateException simUpdateException,
			WebRequest webRequest) {
		ExceptionMessage message = new ExceptionMessage(new Date(), simUpdateException.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(SimDeletionException.class)
	public ResponseEntity<ExceptionMessage> handelSimDeletionException(SimDeletionException simDeletionException,
			WebRequest webRequest) {
		ExceptionMessage message = new ExceptionMessage(new Date(), simDeletionException.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

}
