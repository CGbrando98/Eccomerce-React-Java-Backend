package com.bos.techn.beans;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.*;

@Entity
@Table(name="payment")
@EntityListeners(AuditingEntityListener.class)
public class PaymentResult {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_payment;
	private String id_paymentpaypal;
	private String paymentstatus;
	private String paymentupdatetime;
	private String paymentemail;
	public int getId_payment() {
		return id_payment;
	}
	public void setId_payment(int id_payment) {
		this.id_payment = id_payment;
	}
	public String getId_paymentpaypal() {
		return id_paymentpaypal;
	}
	public void setId_paymentpaypal(String id_paymentpaypal) {
		this.id_paymentpaypal = id_paymentpaypal;
	}
	public String getPaymentstatus() {
		return paymentstatus;
	}
	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}
	public String getPaymentupdatetime() {
		return paymentupdatetime;
	}
	public void setPaymentupdatetime(String paymentupdatetime) {
		this.paymentupdatetime = paymentupdatetime;
	}
	public String getPaymentemail() {
		return paymentemail;
	}
	public void setPaymentemail(String paymentemail) {
		this.paymentemail = paymentemail;
	}	
	
	
	
//	@OneToOne(mappedBy="paymentresult")
//	private Cart paymentCart;
}
