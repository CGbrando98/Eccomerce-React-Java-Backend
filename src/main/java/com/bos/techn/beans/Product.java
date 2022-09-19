package com.bos.techn.beans;

import java.time.*; 
import java.util.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "product")
// added for auditing - letting spring update metadata
@EntityListeners(AuditingEntityListener.class)
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_product;
	private String productname;
	private String image;
	private String description;
	private String brand;
	private String category;
	private double price;
	// number in stock
	private int stock;
	private double avgrating;
	// number of reviews
	private int reviews;
	@CreatedDate
	private Instant productCreatedDate;
	@LastModifiedDate
	private Instant productLastModifiedDate;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	// here name is the one mapping
	@JoinColumn(name="id_user")
	// deleting a user deletes there products
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User productUser;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	// add product_id to reviews table
	@JoinColumn(name="id_product")
	private List<Review> productReviews;

	public int getId_product() {
		return id_product;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getAvgrating() {
		return avgrating;
	}

	public void setAvgrating(double avgrating) {
		this.avgrating = avgrating;
	}

	public int getReviews() {
		return reviews;
	}

	public void setReviews(int reviews) {
		this.reviews = reviews;
	}

	public Instant getProductCreatedDate() {
		return productCreatedDate;
	}

	public void setProductCreatedDate(Instant productCreatedDate) {
		this.productCreatedDate = productCreatedDate;
	}

	public Instant getProductLastModifiedDate() {
		return productLastModifiedDate;
	}

	public void setProductLastModifiedDate(Instant productLastModifiedDate) {
		this.productLastModifiedDate = productLastModifiedDate;
	}

	public User getProductUser() {
		return productUser;
	}

	public void setProductUser(User productUser) {
		this.productUser = productUser;
	}

	public List<Review> getProductReviews() {
		return productReviews;
	}

	public void setProductReviews(List<Review> productReviews) {
		this.productReviews = productReviews;
	}

	public Product(String productname, String image, String description, String brand, String category, double price,
			int stock, double avgrating, int reviews, Instant productCreatedDate, Instant productLastModifiedDate,
			User productUser, List<Review> productReviews) {
		super();
		this.productname = productname;
		this.image = image;
		this.description = description;
		this.brand = brand;
		this.category = category;
		this.price = price;
		this.stock = stock;
		this.avgrating = avgrating;
		this.reviews = reviews;
		this.productCreatedDate = productCreatedDate;
		this.productLastModifiedDate = productLastModifiedDate;
		this.productUser = productUser;
		this.productReviews = productReviews;
	}

	public Product() {
		super();
	}

	
}
