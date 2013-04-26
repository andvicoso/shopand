package org.andvicoso.shopand.controller.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andvicoso.shopand.controller.servlet.base.BaseServlet;
import org.andvicoso.shopand.model.dao.UserDao;
import org.andvicoso.shopand.model.dao.UserDaoJPA;
import org.andvicoso.shopand.model.entity.user.LoginType;
import org.andvicoso.shopand.model.entity.user.User;

@WebServlet("/view/user/save.do")
public class SaveUser extends BaseServlet {

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean error = !isRequestParametersValid(request);

		if (!error) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String conf_password = request.getParameter("confPassword");
			String loginTypeStr = request.getParameter("loginType");
			Long id = getLong(request, "id");
			error = id == null || !conf_password.equals(password);

			if (!error) {
				LoginType type = LoginType.valueOf(loginTypeStr);
				UserDao dao = new UserDaoJPA();
				User user = dao.find(id);

				user.update(name, email, password, type);

				dao.save(user);
			}
		}

		if (error) {
			dispatch(request, response, "put.jsp");
		}
	}

	@Override
	protected String[] getRequiredParameters() {
		return new String[] { "id", "name", "email", "password", "confPassword", "loginType" };
	}
}