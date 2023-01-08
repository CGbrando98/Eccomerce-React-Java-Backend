package com.bos.techn.beans;

import java.time.*;
import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.springframework.data.jpa.domain.support.*;

import lombok.*;

@Entity
@Table(name="item")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter 
@NoArgsConstructor
public class Item {
	
	@Id
	@GeneratedValue
	@Type(type="uuid-char")
	private UUID id_item;
	
	private String name;
	private int qty;
	private String image;
	
	// prod id from react
	@Transient
	private UUID product;
	
	@OneToOne
	@JoinColumn(name="id_product")
	private Product itemProduct;

	
}
