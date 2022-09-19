package com.bos.techn.beans;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.*;

@Entity
@Table(name="item")
@EntityListeners(AuditingEntityListener.class)
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_item;
	private String orderproductname;
	private int qty;
	private String orderproductimage;
	
	@OneToOne
	@JoinColumn(name="id_product")
	private Product itemProduct;

	public int getId_item() {
		return id_item;
	}

	public void setId_item(int id_item) {
		this.id_item = id_item;
	}

	public String getOrderproductname() {
		return orderproductname;
	}

	public void setOrderproductname(String orderproductname) {
		this.orderproductname = orderproductname;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getOrderproductimage() {
		return orderproductimage;
	}

	public void setOrderproductimage(String orderproductimage) {
		this.orderproductimage = orderproductimage;
	}

	public Product getItemProduct() {
		return itemProduct;
	}

	public void setItemProduct(Product itemProduct) {
		this.itemProduct = itemProduct;
	}
	

	
	
}
