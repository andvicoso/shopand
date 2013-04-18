package org.andvicoso.shopand.controller.servlet.admin.product;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andvicoso.shopand.controller.servlet.base.MultipartContentBaseServlet;
import org.andvicoso.shopand.model.dao.CategoryDao;
import org.andvicoso.shopand.model.dao.CategoryDaoJPA;
import org.andvicoso.shopand.model.dao.ImageDao;
import org.andvicoso.shopand.model.dao.ImageDaoJPA;
import org.andvicoso.shopand.model.dao.ProductDao;
import org.andvicoso.shopand.model.dao.ProductDaoJPA;
import org.andvicoso.shopand.model.entity.Category;
import org.andvicoso.shopand.model.entity.Image;
import org.andvicoso.shopand.model.entity.Product;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Add
 */
@WebServlet("/view/admin/product/save.do")
public class SaveProduct extends MultipartContentBaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean error = !ServletFileUpload.isMultipartContent(request);

		if (!error) {
			List<FileItem> items = getItems(request);

			error = !isRequestParametersValid(items);
			if (!error) {
				String name = getParam(items, "name").getString();
				String description = getParam(items, "description").getString();
				String pricez = getParam(items, "price").getString();
				String categoIdz = getParam(items, "categoryId").getString();

				Double price = null;
				Long categoryId = null;
				try {
					price = Double.parseDouble(pricez);
					categoryId = Long.parseLong(categoIdz);
				} catch (Exception e) {
				}

				error = price == null || categoryId == null;

				if (!error) {
					CategoryDao catDao = new CategoryDaoJPA();
					Category category = catDao.find(categoryId);

					ProductDao dao = new ProductDaoJPA();

					FileItem fiid = getParam(items, "id");
					Long id = null;
					if (fiid != null) {
						try {
							id = Long.parseLong(fiid.getString());
						} catch (Exception e) {
						}
					}

					Product product = id == null ? new Product() : dao.find(id);

					Image image = getImage(items, id == null ? null : product
							.getPhoto().getId());

					updateProduct(name, description, price, category, image,
							product);

					dao.save(product);

					response.sendRedirect("list.do");
				}
			}
		}

		if (error) {
			dispatch(request, response, "put.jsp");
		}
	}

	private void updateProduct(String name, String description, Double price,
			Category category, Image image, Product product) {
		product.setDescription(description);
		product.setName(name);
		product.setPrice(price);
		product.setLastUpdate(new Date());
		product.setCategory(category);
		product.setPhoto(image);
	}

	private Image getImage(List<FileItem> items, Long photoId) {
		FileItem fim = getParam(items, "photo");
		ImageDao dao = new ImageDaoJPA();
		Image image = photoId == null ? new Image() : dao.find(photoId);

		if (fim != null) {
			image.setData(fim.get());
			image.setMimeType(fim.getContentType());
			dao.save(image);
		}

		return image;
	}

	@Override
	protected String[] getRequiredParameters() {
		return new String[] { "name", "description", "price", "categoryId",
				"photo" };
	}
}
