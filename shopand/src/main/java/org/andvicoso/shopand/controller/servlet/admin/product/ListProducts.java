package org.andvicoso.shopand.controller.servlet.admin.product;

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
@WebServlet("/view/admin/product/list.do")
public class ListProducts extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ProductDao dao = new ProductDaoJPA();
		List<Product> products = dao.list();

		request.setAttribute("products", products);

		dispatch(request, response, "list.jsp");
	}

}
