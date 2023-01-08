package com.bos.techn.services;

import java.util.*;

import com.bos.techn.beans.*;
import com.bos.techn.exceptions.*;

public interface ReviewServices {
	Review saveReview(Review review, UUID productid) throws SavingDataException;
	Review getReview(UUID id) throws ReviewNotFoundException;
	void deleteReview(UUID id) throws ReviewNotFoundException;
}
