package org.andvicoso.shopand.model.manager;

import java.util.Date;

import org.andvicoso.shopand.infra.exception.PaymentException;
import org.andvicoso.shopand.model.PaymentStatus;
import org.andvicoso.shopand.model.dao.CustomerOrderDao;
import org.andvicoso.shopand.model.dao.CustomerOrderDaoJPA;
import org.andvicoso.shopand.model.entity.CustomerOrder;
import org.andvicoso.shopand.model.entity.ShoppingCart;
import org.andvicoso.shopand.model.entity.user.User;
import org.andvicoso.shopand.model.service.PaymentProvider;
import org.andvicoso.shopand.model.service.PaymentService;

public class OrderManager {

	private CustomerOrderDao dao;

	public OrderManager() {
		dao = new CustomerOrderDaoJPA();
	}

	public CustomerOrder placeOrder(User user, ShoppingCart cart,
			PaymentProvider provider) throws PaymentException {
		CustomerOrder order = createOrder(user, cart, provider);
		PaymentService ps = getPaymentService(provider);

		try {
			Long confNumber = ps.pay(order);
			PaymentStatus status = confNumber == -1 ? PaymentStatus.WAITING
					: PaymentStatus.PAID;
			order.setConfirmationNumber(confNumber);
			order.setStatus(status);
		} catch (PaymentException e) {
			order.setStatus(PaymentStatus.ERROR);
			throw e;
		} finally {
			dao.save(order);
		}

		return order;
	}

	private PaymentService getPaymentService(PaymentProvider provider) {
		return new PaymentService();
	}

	private CustomerOrder createOrder(User customer, ShoppingCart cart,
			PaymentProvider provider) {
		// set up customer order
		CustomerOrder order = new CustomerOrder();
		order.setUser(customer);
		order.setAmount(cart.getTotal());
		order.setPaymentProvider(provider);
		order.setCreationDate(new Date());

		return order;
	}

	// private void addOrderedItems(CustomerOrder order, ShoppingCart cart) {
	// private void addOrderedItems(CustomerOrder order, ShoppingCart cart) {
	// List<ShoppingCartItem> items = cart.getItems();
	//
	// // iterate through shopping cart and create OrderedProducts
	// for (ShoppingCartItem scItem : items) {
	// int productId = scItem.getProduct().getId();
	//
	// // set up primary key object
	// OrderedProductPK orderedProductPK = new OrderedProductPK();
	// orderedProductPK.setCustomerOrderId(order.getId());
	// orderedProductPK.setProductId(productId);
	//
	// // create ordered item using PK object
	// OrderedProduct orderedItem = new OrderedProduct(orderedProductPK);
	//
	// // set quantity
	// orderedItem.setQuantity(scItem.getQuantity());
	// }
	// }
	// }
}
