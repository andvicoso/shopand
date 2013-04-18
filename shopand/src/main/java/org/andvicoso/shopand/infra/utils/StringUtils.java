package org.andvicoso.shopand.infra.utils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public final class StringUtils {

	private static final Locale	BRAZIL	= new Locale("pt", "BR");

	public static String bfmt(double number) {
		return byteScaleFormatter(number);
	}

	public static String fmt(double number) {
		return numberScaleFormatter(number);
	}

	public static String fmt(double number, int precision) {
		return numberScaleFormatter(number, precision);
	}

	public static String numberScaleFormatter(double number) {
		int precision = (number > 1 || number < -1 ? 1 : 0);
		return numberScaleFormatter(number, precision);
	}

	public static String byteScaleFormatter(double bytes) {
		int precision = (bytes < 1024) ? 0 : 1;
		return byteScaleFormatter(bytes, precision);
	}

	public static String numberScaleFormatter(double number, int precision) {
		if (number == 0) {
			return "0 ";
		}
		double signum = Math.signum(number);
		number *= signum;
		int exp = (int) Math.floor(Math.log10(number) / 3);
		exp = (exp < -6) ? -6 : exp;
		exp = (exp > 7) ? 7 : exp;
		String pre = "afpnum kMGTPEZ".charAt(exp + 6) + "";
		if (pre.equals(" ")) {
			pre = "";
		}
		return String.format("%." + precision + "f %s",
				signum * number / Math.pow(1E3, exp), pre);
	}

	public static String byteScaleFormatter(double bytes, int precision) {
		int exp = Math.getExponent(bytes) / 10;
		exp = (exp < 0) ? 0 : exp;
		exp = (exp > 7) ? 7 : exp;
		String pre = (" KMGTPEZ").charAt(exp) + "i";
		if (pre.equals(" i")) {
			pre = "";
		}
		return String.format("%." + precision + "f %sB",
				bytes / Math.pow(1024, exp), pre);
	}

	public static String glue(final String pConnector,
			final boolean pAcceptEmpty, final Object... pObjs) {
		String result = "";
		for (int i = 0; i < pObjs.length - 1; i++)
			if (pObjs[i] != null) {
				String string = "";
				if (pObjs instanceof Object[] && pObjs.length > 0)
					string = glue(pConnector, pObjs[i]);
				else if (!(pObjs instanceof Object[]))
					string = pObjs[i].toString();
				if (!string.isEmpty() || (pAcceptEmpty && string.isEmpty()))
					result += string + pConnector;
			}

		final Object last = pObjs[pObjs.length - 1];
		return last != null ? result + last.toString() : result;
	}

	public static String glue(final String pConnector, final Object... pObjs) {
		return glue(pConnector, false, pObjs);
	}

	public static String glueGetParameters(final Map<String, Object> pParameters) {
		Collection<Object> params = new ArrayList<Object>();

		for (Entry<String, Object> entry : pParameters.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			Object stringValue = value != null ? value.toString() : "";
			params.add(key + "=" + stringValue);
		}

		return glue("&", false, params.toArray());
	}

	public static String capitalize(final String text) {
		final StringBuilder result = new StringBuilder();

		for (final String string : text.split(" ")) {
			result.append(string.substring(0, 1).toUpperCase());
			result.append(string.substring(1).toLowerCase());
			result.append(" ");
		}

		return result.toString().trim();
	}

	public static boolean isBlank(final String string) {
		return string == null || isEmpty(string.trim());
	}

	public static boolean isEmpty(final String string) {
		return string == null || string.isEmpty();
	}

	public static boolean isNotBlank(final String string) {
		return !isBlank(string);
	}

	public static boolean isNotEmpty(final String string) {
		return !isEmpty(string);
	}

	private StringUtils() {
		// construtor privado para não ser instanciado
	}

	public static String fmtReal(double pValue) {
		NumberFormat formatter = NumberFormat.getInstance(BRAZIL);
		return formatter.format(pValue);
	}
}
