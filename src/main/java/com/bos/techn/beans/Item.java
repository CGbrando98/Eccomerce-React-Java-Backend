package com.bos.techn.beans;

import java.time.*;

import javax.persistence.*;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_item;
	
	private String name;
	private int qty;
	private String image;
	
	// prod id from react
	@Transient
	private int product;
	
	@OneToOne
	@JoinColumn(name="id_product")
	private Product itemProduct;

	
}
