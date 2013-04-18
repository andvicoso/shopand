package org.andvicoso.shopand.model.service;

import java.util.Random;

import org.andvicoso.shopand.infra.exception.PaymentException;
import org.andvicoso.shopand.model.entity.CustomerOrder;

public class PaymentService {

	private static final double PAYMENT_ERROR_RATE = 0.25;
	private static final double PAYMENT_WAITING_RATE = 0.5;

	public long pay(CustomerOrder order) throws PaymentException {
		// fake payment
		Random random = new Random();
		// if random number < 0.25, the payment was not realized
		double paid = random.nextDouble();

		if (paid > PAYMENT_WAITING_RATE) {
			// create confirmation number
			return random.nextInt(999999999);
		} else if (paid < PAYMENT_ERROR_RATE) {
			// create confirmation number
			return -1;
		} else {
			throw new PaymentException();
		}
	}

}
