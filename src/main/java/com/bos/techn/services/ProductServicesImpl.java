package com.bos.techn.services;

import java.util.*;
import java.util.function.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*; 

import com.bos.techn.beans.*;
import com.bos.techn.daos.*;
import com.bos.techn.exceptions.*;
import com.cg.onlineshop.daos.*;
import com.cg.onlineshop.exceptions.*;
import com.cg.onlineshop.pojos.*;

//identify the class as a spring bean
@Component
public class ProductServicesImpl implements ProductServices{
	
	// instantiation of the ProductDAO class
	@Autowired
	private ProductDAO productDao;
	
	@Override
	public void saveProduct(Product product) {
		try {
			product = productDao.save(product);
		} catch (Exception e) {
			System.err.println("Error in saving");
		}
	}
	
	public Product getProduct(int id) throws ProductNotFoundException {
		Optional<Product> optional = productDao.findById(id);
		// lambda function 
		Supplier<ProductNotFoundException> exceptionSupplier = () -> new 
				ProductNotFoundException("Product not found for id: "+id);
		return optional.orElseThrow(exceptionSupplier);
	}
	
	@Override
	public void updateProduct(Product newProduct, int id) throws ProductNotFoundException {
		try {
			newProduct.setId(id);
			productDao.save(newProduct);
			} catch (Exception e) {
				throw new ProductNotFoundException("Could not find Product to delete with "
						+ "id: "+ id);
			}
		}

	@Override
	public void deleteProduct(int id) throws ProductNotFoundException {
		try {
			productDao.deleteById(id);
		}
		catch (Exception e) {
			throw new ProductNotFoundException("Could not find Product to delete with "
					+ "id: "+ id);
		}
	}

	@Override
	public List<Product> getProducts() {
		return productDao.findAll();
	}

	@Override
	public void saveBulkProducts(List<Product> products) {
		try {
			productDao.saveAll(products);
		} catch (Exception e) {
		System.err.println("Error in saving");
		}
	}
	
}
