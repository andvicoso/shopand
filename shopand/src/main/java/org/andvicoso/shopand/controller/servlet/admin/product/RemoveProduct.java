package org.andvicoso.shopand.controller.servlet.admin.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andvicoso.shopand.controller.servlet.base.BaseServlet;
import org.andvicoso.shopand.model.dao.ProductDao;
import org.andvicoso.shopand.model.dao.ProductDaoJPA;

/**
 * Servlet implementation class RemoveFromCart
 */
@WebServlet("/view/admin/product/remove.do")
public class RemoveProduct extends BaseServlet {
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
				ProductDao dao = new ProductDaoJPA();
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
