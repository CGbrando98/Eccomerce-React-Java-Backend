package com.bos.techn.controllers;

import java.io.*; 
import java.text.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import com.bos.techn.beans.*;
import com.bos.techn.exceptions.*;
import com.bos.techn.services.*;
import com.cloudinary.utils.*;

import com.cloudinary.Cloudinary;

@RestController
public class ProductController {
	
	// magical instantiation
	@Autowired
	private ProductServices productServices;
	
	 Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
	    		//hide this info
	    "cloud_name", "da6su05rx",
	    "api_key", "658295152147355",
	    "api_secret", "uESbpI7kfembW62YMJtirgH4BfE",
	    "secure", true));
	
	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) throws SavingDataException {
		Product savedProduct = productServices.saveProduct(product);
		return new ResponseEntity<>(savedProduct, HttpStatus.OK);
	}
	
	@GetMapping("/products/{productid}")
	public ResponseEntity<Product> searchProduct(@PathVariable int productid) throws ProductNotFoundException {
		Product product = productServices.getProduct(productid);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@GetMapping("/products/query")
	public ResponseEntity<Map<String, Object>> queryProduct(@RequestParam(name="page") int page,@RequestParam(name="keyword") String keyword) throws ProductNotFoundException {
		Map<String, Object> productsAndPages = productServices.getProductsByName(page,keyword);
		return new ResponseEntity<Map<String, Object>>(productsAndPages,HttpStatus.OK);
	}
	
	@GetMapping("/products/top")
	public ResponseEntity<List<Product>> topProduct() throws ProductNotFoundException {
		List<Product> topProducts = productServices.getProductsByRating();
		return new ResponseEntity<List<Product>>(topProducts,HttpStatus.OK);
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
	public ResponseEntity<Map<String, Object>> searchProducts(@RequestParam(name="page") int page){
		Map<String, Object> productsAndPages = productServices.getProducts(page);
		return new ResponseEntity<Map<String, Object>>(productsAndPages,HttpStatus.OK);
	}
	
	@PostMapping("/products/bulk")
	public ResponseEntity<String> addProducts(@RequestBody List<Product> products) {
		productServices.saveBulkProducts(products);
		return new ResponseEntity<>("Products added", HttpStatus.OK);
	}
	
	
	//img upload
	@PostMapping("/upload")
	public ResponseEntity<Map> upload(@RequestParam("image") MultipartFile multipartFile) throws IOException, ParseException {
		Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
		System.out.println(uploadResult);
		return new ResponseEntity<>(uploadResult, HttpStatus.OK);
	}
	
}
