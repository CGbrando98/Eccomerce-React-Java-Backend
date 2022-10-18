package com.bos.techn.services;

import com.bos.techn.beans.*;
import com.bos.techn.exceptions.*;

public interface ReviewServices {
	Review saveReview(Review review, int productid) throws SavingDataException;
	Review getReview(int id) throws ReviewNotFoundException;
	void deleteReview(int id) throws ReviewNotFoundException;
}
