package com.bos.techn.beans;

import java.time.*; 

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name="review")
@EntityListeners(AuditingEntityListener.class)
public class Review {
	
	//there is a problem with getting reviews
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_review;
	private double rating;
	private String comment;
	
	@CreatedDate
	private Instant reviewCreatedDate;
	@LastModifiedDate
	private Instant reviewLastModifiedDate;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name="id_user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User reviewUser;
	
//	@ManyToOne(fetch = FetchType.LAZY,optional = false)
//	@JoinColumn(name="id_product")
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	private Product reviewProduct;

	public int getId_review() {
		return id_review;
	}

	public void setId_review(int id_review) {
		this.id_review = id_review;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getReviewUser() {
		return reviewUser;
	}

	public void setReviewUser(User reviewUser) {
		this.reviewUser = reviewUser;
	}

//	public Product getReviewProduct() {
//		return reviewProduct;
//	}
//
//	public void setReviewProduct(Product reviewProduct) {
//		this.reviewProduct = reviewProduct;
//	}

	public Review(double rating, String comment, User reviewUser, Product reviewProduct) {
		super();
		this.rating = rating;
		this.comment = comment;
		this.reviewUser = reviewUser;
//		this.reviewProduct = reviewProduct;
	}

	public Review() {
		super();
	}
	
}
