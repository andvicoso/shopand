package org.andvicoso.shopand.controller.servlet.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.andvicoso.shopand.controller.servlet.base.BaseServlet;
import org.andvicoso.shopand.model.entity.ShoppingCart;

/**
 * Servlet implementation class RemoveFromCart
 */
@WebServlet("/view/cart/remove.do")
public class RemoveFromCart extends BaseServlet {

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
				HttpSession session = request.getSession();
				ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
				if (cart != null) {
					if (cart != null) {
						cart.remove(id);
					}
				}
			}
		}
		response.sendRedirect("list.jsp");
	}
}
