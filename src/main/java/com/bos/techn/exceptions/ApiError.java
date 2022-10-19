package com.bos.techn.exceptions;

import java.time.*;

import org.springframework.http.*;

import com.fasterxml.jackson.annotation.*;

import lombok.*;

@Getter
@Setter
class ApiError {

	   private HttpStatus status;
	   private String message;


	   ApiError(HttpStatus status) {
	       this.status = status;
	       this.message = "Unexpected Error";
	   }
	   
	   ApiError(HttpStatus status, String message) {
	       this.status = status;
	       this.message = message;
	   }
	}