package org.andvicoso.shopand.controller.servlet.guest.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.andvicoso.shopand.controller.servlet.base.BaseServlet;
import org.andvicoso.shopand.model.entity.user.User;
import org.andvicoso.shopand.model.manager.PasswordManager;

@WebServlet("/view/guest/login.do")
public class LoginServlet extends BaseServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean error = !isRequestParametersValid(req);

		if (!error) {
			String email = req.getParameter("email");
			String password = req.getParameter("password");

			PasswordManager pv = new PasswordManager();
			User user = pv.verify(email, password);
			error = user == null;

			if (!error) {
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
				session.setAttribute("login", user.getTypeText());

				redirectToIndex(req, resp);
			}
		}

		if (error) {
			dispatch(req, resp, "login.jsp");
		}
	}

	@Override
	protected String[] getRequiredParameters() {
		return new String[] { "email", "password" };
	}
}
