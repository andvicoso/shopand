package org.andvicoso.shopand.controller.servlet.payment.pagseguro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.andvicoso.shopand.controller.servlet.base.BaseServlet;
import org.andvicoso.shopand.model.entity.Payment;
import org.andvicoso.shopand.model.entity.user.User;
import org.andvicoso.shopand.model.manager.MailManager;
import org.andvicoso.shopand.model.service.payment.PaymentProvider;
import org.andvicoso.shopand.model.service.payment.gateway.PaymentGateway;

@WebServlet("/payment/pagseguro/processReturn.do")
public class ProcessReturn extends BaseServlet {

	private MailManager mailManager;

	@Override
	public void init() throws ServletException {
		mailManager = new MailManager();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);

		if (session != null) {
			PaymentGateway paymentGateway = PaymentProvider.PAGSEGURO.getGateway();
			Payment payment = paymentGateway.processReturn(req.getParameterMap());
			if (payment != null) {
				session.removeAttribute("cart");
				User user = (User) session.getAttribute("user");
				String msg = mailManager.sendPurchaseMail(req.getContextPath(), user);

				if (!msg.isEmpty()) {
					addGlobalMsg(req, msg, MessageType.ERROR);
				}

				req.setAttribute("payment", payment);
				String url = req.getContextPath() + "/view/user/purchase/status/" + payment.getStatus().getImage()
						+ ".jsp";
				dispatch(req, resp, url);
			}
		}
	}

}
