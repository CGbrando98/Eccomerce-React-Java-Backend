package com.bos.techn.exceptions;

import javax.persistence.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.*;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  
   @ExceptionHandler(SavingDataException.class)
   public ResponseEntity<ApiError> SavingDataException(
		   SavingDataException ex) {
       ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Error in saving data to DB");
       return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
   }
   
   @ExceptionHandler(org.springframework.security.authentication.BadCredentialsException.class)
   public ResponseEntity<ApiError> BadCredentialsException(
		   org.springframework.security.authentication.BadCredentialsException ex) {
       ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Incorrect Username or Password!");
       return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
   }
}