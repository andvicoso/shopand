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

/**
 * Servlet implementation class RemoveFromCart
 */
@WebServlet("/view/admin/category/remove.do")
public class RemoveCategory extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		boolean error = !isRequestParametersValid(request);

		if (!error) {
			Long id = getLong(request, "id");
			error = id == null;
			if (!error) {
				CategoryDao dao = new CategoryDaoJPA();
				dao.remove(id);

				response.sendRedirect("list.do");
			}
		}
		if (error) {
			dispatch(request, response, "edit.do");
		}
	}

	@Override
	protected String[] getRequiredParameters() {
		return new String[] { "id" };
	}

}
