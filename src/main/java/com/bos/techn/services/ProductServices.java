package com.bos.techn.services;

import java.util.*;

import com.bos.techn.beans.*; 
import com.bos.techn.exceptions.*;
import com.cg.onlineshop.exceptions.*;

public interface ProductServices {
	Product saveProduct(Product product) throws SavingDataException;
	Product getProduct(int id) throws ProductNotFoundException;
	void deleteProduct(int id) throws ProductNotFoundException;
	void updateProduct(Product product, int id) throws ProductNotFoundException;
	List<Product> getProducts();
	void saveBulkProducts(List<Product> products);
}
