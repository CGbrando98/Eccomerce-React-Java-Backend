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
@Table(name="review")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter 
@NoArgsConstructor
@ToString
public class Review {
	
	//there is a problem with getting reviews
	
	@Id
	@GeneratedValue
	@Type(type="uuid-char")
	private UUID id_review;
	
	private double rating;
	private String comment;
	
	@CreatedDate
	private Date reviewCreatedDate;
	@LastModifiedDate
	private Date reviewLastModifiedDate;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User reviewUser;
	
	@Transient
	private UUID userid;
	
	public Review(double rating, String comment, User reviewUser, Product reviewProduct) {
		super();
		this.rating = rating;
		this.comment = comment;
		this.reviewUser = reviewUser;
	}
}
