package org.andvicoso.shopand.model.service.payment.gateway;

import java.util.Map;

import org.andvicoso.shopand.model.entity.CustomerOrder;
import org.andvicoso.shopand.model.entity.Payment;

public interface PaymentGateway {

	String processOrder(CustomerOrder order);

	Payment processReturn(Map<String, String[]> parameters);

}
