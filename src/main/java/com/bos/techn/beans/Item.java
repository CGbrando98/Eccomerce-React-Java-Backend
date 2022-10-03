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
	private String orderproductname;
	private int qty;
	private String orderproductimage;
	
	@OneToOne
	@JoinColumn(name="id_product")
	private Product itemProduct;

	
}
