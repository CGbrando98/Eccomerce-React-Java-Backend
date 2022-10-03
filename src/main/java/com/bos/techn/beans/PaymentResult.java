package com.bos.techn.beans;

import java.time.*;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.*;

import lombok.*;

@Entity
@Table(name="payment")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter 
@NoArgsConstructor
public class PaymentResult {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_payment;
	private String id_paymentpaypal;
	private String paymentstatus;
	private String paymentupdatetime;
	private String paymentemail;
}
