package br.com.victorcaselli.dscatolog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.victorcaselli.dscatolog.services.exceptions.DataBaseException;
import br.com.victorcaselli.dscatolog.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException enfe, HttpServletRequest request ){ 
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError se = new StandardError();
		se.setTimestamp(Instant.now());
		se.setStatus(status.value());
		se.setError("Resource not found");
		se.setMessage(enfe.getMessage());
		se.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(se);
	}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> databaseIntegrity(DataBaseException e, HttpServletRequest request ){ 
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError se = new StandardError();
		se.setTimestamp(Instant.now());
		se.setStatus(status.value());
		se.setError("Database exception");
		se.setMessage(e.getMessage());
		se.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(se);
	}
	
	
	

}
