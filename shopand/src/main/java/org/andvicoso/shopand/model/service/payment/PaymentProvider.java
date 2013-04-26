package org.andvicoso.shopand.model.service.payment;

import org.andvicoso.shopand.model.service.payment.gateway.FakePaymentGateway;
import org.andvicoso.shopand.model.service.payment.gateway.PagSeguroPaymentGateway;
import org.andvicoso.shopand.model.service.payment.gateway.PayPalPaymentGateway;
import org.andvicoso.shopand.model.service.payment.gateway.PaymentGateway;

public enum PaymentProvider {

	PAGSEGURO(PagSeguroPaymentGateway.class), PAYPAL(PayPalPaymentGateway.class), FAKE(FakePaymentGateway.class);

	private Class<? extends PaymentGateway> gateway;

	private PaymentProvider(Class<? extends PaymentGateway> gateway) {
		this.gateway = gateway;
	}

	public PaymentGateway getGateway() {
		try {
			return gateway.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
