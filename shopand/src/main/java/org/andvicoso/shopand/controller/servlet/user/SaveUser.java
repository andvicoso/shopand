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
			Long id = getLong(request, "id");
			error = id == null || !conf_password.equals(password);

			if (!error) {
				UserDao dao = new UserDaoJPA();
				User user = dao.find(id);

				updateUser(name, email, password, user);

				dao.save(user);
			}
		}

		if (error) {
			dispatch(request, response, "put.jsp");
		}
	}

	private void updateUser(String name, String email, String password,
			User user) {
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setType(LoginType.USER);
	}

	@Override
	protected String[] getRequiredParameters() {
		return new String[] { "id", "name", "email", "password", "confPassword" };
	}
}