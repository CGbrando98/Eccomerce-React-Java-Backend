package com.bos.techn.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.bos.techn.beans.*;
import com.bos.techn.exceptions.*;
import com.bos.techn.services.*;

@RestController
public class ProductController {
	
	// magical instantiation
	@Autowired
	private ProductServices productServices;
	
	@PostMapping("/products")
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		productServices.saveProduct(product);
		return new ResponseEntity<>("Product details added", HttpStatus.OK);
	}
	
	@GetMapping("/products/{productid}")
	public ResponseEntity<Product> searchProduct(@PathVariable int productid) throws ProductNotFoundException {
		Product product = productServices.getProduct(productid);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@PutMapping("/products/{productid}")
	public ResponseEntity<String> changeProduct(@RequestBody Product product, @PathVariable int productid) 
			throws ProductNotFoundException{
		productServices.updateProduct(product, productid);
		return new ResponseEntity<>("Product details updated", HttpStatus.OK);
	}
	
	@DeleteMapping("/products/{productid}")
	public ResponseEntity<String> removeProduct( @PathVariable int productid) throws ProductNotFoundException{
		productServices.deleteProduct(productid);
		return new ResponseEntity<>("Product deleted", HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> searchProducts(){
		List<Product> products = productServices.getProducts();
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@PostMapping("/products/bulk")
	public ResponseEntity<String> addProducts(@RequestBody List<Product> products) {
		productServices.saveBulkProducts(products);
		return new ResponseEntity<>("Products added", HttpStatus.OK);
	}
	
}
