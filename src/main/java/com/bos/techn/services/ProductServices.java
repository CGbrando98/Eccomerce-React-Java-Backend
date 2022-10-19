package com.bos.techn.services;

import java.util.*; 

import com.bos.techn.beans.*; 
import com.bos.techn.exceptions.*;


public interface ProductServices {
	Product saveProduct(Product product) throws SavingDataException;
	Product getProduct(int id) throws ProductNotFoundException;
	void deleteProduct(int id) throws ProductNotFoundException;
	void updateProduct(Product product, int id) throws ProductNotFoundException;
	Map<String, Object> getProducts(int page);
	void saveBulkProducts(List<Product> products);
	Map<String, Object> getProductsByName(int page, String keyword);
	List<Product> getProductsByRating();
}
