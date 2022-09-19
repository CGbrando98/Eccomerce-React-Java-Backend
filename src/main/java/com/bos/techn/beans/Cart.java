package com.bos.techn.beans;

import java.time.*;
import java.util.*;

import javax.persistence.*;
import javax.persistence.Id;

import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.*;

@Entity
@Table(name="cart")
@EntityListeners(AuditingEntityListener.class)
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
	public int getId_cart() {
		return id_cart;
	}
	public void setId_cart(int id_cart) {
		this.id_cart = id_cart;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public ShippingAddress getShipping() {
		return shipping;
	}
	public void setShipping(ShippingAddress shipping) {
		this.shipping = shipping;
	}
	public PaymentResult getPayment() {
		return payment;
	}
	public void setPayment(PaymentResult payment) {
		this.payment = payment;
	}
	public String getPaymentmethod() {
		return paymentmethod;
	}
	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}
	public double getTaxprice() {
		return taxprice;
	}
	public void setTaxprice(double taxprice) {
		this.taxprice = taxprice;
	}
	public double getShippingprice() {
		return shippingprice;
	}
	public void setShippingprice(double shippingprice) {
		this.shippingprice = shippingprice;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public Boolean getIspaid() {
		return ispaid;
	}
	public void setIspaid(Boolean ispaid) {
		this.ispaid = ispaid;
	}
	public LocalDateTime getPaidat() {
		return paidat;
	}
	public void setPaidat(LocalDateTime paidat) {
		this.paidat = paidat;
	}
	public Boolean getIsdelivered() {
		return isdelivered;
	}
	public void setIsdelivered(Boolean isdelivered) {
		this.isdelivered = isdelivered;
	}
	public LocalDateTime getDeliveredat() {
		return deliveredat;
	}
	public void setDeliveredat(LocalDateTime deliveredat) {
		this.deliveredat = deliveredat;
	}
	public Instant getOrderCreatedDate() {
		return orderCreatedDate;
	}
	public void setOrderCreatedDate(Instant orderCreatedDate) {
		this.orderCreatedDate = orderCreatedDate;
	}
	public Instant getOrderLastModifiedDate() {
		return orderLastModifiedDate;
	}
	public void setOrderLastModifiedDate(Instant orderLastModifiedDate) {
		this.orderLastModifiedDate = orderLastModifiedDate;
	}
	
//	@OneToOne
//	@JoinColumn(name="id_user")
//	private User orderUser;
	

	
}

