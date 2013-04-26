package org.andvicoso.shopand.model.service.payment.gateway;

import java.util.Map;
import java.util.Random;

import org.andvicoso.shopand.model.entity.CustomerOrder;
import org.andvicoso.shopand.model.entity.Payment;

public class FakePaymentGateway implements PaymentGateway {

	private static final double PAYMENT_ERROR_RATE = 0.25;

	@Override
	public String processOrder(CustomerOrder order) {
		return "/payment/fake/random.do";
	}

	@Override
	public Payment processReturn(Map<String, String[]> parameters) {
		Payment payment = new Payment();
		// fake payment
		int confNumber = -1;
		Random random = new Random();
		// if random number < 0.25, the payment was not realized
		double paid = random.nextDouble();
		// create confirmation number
		if (paid > PAYMENT_ERROR_RATE) {

			confNumber = random.nextInt(999999999);
		}

		return payment;
	}
}
