package org.andvicoso.shopand.model.dao;

import java.util.List;

import org.andvicoso.shopand.model.dao.base.GenericDao;
import org.andvicoso.shopand.model.dao.base.SearchableDao;
import org.andvicoso.shopand.model.entity.Product;

public interface ProductDao extends GenericDao<Product>, SearchableDao<Product> {

	List<Product> listByCategory(Long id);

}
