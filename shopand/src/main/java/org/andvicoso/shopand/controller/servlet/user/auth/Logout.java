package org.andvicoso.shopand.controller.servlet.user.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.andvicoso.shopand.controller.servlet.base.BaseServlet;

@WebServlet("/view/user/logout.do")
public class Logout extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		redirectToIndex(req, resp);
	}
}
