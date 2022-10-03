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

import lombok.*;

@Entity
@Table(name="review")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter 
@NoArgsConstructor
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
	
	public Review(double rating, String comment, User reviewUser, Product reviewProduct) {
		super();
		this.rating = rating;
		this.comment = comment;
		this.reviewUser = reviewUser;
	}
}
