package com.chiansofit.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chiansofit.entity.User;
import com.chiansofit.services.Service;

public class Welcome extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login/login.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service service = new Service();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = service.login(username, password);
		if (user != null) {
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else {
			request.setAttribute("message", "用户名密码错误");
			request.getRequestDispatcher("/WEB-INF/message/message.jsp").forward(request, response);
		}
	}
}
