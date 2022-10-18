package com.bos.techn.controllers;

import java.util.*;

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
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> searchOrders() {
		List<Order> orders = orderServices.getOrders();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}
	
	
	@GetMapping("/orders/profile/{userid}")
	public ResponseEntity<List<Order>> searchOrdersByUser(@PathVariable int userid) {
		System.out.println("profile orders");
		List<Order> orders = orderServices.getOrdersByUserId(userid);
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}
	
	@GetMapping("/orders/{orderid}")
	public ResponseEntity<Order> searchOrder(@PathVariable int orderid) throws OrderNotFoundException{
		Order order = orderServices.getOrder(orderid);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@PutMapping("/orders/{orderid}/pay")
	public ResponseEntity<Order> payOrder(@PathVariable int orderid,@RequestBody PaymentResult payment) throws OrderNotFoundException{
		Order order = orderServices.payOrder(orderid, payment);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@PutMapping("/orders/{orderid}/deliver")
	public ResponseEntity<Order> deliverOrder(@PathVariable int orderid) throws OrderNotFoundException{
		System.out.println("hit");
		Order order = orderServices.deliverOrder(orderid);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@DeleteMapping("/orders/{orderid}")
	public ResponseEntity<String> removeOrder( @PathVariable int orderid) throws OrderNotFoundException{
		orderServices.deleteOrder(orderid);
		return new ResponseEntity<>("Order deleted", HttpStatus.OK);
	}
}
