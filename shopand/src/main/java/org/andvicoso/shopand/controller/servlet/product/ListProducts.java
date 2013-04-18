package org.andvicoso.shopand.controller.servlet.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andvicoso.shopand.controller.servlet.base.BaseServlet;
import org.andvicoso.shopand.model.dao.CategoryDaoJPA;
import org.andvicoso.shopand.model.dao.ProductDao;
import org.andvicoso.shopand.model.dao.ProductDaoJPA;
import org.andvicoso.shopand.model.entity.Category;
import org.andvicoso.shopand.model.entity.Product;

/**
 * Servlet implementation class List
 */
@WebServlet("/view/product/list.do")
public class ListProducts extends BaseServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Long id = getLong(request, "id");

		ProductDao dao = new ProductDaoJPA();
		List<Product> products = id == null ? dao.list() : dao
				.listByCategory(id);

		request.setAttribute("products", products);
		
		if (id != null) {
			Category category = new CategoryDaoJPA().find(id);
			request.setAttribute("category", category);
		}

		dispatch(request, response, "list.jsp");
	}

}
