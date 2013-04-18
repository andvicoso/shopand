package org.andvicoso.shopand.controller.servlet.guest.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andvicoso.shopand.controller.servlet.base.BaseServlet;
import org.andvicoso.shopand.infra.utils.CriptoUtils;
import org.andvicoso.shopand.model.dao.UserDao;
import org.andvicoso.shopand.model.dao.UserDaoJPA;
import org.andvicoso.shopand.model.entity.user.User;
import org.andvicoso.shopand.model.manager.MailManager;
import org.apache.commons.lang.RandomStringUtils;

@WebServlet("/view/guest/forgotPassword.do")
public class ForgotPassword extends BaseServlet {
	private MailManager mailManager;

	@Override
	public void init() throws ServletException {
		mailManager = new MailManager();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean error = !isRequestParametersValid(req);
		if (!error) {
			String email = req.getParameter("email");
			UserDao dao = new UserDaoJPA();

			User user = dao.findByEmail(email);
			error = user == null;

			if (!error) {
				// Generate new password
				String newPassword = generatePassword();
				String newCriptPass = CriptoUtils.encryptMD5(newPassword);
				user.setPassword(newCriptPass);
				// save user
				dao.save(user);
				// send email with new password
				String msg = mailManager.sendNewPasswordMail(user, newPassword);

				if (!msg.isEmpty()) {
					addGlobalMsg(req, msg, MessageType.ERROR);
				}
			}
		}
		redirectToIndex(req, resp);
	}

	private String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(8);
	}

	@Override
	protected String[] getRequiredParameters() {
		return new String[] { "email" };
	}
}
