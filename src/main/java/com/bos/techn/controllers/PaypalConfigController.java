package com.bos.techn.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaypalConfigController {
	
	@Value("${spring.clientId}")
	String clientId;
	
	@GetMapping("/config/paypal")
	public ResponseEntity<String> paypalConfig() {
		return new ResponseEntity<>(clientId, HttpStatus.OK);
	}
}
