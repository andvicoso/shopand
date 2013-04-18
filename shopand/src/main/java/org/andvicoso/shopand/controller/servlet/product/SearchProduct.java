package org.andvicoso.shopand.controller.servlet.product;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/view/guest/product/search.do")
public class SearchProduct extends BaseServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		boolean error = !isRequestParametersValid(request);

		if (!error) {
			String query = request.getParameter("q");

			ProductDao dao = new ProductDaoJPA();
			List<Product> products = dao.search(query);

			request.setAttribute("products", products);
			request.setAttribute("query", query);
		}
		dispatch(request, response, "searchResult.jsp");
	}

	@Override
	protected String[] getRequiredParameters() {
		return new String[] { "q" };
	}

}
