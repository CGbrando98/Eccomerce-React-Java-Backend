package com.bos.techn.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.bos.techn.beans.*;
import com.bos.techn.exceptions.*;
import com.bos.techn.services.*;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class ReviewController {
	
	@Autowired
	private ReviewServices reviewServices;

	@PostMapping("/products/{productid}/reviews")
	public ResponseEntity<String> addReview(@RequestBody Review review, @PathVariable int productid) {
		reviewServices.saveReview(review, productid);
		return new ResponseEntity<>("review added", HttpStatus.OK);
	}
	
	@GetMapping("/products/{productid}/reviews/{reviewid}")
	public ResponseEntity<Review> searchReview(@PathVariable int reviewid) throws ReviewNotFoundException{
		System.out.println(reviewid);
		Review review = reviewServices.getReview(reviewid);
		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}
	
	@DeleteMapping("products/{productid}/reviews/{reviewid}")
	public ResponseEntity<String> removeReview( @PathVariable int reviewid) throws ReviewNotFoundException{
		reviewServices.deleteReview(reviewid);
		return new ResponseEntity<>("review deleted", HttpStatus.OK);
	}
}
