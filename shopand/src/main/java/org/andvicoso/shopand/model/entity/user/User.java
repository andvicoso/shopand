package org.andvicoso.shopand.model.entity.user;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.andvicoso.shopand.model.entity.base.AbstractNamedEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User extends AbstractNamedEntity {

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	private String password;

	@Enumerated(EnumType.STRING)
	private LoginType type;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginType getType() {
		return type;
	}

	public void setType(LoginType type) {
		this.type = type;
	}

	public String getTypeText() {
		return type.name().toLowerCase();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	


}
