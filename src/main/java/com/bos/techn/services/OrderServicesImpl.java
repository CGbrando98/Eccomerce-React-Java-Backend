package com.bos.techn.services;

import java.time.*;
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
public class OrderServicesImpl implements OrderServices{
	
	@Autowired
	private OrderDAO orderDao;
	
	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private ItemDAO itemDao;
	
	@Autowired
	private UserDAO userDao;
	
	@Override
	public Order saveOrder(Order order) throws SavingDataException {
		try {
			
			Supplier<UserNotFoundException> userExceptionSupplier = () -> new 
					UserNotFoundException("User not found for id");
			
			Supplier<ProductNotFoundException> productExceptionSupplier = () -> new 
					ProductNotFoundException("Product not found for id");
			
			Optional<User> optionalUser = userDao.findById(order.getUserid());
			order.setUser(optionalUser.orElseThrow(userExceptionSupplier));
			
			List<Item> orderItems = order.getItems();
			for (int i=0; i<orderItems.size();i++) {
				Optional<Product> optionalProduct = productDao.findById(orderItems.get(i).getProduct());
				orderItems.get(i).setItemProduct(optionalProduct.orElseThrow(productExceptionSupplier));
			}
				
			order.setItems(orderItems);
			Order savedOrder = orderDao.save(order);
			savedOrder.getUser().setPassword(null);
			return savedOrder;
		} catch (Exception e) {
			throw new SavingDataException();
		}
	}
	
	@Override
	public List<Order> getOrders() {
		List<Order> orders = orderDao.findAll();
		for (int i =0; i<orders.size(); i++) {
			orders.get(i).getUser().setPassword(null);
		}
		return orders;
	}

	@Override
	public Order getOrder(UUID id) throws OrderNotFoundException {
		Optional<Order> optional = orderDao.findById(id);
		
		Supplier<OrderNotFoundException> exceptionSupplier = () -> new 
				OrderNotFoundException("Order not found for id: "+id);
		
		optional.get().getUser().setPassword(null);
		return optional.orElseThrow(exceptionSupplier);
	}
	
	@Override
	public Order payOrder(UUID id, PaymentResult payment ) throws OrderNotFoundException {
		try {
			Optional<Order> optional = orderDao.findById(id);
			Supplier<OrderNotFoundException> exceptionSupplier = () -> new 
					OrderNotFoundException("Order not found for id: "+id);

			optional.get().getPayment().setId_paymentpaypal(payment.getId_paymentpaypal());
			optional.get().getPayment().setPaymentemail(payment.getPaymentemail());
			optional.get().getPayment().setPaymentstatus(payment.getPaymentstatus());
			optional.get().getPayment().setPaymentupdatetime(payment.getPaymentupdatetime());
			optional.get().setIspaid(true);
			optional.get().setPaidat(LocalDateTime.now());
			
			Order savedOrder = orderDao.save(optional.get());
			savedOrder.getUser().setPassword(null);
			return savedOrder;

			} catch (Exception e) {
				throw new OrderNotFoundException("Could not find Order to update with "
						+ "id: "+ id);
			}
		}
	
	@Override
	public Order deliverOrder(UUID id) {
		Optional<Order> optional = orderDao.findById(id);
		Supplier<OrderNotFoundException> exceptionSupplier = () -> new 
				OrderNotFoundException("Order not found for id: "+id);
		
		optional.get().setIsdelivered(true);
		optional.get().setDeliveredat(LocalDateTime.now());
		
		Order savedOrder = orderDao.save(optional.get());
		savedOrder.getUser().setPassword(null);
		return savedOrder; 
	}

	@Override
	public List<Order> getOrdersByUserId(UUID userid) {
		List<Order> orders = orderDao.findAllByUserId(userid);
		for (int i =0; i<orders.size(); i++) {
			orders.get(i).getUser().setPassword(null);
		}
		return orders;
	}

	@Override
	public void deleteOrder(UUID id) throws OrderNotFoundException {
		try {
			orderDao.deleteById(id);
		}
		catch (Exception e) {
			System.out.println(e);
			throw new OrderNotFoundException("Could not find Order to delete with "
					+ "id: "+ id);
		}
	}



}
