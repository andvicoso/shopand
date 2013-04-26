package org.andvicoso.shopand.model.service.payment.gateway;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.andvicoso.shopand.infra.PropertiesManager;
import org.andvicoso.shopand.model.dao.CustomerOrderDao;
import org.andvicoso.shopand.model.dao.CustomerOrderDaoJPA;
import org.andvicoso.shopand.model.entity.CustomerOrder;
import org.andvicoso.shopand.model.entity.Payment;
import org.andvicoso.shopand.model.entity.PaymentStatus;
import org.andvicoso.shopand.model.entity.Product;
import org.andvicoso.shopand.model.service.payment.PaymentProvider;

import br.com.uol.pagseguro.domain.AccountCredentials;
import br.com.uol.pagseguro.domain.Credentials;
import br.com.uol.pagseguro.domain.Currency;
import br.com.uol.pagseguro.domain.Item;
import br.com.uol.pagseguro.domain.PaymentMethod;
import br.com.uol.pagseguro.domain.PaymentMethodCode;
import br.com.uol.pagseguro.domain.PaymentMethodType;
import br.com.uol.pagseguro.domain.PaymentRequest;
import br.com.uol.pagseguro.domain.ShippingType;
import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.domain.TransactionStatus;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.service.TransactionSearchService;

public class PagSeguroPaymentGateway implements PaymentGateway {

	private static final String CREDENTIALS_TOKEN = "payment.pagseguro.credentials.token";
	private static final String CREDENTIALS_EMAIL = "payment.pagseguro.credentials.email";
	private static final String PAYMENT_ID = "paymentId";
	private static final String TRANSACTION_ID = "transactionId";
	private String token;
	private String email;
	private String urlCallback = "http://localhost:8080/shopand/payment/pagseguro/processReturn.do";

	public PagSeguroPaymentGateway() {
		Properties props = PropertiesManager.read("pagseguro");
		email = props.get(CREDENTIALS_EMAIL).toString();
		token = props.get(CREDENTIALS_TOKEN).toString();
	}

	@Override
	public String processOrder(CustomerOrder order) {
		String paymentURL;

		try {
			String name = order.getUser().getName();
			String email = order.getUser().getEmail();

			PaymentRequest paymentRequest = createPaymentRequest(order, name, email);
			setShippingAddress(paymentRequest);

			for (Product product : order.getProducts()) {
				Item item = createItem(product);
				paymentRequest.addItem(item);
			}

			paymentURL = paymentRequest.register(createCredentials()).toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return paymentURL;
	}

	private void setShippingAddress(PaymentRequest paymentRequest) {
		// POG: para nao aparecer o cep no pagseguro
		paymentRequest.setShippingAddress("Brasil", "SP", "São Paulo", "Centro", "00000000", "Central", "0", "");
	}

	private PaymentRequest createPaymentRequest(CustomerOrder order, String name, String email)
			throws MalformedURLException {
		PaymentRequest paymentRequest = new PaymentRequest();
		paymentRequest.setReference(order.getId().toString());
		paymentRequest.setCurrency(Currency.BRL);
		paymentRequest.setSender(name, email);
		paymentRequest.setRedirectURL(new URL(urlCallback));
		paymentRequest.setShippingType(ShippingType.NOT_SPECIFIED);

		return paymentRequest;
	}

	private Item createItem(Product product) {
		String desc = product.getDescription();
		// max 100
		if (desc.length() > 100)
			desc = desc.substring(0, 100);
		BigDecimal value = new BigDecimal(product.getPrice());
		Item item = new Item();
		item.setId(product.getId().toString());
		item.setDescription(desc);
		item.setQuantity(1);
		item.setAmount(value);
		item.setWeight(0L);
		item.setShippingCost(null);

		return item;
	}

	@Override
	public Payment processReturn(Map<String, String[]> parameters) {
		Payment payment = new Payment();

		try {
			String transactionId = parameters.get(TRANSACTION_ID)[0];
			Transaction transaction = TransactionSearchService.searchByCode(createCredentials(), transactionId);
			payment.setConfirmationNumber(Long.parseLong(transaction.getCode()));
			payment.setDate(transaction.getDate());
			payment.setPaymentProvider(PaymentProvider.PAGSEGURO);
			payment.setStatus(getPaymentStatus(transaction.getStatus()));
			//must remove it from here!
			CustomerOrderDao  dao = new CustomerOrderDaoJPA();
			CustomerOrder order = dao.find(Long.parseLong(transaction.getReference()));
			payment.setOrder(order);
			
		} catch (PagSeguroServiceException e) {
			throw new RuntimeException(e);
		}

		return payment;
	}

	private PaymentStatus getPaymentStatus(TransactionStatus status) {
		PaymentStatus ps = PaymentStatus.WAITING;
		if (status.equals(TransactionStatus.PAID))
			ps = PaymentStatus.PAID;
		else if (status.equals(TransactionStatus.CANCELLED))
			ps = PaymentStatus.ERROR;

		return ps;
	}

	private Transaction createFakeTransaction(Map<String, String> parameters) {
		PaymentMethod pm = new PaymentMethod();
		pm.setCode(PaymentMethodCode.VISA_CREDIT_CARD);
		pm.setType(PaymentMethodType.CREDIT_CARD);

		Transaction transaction = new Transaction();
		transaction.setStatus(TransactionStatus.PAID);
		transaction.setPaymentMethod(pm);
		transaction.setDate(new Date());
		transaction.setReference(parameters.get(PAYMENT_ID));

		return transaction;
	}

	private Credentials createCredentials() {
		return new AccountCredentials(email, token);
	}

}