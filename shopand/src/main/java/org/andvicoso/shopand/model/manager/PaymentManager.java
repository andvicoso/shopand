package org.andvicoso.shopand.model.manager;

import java.util.Date;
import java.util.Map;

import org.andvicoso.shopand.infra.exception.PaymentException;
import org.andvicoso.shopand.model.dao.CustomerOrderDao;
import org.andvicoso.shopand.model.dao.CustomerOrderDaoJPA;
import org.andvicoso.shopand.model.entity.CustomerOrder;
import org.andvicoso.shopand.model.entity.ShoppingCart;
import org.andvicoso.shopand.model.entity.user.User;
import org.andvicoso.shopand.model.service.payment.gateway.PaymentGateway;

public class PaymentManager {

	private CustomerOrderDao dao;

	public PaymentManager() {
		dao = new CustomerOrderDaoJPA();
	}

	public String placeOrder(User user, ShoppingCart cart, PaymentGateway ps) throws PaymentException {
		CustomerOrder order = createOrder(user, cart);
		dao.save(order);

		return ps.processOrder(order);
	}

	private CustomerOrder createOrder(User customer, ShoppingCart cart) {
		// set up customer order
		CustomerOrder order = new CustomerOrder();
		order.setUser(customer);
		order.setAmount(cart.getTotal());
		order.setCreationDate(new Date());

		return order;
	}

}
