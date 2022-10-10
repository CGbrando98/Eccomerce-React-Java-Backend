package com.bos.techn.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaypalConfigController {
	@GetMapping("/config/paypal")
	public ResponseEntity<String> paypalConfig() {
		// put this is a the app properties
		System.out.println("hit");
		String clientId = "AbkyavQTtqe7bDIpg7qF16gE1AzHgcbYD8BgGS61La"
				+ "m8xOLx-M1Bze0KdbOAsCTQL_vTKephOZuY2vza";
		return new ResponseEntity<>(clientId, HttpStatus.OK);
	}
}
