package com.bos.techn.beans;

import java.time.*;
import java.util.*;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.*;

import lombok.*;

@Entity
// cannot use the name order
@Table(name="customer_order")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter 
@NoArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_order;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_order")
	private List<Item> items;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_shipping")
	private ShippingAddress shipping;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_user")
	private User user;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_payment")
	private PaymentResult payment;
	
	@Transient
	private int userid;
	
	private String paymentmethod;
	private double itemsprice;
	private double taxprice;
	private double shippingprice;
	private double totalprice;
	private Boolean ispaid;
	private LocalDateTime paidat;
	private Boolean isdelivered;
	private LocalDateTime deliveredat;
	
	@CreatedDate
	private Date orderCreatedDate;
	@LastModifiedDate
	private Date orderLastModifiedDate;
	
}

