package org.andvicoso.shopand.infra.utils;

import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class LoggerUtils {

	public static Logger getLogger(Object object) {
		return Logger.getLogger(object.getClass().getName());
	}

	public static Log getLog(Object object) {
		return LogFactory.getLog(object.getClass());
	}

	public static String getStackTrace(Exception exception) {

		StringBuilder builder = new StringBuilder();

		StackTraceElement[] stack = exception.getStackTrace();

		builder.append("Causado por: ").append(exception.getClass().getName());

		for (StackTraceElement stackElement : stack) {
			builder.append("\n\tat ").append(stackElement.toString());
		}

		return builder.toString();
	}

	public static void logException(Object object, Exception exception) {
		Logger logger = getLogger(object);
		logger.severe(getStackTrace(exception));
	}

	private LoggerUtils() {
		// construtor privado para não ser instanciado
	}

}
