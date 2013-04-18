package org.andvicoso.shopand.infra.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.andvicoso.shopand.model.entity.Image;

public final class ImageUtils {

	public static void writeImage(byte[] data, HttpServletResponse resp,
			String type) throws IOException {
		InputStream in = new ByteArrayInputStream(data);
		writeImage(resp, type, in);
	}

	private static void writeImage(HttpServletResponse resp, String type,
			InputStream in) throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		try {
			int length = StreamUtils.write(in, out);
			resp.setContentType("Image/" + type);
			resp.setContentLength(length);
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
		writeImage(resp, type, in);
	}

}
