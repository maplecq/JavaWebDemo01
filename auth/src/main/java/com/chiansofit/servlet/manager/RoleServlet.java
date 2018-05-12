package com.chiansofit.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chiansofit.entity.Permission;
import com.chiansofit.entity.Role;
import com.chiansofit.services.Service;
import com.chiansofit.util.CopyBean;

public class RoleServlet extends HttpServlet {
	private Service service = new Service();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("showAllRole"))
			showAllRole(request, response);
		else if (method.equals("showInsertRole"))
			showInsertRole(request, response);
		else if (method.equals("insertRole"))
			insertRole(request, response);
		else if (method.equals("showUpdateRole"))
			showUpdateRole(request, response);
		else if (method.equals("updateRole"))
			updateRole(request, response);
		else if (method.equals("deleteRole"))
			deleteRole(request, response);
	}

	private void deleteRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roleId = request.getParameter("id");
		try {
			service.deleteRole(roleId);
			request.setAttribute("message", "删除角色成功");
		} catch (RuntimeException e) {
			request.setAttribute("message", "删除角色失败，原因：" + e.getMessage());
		}
		request.getRequestDispatcher("/WEB-INF/message/message.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	private void updateRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Role role = new Role();
		try {
			CopyBean.Copy(role, request.getParameterMap());
			String[] permissionId = request.getParameterValues("pid");
			service.updateRole(role, permissionId);
			request.setAttribute("message", "修改角色成功");
		} catch (RuntimeException e) {
			request.setAttribute("message", "修改角色失败，原因：" + e.getMessage());
		}
		request.getRequestDispatcher("/WEB-INF/message/message.jsp").forward(request, response);
	}

	private void showUpdateRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roleId = request.getParameter("id");
		Role role = service.findRoleById(roleId);
		List<Permission> permission = service.findAllPermission();
		request.setAttribute("role", role);
		request.setAttribute("permission", permission);
		request.getRequestDispatcher("/WEB-INF/manager/updateRole.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	private void insertRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Role role = new Role();
		try {
			CopyBean.Copy(role, request.getParameterMap());
			service.insertRole(role, null);
			request.setAttribute("message", "添加角色成功");
		} catch (RuntimeException e) {
			request.setAttribute("message", "添加角色失败，原因：" + e.getMessage());
		}
		request.getRequestDispatcher("/WEB-INF/message/message.jsp").forward(request, response);
	}

	private void showInsertRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/manager/addRole.jsp").forward(request, response);
	}

	private void showAllRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Role> role = service.fineAllRole();
		request.setAttribute("role", role);
		request.getRequestDispatcher("/WEB-INF/manager/rolelist.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
