package com.bos.techn.services;

import java.util.*; 

import com.bos.techn.beans.*; 
import com.bos.techn.exceptions.*;


public interface ProductServices {
	Product saveProduct(Product product) throws SavingDataException;
	Product getProduct(UUID productid) throws ProductNotFoundException;
	void deleteProduct(UUID productid) throws ProductNotFoundException;
	Product updateProduct(Product product, UUID productid) throws ProductNotFoundException;
	Map<String, Object> getProducts(int page);
	void saveBulkProducts(List<Product> products);
	Map<String, Object> getProductsByName(int page, String keyword);
	List<Product> getProductsByRating();
}
