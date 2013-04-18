package org.andvicoso.shopand.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andvicoso.shopand.model.entity.user.User;

@WebFilter(urlPatterns = { "/user/*", "/admin/*" })
public class LoginFilter implements Filter {

	// private final Log log = LoggerUtils.getLog(this);

	public void destroy() {
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void doFilter(final ServletRequest req, final ServletResponse res,
			final FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;

		// log.debug("Procurando usuário na sessão...");

		final User user = (User) request.getSession().getAttribute("user");

		if (user == null || !validResource(request, user)) {
			// log.debug("Usuário não encontrado! Redirecionando...");
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else {
			// log.debug("Usuário encontrado, continuando cadeia de filtros...");
			chain.doFilter(request, response);
		}
	}

	private boolean validResource(HttpServletRequest request, User user) {
		final String url = request.getRequestURI().replace(
				request.getContextPath(), "");

		return validUrl(url, user);
	}

	private boolean validUrl(String url, User user) {
		return url.startsWith("/view/" + user.getTypeText());
	}

}
