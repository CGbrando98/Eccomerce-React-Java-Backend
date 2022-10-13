package com.bos.techn.beans;

import java.time.*; 
import java.util.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.*;

import com.fasterxml.jackson.annotation.*;

import lombok.*;

@Entity
@Table(name = "product")
// added for auditing - letting spring update metadata
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter 
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_product;
	private String name;
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
	private Date productCreatedDate;
	@LastModifiedDate
	private Date productLastModifiedDate;
	
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
	
	@Transient
	private int userid;

	public Product(String productname, String image, String description, String brand, String category, double price,
			int stock, double avgrating, int reviews, Date productCreatedDate, Date productLastModifiedDate,
			User productUser, List<Review> productReviews) {
		super();
		this.name = name;
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
}
