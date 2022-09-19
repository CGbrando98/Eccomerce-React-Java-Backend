package com.bos.techn.beans;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.*;

@Entity
@Table(name="shipping")
@EntityListeners(AuditingEntityListener.class)
public class ShippingAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_shipping;
	private String address;
	private String city;
	private String postal;
	private String country;
	@Override
	public String toString() {
		return "ShippingAddress [id_shipping=" + id_shipping + ", address=" + address + ", city=" + city + ", postal="
				+ postal + ", country=" + country + "]";
	}
	public int getId_shipping() {
		return id_shipping;
	}
	public void setId_shipping(int id_shipping) {
		this.id_shipping = id_shipping;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
//	@OneToOne(mappedBy="shipping")
//	private Cart shippingCart;
	
	
}
