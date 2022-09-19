package com.bos.techn.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.bos.techn.beans.*;
import com.bos.techn.exceptions.*;
import com.bos.techn.services.*;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class CartController {
	
	@Autowired
	private CartServices cartServices;

	@PostMapping("/carts")
	public ResponseEntity<String> addCart(@RequestBody Cart cart) {
		cartServices.saveCart(cart);
		
		return new ResponseEntity<>("Cart added", HttpStatus.OK);
	}
	
	@GetMapping("/carts/{cartid}")
	public ResponseEntity<Cart> searchCart(@PathVariable int cartid) throws CartNotFoundException{
		Cart cart = cartServices.getCart(cartid);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	@DeleteMapping("/carts/{cartid}")
	public ResponseEntity<String> removeCart( @PathVariable int cartid) throws CartNotFoundException{
		cartServices.deleteCart(cartid);
		return new ResponseEntity<>("Cart deleted", HttpStatus.OK);
	}
}
