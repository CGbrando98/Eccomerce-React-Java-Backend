package com.bos.techn.services;

import java.util.*;

import com.bos.techn.beans.*;
import com.bos.techn.exceptions.*;

public interface OrderServices {
	Order saveOrder(Order cart) throws SavingDataException;
	Order getOrder(UUID orderid) throws OrderNotFoundException;
	void deleteOrder(UUID orderid) throws OrderNotFoundException;
	Order payOrder(UUID orderid, PaymentResult payment) throws OrderNotFoundException;
	Order deliverOrder(UUID orderid);
	List<Order> getOrdersByUserId(UUID userid);
	List<Order> getOrders();
}
