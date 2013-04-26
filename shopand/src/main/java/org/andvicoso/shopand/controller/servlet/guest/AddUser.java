package org.andvicoso.shopand.controller.servlet.guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andvicoso.shopand.controller.servlet.base.BaseServlet;
import org.andvicoso.shopand.infra.utils.StringUtils;
import org.andvicoso.shopand.model.dao.UserDao;
import org.andvicoso.shopand.model.dao.UserDaoJPA;
import org.andvicoso.shopand.model.entity.user.LoginType;
import org.andvicoso.shopand.model.entity.user.User;
import org.andvicoso.shopand.model.manager.MailManager;

@WebServlet("/view/guest/addUser.do")
public class AddUser extends BaseServlet {

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
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String conf_password = req.getParameter("confPassword");
			String loginTypeStr = req.getParameter("loginType");
			String acceptedTerms = req.getParameter("acceptedTerms");
			error = StringUtils.isBlank(acceptedTerms)
					|| !conf_password.equals(password);

			if (!error) {
				LoginType type = LoginType.valueOf(loginTypeStr);
				User user = new User();
				user.update(name, email, password, type);

				UserDao dao = new UserDaoJPA();
				User sameEmailUser = dao.findByEmail(email);

				if (sameEmailUser == null) {
					dao.save(user);

					String msg = mailManager.sendSignUpMail(user);

					if (!msg.isEmpty()) {
						addGlobalMsg(req, msg, MessageType.ERROR);
					}
				}

				redirectToIndex(req, resp);
			}
		}

		if (error) {
			dispatch(req, resp, "put.jsp");
		}
	}

	@Override
	protected String[] getRequiredParameters() {
		return new String[] { "name", "email", "password", "confPassword",
				"acceptedTerms", "loginType" };
	}
}
