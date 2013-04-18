package org.andvicoso.shopand.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.andvicoso.shopand.model.entity.base.AbstractNamedEntity;

@Entity
@Table(name = "product")
public class Product extends AbstractNamedEntity {

	@OneToOne
	private Image photo;
	@NotNull
	private Double price;

	private String description;

	@ManyToOne
	@NotNull
	private Category category;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date lastUpdate;

	public Image getPhoto() {
		return photo;
	}

	public void setPhoto(Image photo) {
		this.photo = photo;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
