package org.andvicoso.shopand.controller.servlet.payment.pagseguro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.andvicoso.shopand.controller.servlet.base.BaseServlet;
import org.andvicoso.shopand.infra.exception.PaymentException;
import org.andvicoso.shopand.model.entity.ShoppingCart;
import org.andvicoso.shopand.model.entity.user.User;
import org.andvicoso.shopand.model.manager.PaymentManager;
import org.andvicoso.shopand.model.service.payment.PaymentProvider;
import org.andvicoso.shopand.model.service.payment.gateway.PaymentGateway;

@WebServlet("/payment/pagseguro/placeOrder.do")
public class PlaceOrder extends BaseServlet {

	private PaymentManager paymentManager;

	@Override
	public void init() throws ServletException {
		paymentManager = new PaymentManager();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		boolean error = session == null;

		if (!error) {
			PaymentGateway gateway = PaymentProvider.PAGSEGURO.getGateway();
			User user = (User) session.getAttribute("user");
			ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

			if (user != null) {
				try {
					String payUrl = paymentManager.placeOrder(user, cart, gateway);
					resp.sendRedirect(payUrl);
				} catch (PaymentException e) {
				}
			}
		}
	}

}
