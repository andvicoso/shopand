package org.andvicoso.shopand.model.entity;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	private List<Product> products;

	public ShoppingCart() {
		products = new ArrayList<Product>();
	}

	public void add(Product product) {
		products.add(product);
	}

	public void remove(Product product) {
		products.remove(product);
	}

	public List<Product> getProducts() {
		return products;
	}

	public void clear() {
		products.clear();
	}

	public void remove(Long id) {
		Product toRemove = getProduct(id);

		if (toRemove != null) {
			products.remove(toRemove);
		}
	}

	public Double getTotal() {
		double sum = 0;
		for (Product product : getProducts()) {
			sum += product.getPrice();
		}
		return sum;
	}

	public Product getProduct(Long id) {
		Product product = null;
		for (Product p : getProducts()) {
			if (p.getId().intValue() == id) {
				product = p;
				break;
			}
		}
		return product;
	}
}
