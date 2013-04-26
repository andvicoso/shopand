package org.andvicoso.shopand.controller.servlet.user.purchase;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andvicoso.shopand.controller.servlet.base.BaseServlet;
import org.andvicoso.shopand.model.dao.PaymentDao;
import org.andvicoso.shopand.model.dao.PaymentDaoJPA;
import org.andvicoso.shopand.model.entity.Payment;

/**
 * Servlet implementation class List
 */
@WebServlet("/view/user/purchase/list.do")
public class ListPurchases extends BaseServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession(false);
		//if (session != null) {
			//User user = (User) session.getAttribute("user");

			//if (user != null) {
				PaymentDao dao = new PaymentDaoJPA();
				List<Payment> payments = dao.list();//dao.listByUser(user.getId());

				request.setAttribute("payments", payments);

				dispatch(request, response, "list.jsp");
			//}
		//}
	}
}
