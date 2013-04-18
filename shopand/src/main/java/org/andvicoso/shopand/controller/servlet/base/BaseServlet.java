package org.andvicoso.shopand.controller.servlet.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andvicoso.shopand.infra.utils.StringUtils;

public class BaseServlet extends HttpServlet {

	protected static final String GLOBAL_MSG = "global";
	protected static final String REQUIRED_FIELD_MSG = "O campo é obrigatório!";
	protected static final String OPTIONAL_FIELD_MSG = "O campo não foi preenchido!";

	protected boolean isRequestParametersValid(HttpServletRequest request) {
		Map<String, String> msgs = new HashMap<String, String>();

		verifyParameters(request, msgs, getRequiredParameters(),
				REQUIRED_FIELD_MSG);

		return msgs.isEmpty();
	}

	protected Map<String, String> getErrorMessages(HttpServletRequest request) {
		Map<String, String> msgs = new HashMap<String, String>();

		verifyParameters(request, msgs, getRequiredParameters(),
				REQUIRED_FIELD_MSG);

		return msgs;
	}

	protected Map<String, String> getWarningMessages(HttpServletRequest request) {
		Map<String, String> msgs = new HashMap<String, String>();

		verifyParameters(request, msgs, getOptionalParameters(),
				OPTIONAL_FIELD_MSG);

		return msgs;
	}

	private void verifyParameters(HttpServletRequest request,
			Map<String, String> msgs, String[] parameters, String fieldMsg) {
		for (String fieldName : parameters) {
			if (!isParameterValid(request, fieldName))
				msgs.put(fieldName, fieldMsg);
		}
	}

	private boolean isParameterValid(HttpServletRequest request,
			String fieldName) {
		String fieldValue = request.getParameter(fieldName);
		return StringUtils.isNotBlank(fieldValue);
	}

	protected void dispatch(HttpServletRequest request,
			HttpServletResponse response, String url) throws ServletException,
			IOException {
		request.setAttribute(MessageType.ERROR.toString(),
				getErrorMessages(request));
		request.setAttribute(MessageType.WARN.toString(),
				getWarningMessages(request));
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected Long getLong(HttpServletRequest request, String fieldName) {
		String value = request.getParameter(fieldName);
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
		}
		return null;
	}

	protected Integer getInt(HttpServletRequest request, String fieldName) {
		String value = request.getParameter(fieldName);
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
		}
		return null;
	}

	protected Double getDouble(HttpServletRequest request, String fieldName) {
		String value = request.getParameter(fieldName);
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
		}
		return null;
	}

	protected String[] getRequiredParameters() {
		return new String[] {};
	}

	protected String[] getOptionalParameters() {
		return new String[] {};
	}

	protected void redirectToIndex(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		resp.sendRedirect(req.getContextPath() + "/index.jsp");
	}

	protected void addGlobalMsg(HttpServletRequest req, String msg,
			MessageType type) {
		req.setAttribute(GLOBAL_MSG + "." + type, msg);
	}

	public static enum MessageType {
		ERROR, WARN, INFO;
		public String toString() {
			return name().toLowerCase();
		};
	}
}
