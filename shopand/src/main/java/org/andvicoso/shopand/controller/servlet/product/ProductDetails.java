package org.andvicoso.shopand.controller.servlet.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andvicoso.shopand.controller.servlet.base.BaseServlet;
import org.andvicoso.shopand.model.dao.ProductDao;
import org.andvicoso.shopand.model.dao.ProductDaoJPA;
import org.andvicoso.shopand.model.entity.Product;

/**
 * Servlet implementation class List
 */
@WebServlet("/view/product/details.do")
public class ProductDetails extends BaseServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Long id = getLong(request, "id");

		ProductDao dao = new ProductDaoJPA();
		Product product = dao.find(id);

		request.setAttribute("product", product);

		dispatch(request, response, "details.jsp");
	}

	@Override
	protected String[] getRequiredParameters() {
		return new String[] { "id" };
	}

}
