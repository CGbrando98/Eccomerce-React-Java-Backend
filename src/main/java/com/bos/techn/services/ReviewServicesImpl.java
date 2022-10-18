package com.bos.techn.services;

import java.util.*; 
import java.util.function.*;
import java.util.stream.*;

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
	public Review saveReview(Review review, int productid) throws SavingDataException {
		try {
			
			Optional<Product> optionalProduct = productDao.findById(productid);
			Supplier<ProductNotFoundException> exceptionSupplierProduct = () -> new 
					ProductNotFoundException("Product not found");

			List<Review> reviews = optionalProduct.get().getProductReviews().stream().filter(r->
				r.getReviewUser().getId_user()==review.getUserid()).collect(Collectors.toList());

			if (reviews.isEmpty()) {
			
			Optional<User> optionalUser = userDao.findById(review.getUserid());
			Supplier<UserNotFoundException> exceptionSupplierUser = () -> new 
					UserNotFoundException("User not found for id");

			review.setReviewUser(optionalUser.orElseThrow(exceptionSupplierUser));
			
			updateAvgRatingAndReviews(review,optionalProduct);
			return reviewDao.save(review);}
			
			else {System.out.println("Review exists for user");}
				throw new SavingDataException();

		} catch (Exception e) {
			throw new SavingDataException();
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
	
	public void updateAvgRatingAndReviews(Review review,Optional<Product> optionalProduct) {
		double avg=0;
		double sum = 0;
		int numReviews = optionalProduct.get().getProductReviews().size();
		
		for (int i=0;i<optionalProduct.get().getProductReviews().size();i++) {
			sum = sum + optionalProduct.get().getProductReviews().get(i).getRating();
		}
		
		if (sum==0) {
			avg=review.getRating();
		} else {
			// dont forget about incomming review
			avg=(sum+review.getRating())/(numReviews+1);

		}
		
		optionalProduct.get().setAvgrating(avg);
		optionalProduct.get().setReviews(optionalProduct.get().getReviews()+1);
		optionalProduct.get().getProductReviews().add(review);

	}
}
