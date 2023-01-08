package com.bos.techn.beans;

import java.time.*;
import java.util.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.springframework.data.jpa.domain.support.*;

import com.fasterxml.jackson.annotation.*;

import lombok.*;

@Entity
@Table(name="payment")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter 
@NoArgsConstructor
public class PaymentResult {

	@Id
	@GeneratedValue
	@Type(type="uuid-char")
	private UUID id_payment;
	private String id_paymentpaypal;
	private String paymentstatus;
	private String paymentupdatetime;
	private String paymentemail;
	
	@OneToOne(cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "id_order")
	@JsonBackReference
	private Order paymentOrder;
}
