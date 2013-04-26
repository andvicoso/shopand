package org.andvicoso.shopand.infra.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.andvicoso.shopand.model.entity.Image;

public final class ImageUtils {

	private static final String IMAGE = "image/";

	public static void writeImage(byte[] data, HttpServletResponse resp,
			String type) throws IOException {
		InputStream in = new ByteArrayInputStream(data);
		resp.setContentLength(data.length);
		resp.setContentType(IMAGE + type);
		writeImage(resp, type, in);
	}

	private static void writeImage(HttpServletResponse resp, String type,
			InputStream in) throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		try {
			StreamUtils.write(in, out);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		} finally {
			StreamUtils.closeQuietly(in);
			StreamUtils.closeQuietly(out);
		}
	}

	public static void writeImage(Image image, HttpServletResponse resp,
			String type) throws IOException {
		InputStream in = image.getAsStream();
		resp.setContentLength(image.getData().length);
		resp.setContentType(IMAGE + type);
		writeImage(resp, type, in);
	}

}
