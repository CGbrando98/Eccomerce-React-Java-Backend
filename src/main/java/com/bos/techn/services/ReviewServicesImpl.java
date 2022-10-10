package com.bos.techn.services;

import java.util.*; 
import java.util.function.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.bos.techn.*;
import com.bos.techn.beans.*;
import com.bos.techn.daos.*;
import com.bos.techn.exceptions.*;

@Component
public class ReviewServicesImpl implements ReviewServices{
	
	@Autowired
	private ReviewDAO reviewDao;
	
	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private UserDAO userDao;
	
	@Override
	public void saveReview(Review review, int productid) {
		try {
			Optional<User> optionalUser = userDao.findById(TechnApplication.userIDValue);
			Supplier<UserNotFoundException> exceptionSupplierUser = () -> new 
					UserNotFoundException("User not found for id");

			review.setReviewUser(optionalUser.orElseThrow(exceptionSupplierUser));
			
			productDao.findById(productid).map(product -> {
				product.getProductReviews().add(review);
				return reviewDao.save(review);
						}).orElseThrow(
						() -> new ProductNotFoundException("Product Not found with id"));

		} catch (Exception e) {
			System.out.println(e);
			System.err.println("Error in saving");
		}
	}

	@Override
	public Review getReview(int id) throws ReviewNotFoundException {
		Optional<Review> optional = reviewDao.findById(id);
		Supplier<ReviewNotFoundException> exceptionSupplier = () -> new 
				ReviewNotFoundException("Review not found for id: "+id);
		return optional.orElseThrow(exceptionSupplier);
	}

	@Override
	public void deleteReview(int id) throws ReviewNotFoundException {
		try {
			reviewDao.deleteById(id);
		}
		catch (Exception e) {
			System.out.println(e);
			throw new ReviewNotFoundException("Could not find Review to delete with "
					+ "id: "+ id);
		}
	}
}
