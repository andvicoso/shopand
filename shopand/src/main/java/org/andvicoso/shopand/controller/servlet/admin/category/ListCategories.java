package org.andvicoso.shopand.controller.servlet.admin.category;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class List
 */
@WebServlet("/view/admin/category/list.do")
public class ListCategories extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		CategoryDao dao = new CategoryDaoJPA();
		List<Category> categories = dao.list();

		request.setAttribute("categories", categories);

		dispatch(request, response, "list.jsp");
	}

}
