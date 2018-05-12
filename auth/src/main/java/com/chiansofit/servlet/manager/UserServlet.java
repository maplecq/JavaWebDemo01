package com.chiansofit.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chiansofit.entity.Role;
import com.chiansofit.entity.User;
import com.chiansofit.services.Service;
import com.chiansofit.util.CopyBean;

public class UserServlet extends HttpServlet {
	private Service service = new Service();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("showAllUser"))
			showAllUser(request, response);
		else if (method.equals("showInsertUser"))
			showInsertUser(request, response);
		else if (method.equals("addUser"))
			addUser(request, response);
		else if (method.equals("showUpdateUser"))
			showUpdateUser(request, response);
		else if (method.equals("updateUser"))
			updateUser(request, response);
		else if (method.equals("deleteUser"))
			deleteUser(request, response);
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("id");
		try {
			service.deleteUser(userId);
			request.setAttribute("message", "删除用户成功");
		} catch (RuntimeException e) {
			request.setAttribute("message", "删除用户失败");
		}
		request.getRequestDispatcher("/WEB-INF/message/message.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		try {
			CopyBean.Copy(user, request.getParameterMap());
			String[] roleId = request.getParameterValues("rid");
			service.updateUser(user, roleId);
			request.setAttribute("message", "修改用户成功");
		} catch (RuntimeException e) {
			request.setAttribute("message", "修改用户失败，原因：" + e.getMessage());
		}
		request.getRequestDispatcher("/WEB-INF/message/message.jsp").forward(request, response);
	}

	private void showUpdateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("id");
		User user = service.findUserById(userId);
		List<Role> role = service.fineAllRole();
		request.setAttribute("user", user);
		request.setAttribute("role", role);
		request.getRequestDispatcher("/WEB-INF/manager/updateUser.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	private void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		try {
			CopyBean.Copy(user, request.getParameterMap());
			service.insertUser(user, null);
			request.setAttribute("message", "添加用户成功");
		} catch (RuntimeException e) {
			request.setAttribute("message", "添加用户失败，原因：" + e.getMessage());
		}
		request.getRequestDispatcher("/WEB-INF/message/message.jsp").forward(request, response);
	}

	private void showInsertUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/manager/addUser.jsp").forward(request, response);
	}

	private void showAllUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> user = service.findAllUser();
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/manager/userlist.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
