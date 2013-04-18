package org.andvicoso.shopand.infra.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public final class NumberUtils {

	public static String formatNumber(Double valor) {
		DecimalFormat formato = new DecimalFormat();
		// 2 casas decimais
		formato.setMinimumFractionDigits(2);
		String output = formato.format(valor);
		return output;
	}

	public static String formatNumber(Double valor, Integer casasDecimais) {
		DecimalFormat formato = new DecimalFormat();
		formato.setMinimumFractionDigits(casasDecimais);
		String output = formato.format(valor);
		return output;
	}

	public static String formatNumber(Double valor, Integer casasDecimais, Locale locale) {
		DecimalFormat formato = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(locale));
		formato.setMinimumFractionDigits(casasDecimais);
		String output = formato.format(valor);
		return output;
	}

	public static String formatNumber(Double valor, Locale locale) {
		DecimalFormat formato = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(locale));
		formato.setMinimumFractionDigits(2);
		String output = formato.format(valor);
		return output;
	}

	public static String formatNumber(String format, Number value) {

		NumberFormat f = new DecimalFormat(format);

		return f.format(value);

	}

	public static boolean isNotNullOrZero(final Long id) {
		return !isNullOrZero(id);
	}

	public static boolean isNullOrZero(final Long id) {
		return id == null || id.longValue() == 0;
	}

	public static boolean isNumber(final String number) {

		boolean retorno = false;

		try {
			Long.parseLong(number.replace(".", ""));
			retorno = true;
		} catch (final Exception e) {
			// não faz nada
		}

		return retorno;

	}

	private NumberUtils() {
		// construtor privado para não ser instanciado
	}

}