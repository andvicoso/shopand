package org.andvicoso.shopand.infra.utils;

import java.io.*;

import org.apache.commons.io.output.ByteArrayOutputStream;

public class StreamUtils {

	private static final int BUFFER_SIZE = 1024;

	public static void closeQuietly(final Closeable closeable) {

		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (final IOException ioe) {
			// ignore
		}

	}

	public static long copy(InputStream input, OutputStream output) {

		try {

			byte[] buffer = new byte[BUFFER_SIZE];
			long count = 0;
			int n = 0;
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
				count += n;
			}
			return count;

		} catch (final Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static byte[] toByteArray(InputStream input) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		copy(input, output);
		return output.toByteArray();
	}

	public static int write(final InputStream in, final OutputStream out) {

		try {

			int length = 0;
			final byte[] buf = new byte[BUFFER_SIZE];
			int count = 0;

			while ((count = in.read(buf)) >= 0) {
				out.write(buf, 0, count);
				length += count;
			}
			out.flush();

			return length;

		} catch (final Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static void write(final String string, final OutputStream out) {
		final PrintWriter writer = new PrintWriter(out);
		writer.print(string);
		writer.close();
	}

	private StreamUtils() {
		// construtor privado para não ser instanciado
	}

}
