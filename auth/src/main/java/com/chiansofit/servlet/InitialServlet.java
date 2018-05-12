package com.chiansofit.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chiansofit.services.InitialService;

public class InitialServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InitialService iniService = new InitialService();
		String message = null;
		try {
			message = iniService.initial();
			request.setAttribute("message", message);
		} catch (Exception e) {
			request.setAttribute("message", message);
		}
		request.getRequestDispatcher("/WEB-INF/message/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
