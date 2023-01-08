package com.bos.techn.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.bos.techn.beans.*;
import com.bos.techn.exceptions.*;
import com.bos.techn.services.*;

@RestController
public class ReviewController {
	
	@Autowired
	private ReviewServices reviewServices;

	@PostMapping("/products/{productid}/reviews")
	public ResponseEntity<Review> addReview(@RequestBody Review review, @PathVariable UUID productid) throws SavingDataException{
		Review savedReview = reviewServices.saveReview(review, productid);
		return new ResponseEntity<>(savedReview, HttpStatus.OK);
	}
	
	@GetMapping("/products/{productid}/reviews/{reviewid}")
	public ResponseEntity<Review> searchReview(@PathVariable UUID reviewid) throws ReviewNotFoundException{
		Review review = reviewServices.getReview(reviewid);
		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}
	
	@DeleteMapping("products/{productid}/reviews/{reviewid}")
	public ResponseEntity<String> removeReview( @PathVariable UUID reviewid) throws ReviewNotFoundException{
		reviewServices.deleteReview(reviewid);
		return new ResponseEntity<>("review deleted", HttpStatus.OK);
	}
}
