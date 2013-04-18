package org.andvicoso.shopand.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.andvicoso.shopand.model.entity.base.AbstractNamedEntity;

@Entity
@Table(name = "category")
public class Category extends AbstractNamedEntity {

}
