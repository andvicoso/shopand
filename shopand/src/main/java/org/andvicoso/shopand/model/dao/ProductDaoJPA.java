package org.andvicoso.shopand.model.dao;

import java.util.List;

import org.andvicoso.shopand.model.dao.base.GenericDaoJPA;
import org.andvicoso.shopand.model.entity.Product;

public class ProductDaoJPA extends GenericDaoJPA<Product> implements ProductDao {

	public List<Product> search(String term) {
		String q = "SELECT u FROM Product u WHERE u.name LIKE :term ORDER BY u.name";
		String sterm = "%" + term + "%";
		return createQuery(q).setParameter("term", sterm).getResultList();
	}

	public List<Product> listByCategory(Long id) {
		String q = "SELECT u FROM Product u WHERE u.category.id = :id";
		return createQuery(q).setParameter("id", id).getResultList();
	}

}
