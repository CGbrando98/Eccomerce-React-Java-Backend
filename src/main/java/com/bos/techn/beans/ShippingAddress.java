package com.bos.techn.beans;

import java.time.*;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.*;

import lombok.*;

@Entity
@Table(name="shipping")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter 
@NoArgsConstructor
public class ShippingAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_shipping;
	private String address;
	private String city;
	private String postal;
	private String country;
}
