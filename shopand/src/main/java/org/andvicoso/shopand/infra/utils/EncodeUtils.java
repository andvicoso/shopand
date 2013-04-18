package org.andvicoso.shopand.infra.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;

public final class EncodeUtils {

	private static final String	UTF_8	= "UTF-8";

	public static byte[] decodeAsBase64(final String text) {

		try {
			return Base64.decodeBase64(text.getBytes());
		} catch (final Exception e) {
			throw new RuntimeException();
		}

	}

	public static byte[] decodeAsUTF8(final String text) {

		try {
			return text.getBytes(UTF_8);
		} catch (final Exception e) {
			throw new RuntimeException();
		}

	}

	public static String decodeBase64ToUTF8(String data) {
		byte[] decodeAsBase64 = EncodeUtils.decodeAsBase64(data);
		return EncodeUtils.encodeAsUTF8(decodeAsBase64);
	}

	public static String decodeURL(final String text) {

		try {
			return URLDecoder.decode(text, UTF_8);
		} catch (final Exception e) {
			throw new RuntimeException();
		}
	}

	public static String encodeAsBase64(final byte[] bytes) {
		return new String(Base64.encodeBase64(bytes));
	}

	public static String encodeAsUTF8(final byte[] bytes) {

		try {
			return new String(bytes, UTF_8);
		} catch (final Exception e) {
			throw new RuntimeException();
		}

	}

	public static String encodeURL(final String text) {

		try {
			return URLEncoder.encode(text, UTF_8);
		} catch (final Exception e) {
			throw new RuntimeException();
		}

	}

	public static String encodeUTF8ToBase64(String data) {
		byte[] bytes = EncodeUtils.decodeAsUTF8(data);
		return EncodeUtils.encodeAsBase64(bytes);
	}

	private EncodeUtils() {
		// construtor privado para não ser instanciado
	}

}