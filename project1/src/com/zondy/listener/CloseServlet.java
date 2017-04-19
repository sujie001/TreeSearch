package com.zondy.listener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CloseServlet extends HttpServlet {

	public CloseServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String isBad = request.getParameter("isBad");
		if("false".equals(isBad)){
			request.getSession().removeAttribute("user");
		}
		request.getSession().invalidate();
		
	}

	public void init() throws ServletException {
	}

}
