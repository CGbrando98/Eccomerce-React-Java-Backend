package com.bos.techn.services;

import com.bos.techn.beans.*;
import com.bos.techn.exceptions.*;

public interface ReviewServices {
	void saveReview(Review review, int productid);
	Review getReview(int id) throws ReviewNotFoundException;
	void deleteReview(int id) throws ReviewNotFoundException;
}
