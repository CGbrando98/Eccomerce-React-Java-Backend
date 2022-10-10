package com.bos.techn.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.bos.techn.beans.*;
import com.bos.techn.exceptions.*;
import com.bos.techn.services.*;

@RestController
public class OrderController {
	
	@Autowired
	private OrderServices orderServices;

	@PostMapping("/orders")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) throws SavingDataException {
		Order savedOrder = orderServices.saveOrder(order);
		return new ResponseEntity<>(savedOrder, HttpStatus.OK);
	}
	
	@GetMapping("/orders/{orderid}")
	public ResponseEntity<Order> searchOrder(@PathVariable int orderid) throws OrderNotFoundException{
		Order order = orderServices.getOrder(orderid);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@DeleteMapping("/orders/{orderid}")
	public ResponseEntity<String> removeOrder( @PathVariable int orderid) throws OrderNotFoundException{
		orderServices.deleteOrder(orderid);
		return new ResponseEntity<>("Order deleted", HttpStatus.OK);
	}
}
