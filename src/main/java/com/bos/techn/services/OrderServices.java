package com.bos.techn.services;

import com.bos.techn.beans.*;
import com.bos.techn.exceptions.*;

public interface OrderServices {
	Order saveOrder(Order cart) throws SavingDataException;
	Order getOrder(int id) throws OrderNotFoundException;
	void deleteOrder(int id) throws OrderNotFoundException;
}
