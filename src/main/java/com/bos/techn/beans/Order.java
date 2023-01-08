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
// cannot use the name order
@Table(name="customer_order")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter 
@NoArgsConstructor
@ToString
public class Order {
	
	@Id
	@GeneratedValue
	@Type(type="uuid-char")
	private UUID id_order;
	
	@OneToMany(cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="id_order")
	private List<Item> items;
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy = "shippingOrder" )
	// sets the fk with the back reference
	@JsonManagedReference
	private ShippingAddress shipping;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "paymentOrder" )
	@JsonManagedReference
	private PaymentResult payment;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	// here name is the one mapping
	@JoinColumn(name="id_user")
	// deleting a user deletes there products
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User user;
	
	@Transient
	private UUID userid;
	
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

