package org.andvicoso.shopand.controller.servlet.admin.category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andvicoso.shopand.controller.servlet.base.BaseServlet;
import org.andvicoso.shopand.model.dao.CategoryDao;
import org.andvicoso.shopand.model.dao.CategoryDaoJPA;
import org.andvicoso.shopand.model.entity.Category;

/**
 * Servlet implementation class Add
 */
@WebServlet("/view/admin/category/save.do")
public class SaveCategory extends BaseServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean error = !isRequestParametersValid(request);

		if (!error) {
			String name = request.getParameter("name");
			CategoryDao dao = new CategoryDaoJPA();

			Long id = getLong(request, "id");
			Category category = id == null ? new Category() : dao.find(id);
			category.setName(name);

			dao.save(category);

			response.sendRedirect("list.do");
		}

		if (error) {
			dispatch(request, response, "put.jsp");
		}
	}

	@Override
	protected String[] getRequiredParameters() {
		return new String[] { "name" };
	}
}
