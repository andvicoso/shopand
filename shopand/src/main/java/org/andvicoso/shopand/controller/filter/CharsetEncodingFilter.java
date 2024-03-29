package org.andvicoso.shopand.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CharsetEncodingFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		servletRequest.setCharacterEncoding("UTF-8");
		servletResponse.setContentType("text/html;charset=UTF-8");

		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void destroy() {
	}
}