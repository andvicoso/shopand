package org.andvicoso.shopand.model.entity.base;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable, IdentifiedEntity, SearchableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long	id;

	@Override
	public final boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final AbstractEntity other = (AbstractEntity) obj;

		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}

		return true;

	}

	public Long getId() {
		return id;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
