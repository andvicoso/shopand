package org.andvicoso.shopand.controller.servlet.user.purchase;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.andvicoso.shopand.controller.servlet.base.BaseServlet;
import org.andvicoso.shopand.infra.exception.PaymentException;
import org.andvicoso.shopand.model.entity.CustomerOrder;
import org.andvicoso.shopand.model.entity.ShoppingCart;
import org.andvicoso.shopand.model.entity.user.User;
import org.andvicoso.shopand.model.manager.MailManager;
import org.andvicoso.shopand.model.manager.OrderManager;
import org.andvicoso.shopand.model.service.PaymentProvider;

@WebServlet("/view/user/purchase/pay.do")
public class Pay extends BaseServlet {

	private OrderManager orderManager;
	private MailManager mailManager;

	@Override
	public void init() throws ServletException {
		orderManager = new OrderManager();
		mailManager = new MailManager();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		boolean error = !isRequestParametersValid(req) || session == null;

		if (!error) {
			Integer paymentServiceId = getInt(req, "paymentServiceId");
			error = paymentServiceId == null;

			if (!error) {
				PaymentProvider provider = PaymentProvider.values()[paymentServiceId];
				User user = (User) session.getAttribute("user");
				ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

				if (user != null) {
					try {
						CustomerOrder order = orderManager.placeOrder(user,
								cart, provider);
						String msg = mailManager.sendPurchaseMail(
								req.getContextPath(), user);

						if (!msg.isEmpty()) {
							addGlobalMsg(req, msg, MessageType.ERROR);
						}

						req.setAttribute("order", order);
						String url = "status/" + order.getStatus().getImage()
								+ ".jsp";
						dispatch(req, resp, url);
					} catch (PaymentException e) {
					}
				}
			}
		}
	}

	@Override
	protected String[] getRequiredParameters() {
		return new String[] { "paymentServiceId" };
	}
}
