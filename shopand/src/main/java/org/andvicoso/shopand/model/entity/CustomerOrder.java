package org.andvicoso.shopand.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.andvicoso.shopand.model.PaymentStatus;
import org.andvicoso.shopand.model.entity.base.AbstractEntity;
import org.andvicoso.shopand.model.entity.user.User;
import org.andvicoso.shopand.model.service.PaymentProvider;

@Entity
@Table(name = "customer_order")
public class CustomerOrder extends AbstractEntity {
	@NotNull
	private Double amount;
	@NotNull
	private Date creationDate;
	@NotNull
	private PaymentProvider paymentProvider;
	@NotNull
	private Long confirmationNumber;
	@ManyToOne
	private User user;

	private PaymentStatus status;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(Long confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

}
