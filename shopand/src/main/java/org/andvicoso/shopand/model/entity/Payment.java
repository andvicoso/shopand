package org.andvicoso.shopand.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.andvicoso.shopand.model.entity.base.AbstractEntity;
import org.andvicoso.shopand.model.service.payment.PaymentProvider;

@Entity
@Table(name = "payment")
public class Payment extends AbstractEntity {
	@NotNull
	private Date date;
	@NotNull
	private PaymentProvider paymentProvider;
	@NotNull
	private Long confirmationNumber;
	@OneToOne
	private CustomerOrder order;

	private PaymentStatus status;

	public Long getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(Long confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public PaymentProvider getPaymentProvider() {
		return paymentProvider;
	}

	public void setPaymentProvider(PaymentProvider paymentProvider) {
		this.paymentProvider = paymentProvider;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public CustomerOrder getOrder() {
		return order;
	}

	public void setOrder(CustomerOrder order) {
		this.order = order;
	}

}
