package org.andvicoso.shopand.model.service.payment.gateway;

import java.util.Map;

import org.andvicoso.shopand.model.entity.CustomerOrder;
import org.andvicoso.shopand.model.entity.Payment;

public class PayPalPaymentGateway implements PaymentGateway {

	@Override
	public String processOrder(CustomerOrder order) {
		return "";
	}

	@Override
	public Payment processReturn(Map<String, String[]> parameters) {
		return null;
	}

}
