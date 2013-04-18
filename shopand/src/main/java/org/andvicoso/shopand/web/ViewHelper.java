package org.andvicoso.shopand.web;

import java.util.List;

import org.andvicoso.shopand.model.dao.CategoryDao;
import org.andvicoso.shopand.model.dao.CategoryDaoJPA;
import org.andvicoso.shopand.model.dao.ProductDao;
import org.andvicoso.shopand.model.dao.ProductDaoJPA;
import org.andvicoso.shopand.model.dao.base.GenericDao;
import org.andvicoso.shopand.model.entity.Category;
import org.andvicoso.shopand.model.entity.Product;

public class ViewHelper {

	private CategoryDao categoryDao;
	private ProductDao productDao;

	private GenericDao<Category> getCategoryDao() {
		if (categoryDao == null) {
			categoryDao = new CategoryDaoJPA();
		}
		return categoryDao;
	}

	public List<Category> getCategories() {
		return getCategoryDao().list();
	}

	private GenericDao<Product> getProductDao() {
		if (productDao == null) {
			productDao = new ProductDaoJPA();
		}
		return productDao;
	}

	public List<Product> getHighlights() {
		return getProductDao().list();
	}
}
