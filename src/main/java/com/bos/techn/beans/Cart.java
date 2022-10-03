package com.bos.techn.beans;

import java.time.*;
import java.util.*;

import javax.persistence.*;
import javax.persistence.Id;

import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.*;

import lombok.*;

@Entity
@Table(name="cart")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter 
@NoArgsConstructor
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_cart;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_cart")
	private List<Item> items;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_shipping")
	private ShippingAddress shipping;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_payment")
	private PaymentResult payment;
	
	private String paymentmethod;
	private double taxprice;
	private double shippingprice;
	private double totalprice;
	private Boolean ispaid;
	private LocalDateTime paidat;
	private Boolean isdelivered;
	private LocalDateTime deliveredat;
	
	@CreatedDate
	private Instant orderCreatedDate;
	@LastModifiedDate
	private Instant orderLastModifiedDate;
	
}

