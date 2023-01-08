package com.bos.techn.services;

import java.util.*; 
import java.util.function.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.bos.techn.*;
import com.bos.techn.beans.*;
import com.bos.techn.daos.*;
import com.bos.techn.exceptions.*;


//identify the class as a spring bean
@Component
public class ProductServicesImpl implements ProductServices{
	
	// instantiation of the ProductDAO class
	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private UserDAO userDao;
	
	int pageSize = 4;
	
	@Override
	public Product saveProduct(Product product) throws SavingDataException {
		try {
			Optional<User> optional = userDao.findById(product.getUserid());
			Supplier<UserNotFoundException> exceptionSupplier = () -> new 
					UserNotFoundException("User not found for id");

			product.setProductUser(optional.orElseThrow(exceptionSupplier));
			
			Product savedProduct = productDao.save(product);
			savedProduct.getProductUser().setPassword(null);
			return savedProduct;
			
		} catch (Exception e) {
			throw new SavingDataException("Error in saving product data");
		}
	}
	
	public Product getProduct(UUID id) throws ProductNotFoundException {
		Optional<Product> optional = productDao.findById(id);
		// lambda function 
		Supplier<ProductNotFoundException> exceptionSupplier = () -> new 
				ProductNotFoundException("Product not found for id: "+id);
		
		System.out.println(optional.get());
		optional.get().getProductUser().setPassword(null);
		System.out.println(optional.get());
		return optional.orElseThrow(exceptionSupplier);
	}
	
	@Override
	public Product updateProduct(Product newProduct, UUID id) throws ProductNotFoundException {
		try {
			Optional<User> optional = userDao.findById(newProduct.getUserid());
			Supplier<UserNotFoundException> exceptionSupplier = () -> new 
					UserNotFoundException("User not found for id");
			
			System.out.println(newProduct);
			if (newProduct.getProductReviews() == null) {
				newProduct.setProductReviews(Collections.<Review> emptyList());
			}


			newProduct.setProductUser(optional.orElseThrow(exceptionSupplier));
			newProduct.setId_product(id);
			
			Product savedProduct = productDao.save(newProduct);
			savedProduct.getProductUser().setPassword(null);
			return savedProduct;
			} catch (Exception e) {
				throw new ProductNotFoundException("Could not find Product to update with "
						+ "id: "+ id);
			}
		}
	
	@Override
	public List<Product> getProductsByRating() {
		List<Product> products = productDao.findTop3ByOrderByAvgratingAsc();
		for (int i = 0; i<products.size();i++) {
			products.get(i).getProductUser().setPassword(null);
		}
		return products;
	}

	@Override
	public void deleteProduct(UUID id) throws ProductNotFoundException {
		try {
			productDao.deleteById(id);
		}
		catch (Exception e) {
			throw new ProductNotFoundException("Could not find Product to delete with "
					+ "id: "+ id);
		}
	}
	
	@Override
	public Map<String, Object> getProductsByName(int page,String keyword) {
		int numRecords = (int) productDao.findByNameLike("%"+keyword+"%").size();
		 Map<String, Object> productsAndPages = new HashMap<>();
		 
		 List<Product> products = productDao.findByNameLike("%"+keyword+"%");
		 for (int i = 0; i<products.size(); i++) {
			 products.get(i).getProductUser().setPassword(null);
		 }
		
		 if (numRecords<pageSize*page) {
				 productsAndPages.put("products", products.subList(pageSize*(page-1), numRecords));
		 } else {
			 productsAndPages.put("products", products.subList(pageSize*(page-1), pageSize*page));
		 }
		 
		 productsAndPages.put("pages",Math.ceil((double) (products.size())/((double) pageSize)));
		 productsAndPages.put("page", page);
		 System.out.println(productsAndPages.get("pages"));
		 return productsAndPages;
	}
	

	@Override
	public Map<String, Object> getProducts(int page) {
		 int numRecords = (int) productDao.count();
		 Map<String, Object> productsAndPages = new HashMap<>();
		 
		 List<Product> products = productDao.findAll();
		 for (int i = 0; i<products.size(); i++) {
//			 products.get(i).getProductUser().setPassword(null);
		 }
		 
		 if (numRecords<pageSize*page) {
			 productsAndPages.put("products", products.subList(pageSize*(page-1), numRecords));
		 } else {
			 productsAndPages.put("products", products.subList(pageSize*(page-1), pageSize*page));
		 }
		 
		 productsAndPages.put("pages", Math.ceil((double)productDao.count()/(double) pageSize));
		 productsAndPages.put("page", page);
		return productsAndPages;
	}

	@Override
	public void saveBulkProducts(List<Product> products) {
		try {
			Optional<User> optional = userDao.findById(UUID.fromString("3f809574-c484-48ab-912a-3984caaffc79"));
			Supplier<UserNotFoundException> exceptionSupplier = () -> new 
					UserNotFoundException("User not found for id");
			products.forEach(p -> {
				try {
					p.setProductUser(optional.orElseThrow(exceptionSupplier));
				} catch (UserNotFoundException e) {
					e.printStackTrace();
				}
			});
			productDao.saveAll(products);
		} catch (Exception e) {
		System.err.println("Error in saving");
		}
	}

}
