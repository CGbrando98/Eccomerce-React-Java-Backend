package com.bos.techn.services;

import java.util.*;
import java.util.function.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.bos.techn.beans.*;
import com.bos.techn.daos.*;
import com.bos.techn.exceptions.*;

@Component
public class CartServicesImpl implements CartServices{
	
	@Autowired
	private CartDAO cartDao;
	
	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private ItemDAO itemDao;
	
	@Override
	public void saveCart(Cart cart) {
		try {
			Optional<Product> optionalProduct = productDao.findById(56);
			Supplier<ProductNotFoundException> exceptionSupplier = () -> new 
					ProductNotFoundException("Product not found for id");
			
			// get the list of items, modify it and append it backl to the cart obj
			List<Item> cartItems = cart.getItems();
			cartItems.get(0).setItemProduct(optionalProduct.orElseThrow(exceptionSupplier));
			cartItems.get(1).setItemProduct(optionalProduct.orElseThrow(exceptionSupplier));
			cart.setItems(cartItems);
			
			cartDao.save(cart);
		} catch (Exception e) {
			System.out.println(e);
			System.err.println("Error in saving");
		}
	}

	@Override
	public Cart getCart(int id) throws CartNotFoundException {
		Optional<Cart> optional = cartDao.findById(id);
		// lambda function 
		Supplier<CartNotFoundException> exceptionSupplier = () -> new 
				CartNotFoundException("Cart not found for id: "+id);
		return optional.orElseThrow(exceptionSupplier);
	}

	@Override
	public void deleteCart(int id) throws CartNotFoundException {
		try {
			cartDao.deleteById(id);
		}
		catch (Exception e) {
			System.out.println(e);
			throw new CartNotFoundException("Could not find Cart to delete with "
					+ "id: "+ id);
		}
	}
}
