package org.andvicoso.shopand.controller.servlet.image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.andvicoso.shopand.controller.servlet.base.BaseServlet;
import org.andvicoso.shopand.infra.utils.ImageUtils;
import org.andvicoso.shopand.infra.utils.StringUtils;
import org.andvicoso.shopand.model.dao.ImageDao;
import org.andvicoso.shopand.model.dao.ImageDaoJPA;
import org.andvicoso.shopand.model.entity.Image;

@WebServlet("/image.do")
public class ImageServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean error = !isRequestParametersValid(req);

		if (!error) {
			Long id = getLong(req, "id");
			error = id == null;

			if (!error) {
				ImageDao dao = new ImageDaoJPA();
				Image image = dao.find(id);

				if (image != null) {

					String widthParam = req.getParameter("width");
					String heightParam = req.getParameter("height");

					byte[] bytes = image.getData();

					if (!StringUtils.isBlank(heightParam)
							&& !StringUtils.isBlank(widthParam)) {
						Integer width = Integer.parseInt(widthParam);
						Integer height = Integer.valueOf(heightParam);

						try {
							bytes = resize(bytes, width, height,
									image.getType());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					ImageUtils.writeImage(image, resp, image.getType());
					resp.setStatus(304);
				} else {
					resp.sendError(404);
				}
			}
		}
	}

	private byte[] resize(byte[] data, Integer width, Integer height,
			String type) throws Exception {
		InputStream input = new ByteArrayInputStream(data);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		Thumbnails.of(input).size(width, height).crop(Positions.CENTER)
				.outputFormat(type).toOutputStream(output);
		return output.toByteArray();
	}
}
