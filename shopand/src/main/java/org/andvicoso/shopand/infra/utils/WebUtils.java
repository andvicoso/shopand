package org.andvicoso.shopand.infra.utils;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class WebUtils {

	public static String getBaseURL(final HttpServletRequest req) {

		final String scheme = req.getScheme(); // http
		final int serverPort = req.getServerPort(); // 80
		final String serverName = req.getServerName(); // hostname.com
		final String contextPath = req.getContextPath(); // /mywebapp

		return scheme + "://" + serverName + ":" + serverPort + contextPath;

	}

	public static String getRequestURL(final HttpServletRequest req) {
		return getBaseURL(req) + req.getRequestURI();
	}

	public static void writeInResponse(final HttpServletResponse res,
			final InputStream in, final String mimeType) {

		OutputStream out = null;
		int length = 0;

		try {

			out = res.getOutputStream();

			length = StreamUtils.write(in, out);

			res.setContentType(mimeType);
			res.setContentLength(length);

		} catch (final Exception e) {
			throw new RuntimeException(e);
		} finally {
			StreamUtils.closeQuietly(in);
			StreamUtils.closeQuietly(out);
		}

	}

	public static void writeInResponse(final HttpServletResponse res,
			final String string, final String mimeType) {

		OutputStream out = null;
		final int length = string.length();

		try {

			out = res.getOutputStream();

			StreamUtils.write(string, out);

			res.setContentType(mimeType);
			res.setContentLength(length);

		} catch (final Exception e) {
			throw new RuntimeException(e);
		} finally {
			StreamUtils.closeQuietly(out);
		}

	}

	private WebUtils() {
		// construtor privado para não ser instanciado
	}
}
