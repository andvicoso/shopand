package org.andvicoso.shopand.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.andvicoso.shopand.model.entity.base.AbstractEntity;
import org.andvicoso.shopand.model.entity.user.User;

@Entity
@Table(name = "customer_order")
public class CustomerOrder extends AbstractEntity {
	@NotNull
	private Double amount;
	@NotNull
	private Date creationDate;
	@ManyToOne
	private User user;
	@ManyToMany
	private List<Product> products;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
