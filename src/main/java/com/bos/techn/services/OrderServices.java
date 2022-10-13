package com.bos.techn.services;

import java.util.*;

import com.bos.techn.beans.*;
import com.bos.techn.exceptions.*;

public interface OrderServices {
	Order saveOrder(Order cart) throws SavingDataException;
	Order getOrder(int id) throws OrderNotFoundException;
	void deleteOrder(int id) throws OrderNotFoundException;
	Order payOrder(int id, PaymentResult payment) throws OrderNotFoundException;
	List<Order> getOrdersByUserId(int userid);
}
