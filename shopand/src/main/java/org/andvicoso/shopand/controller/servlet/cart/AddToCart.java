package org.andvicoso.shopand.controller.servlet.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.andvicoso.shopand.controller.servlet.base.BaseServlet;
import org.andvicoso.shopand.model.dao.ProductDao;
import org.andvicoso.shopand.model.dao.ProductDaoJPA;
import org.andvicoso.shopand.model.entity.Product;
import org.andvicoso.shopand.model.entity.ShoppingCart;

/**
 * Servlet implementation class Add
 */
@WebServlet("/view/cart/add.do")
public class AddToCart extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean error = !isRequestParametersValid(request);

		if (!error) {
			Integer id = getInt(request, "id");
			error = id == null;

			if (!error) {
				ProductDao dao = new ProductDaoJPA();
				Product product = dao.find(id);

				HttpSession session = request.getSession();
				ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
				if (cart == null) {
					cart = new ShoppingCart();
					session.setAttribute("cart", cart);
				}

				cart.add(product);
			}
		}

		response.sendRedirect("list.jsp");
	}
}
