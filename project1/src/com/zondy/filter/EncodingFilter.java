package com.zondy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class EncodingFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = 19876543646647646L;

	private FilterConfig filterConfig = null;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		response.setContentType("text/html");
		String encoding = this.filterConfig.getInitParameter("encoding");
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
	}

	public EncodingFilter() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void init() throws ServletException {
		
	}
}
