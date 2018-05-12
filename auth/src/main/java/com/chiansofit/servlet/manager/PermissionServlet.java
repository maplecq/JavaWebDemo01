package com.chiansofit.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chiansofit.entity.Permission;
import com.chiansofit.services.Service;
import com.chiansofit.util.CopyBean;

public class PermissionServlet extends HttpServlet {
	private Service service = new Service();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("showAllpermissoin"))
			showAllpermissoin(request, response);
		else if (method.equals("showInsertPermission"))
			showInsertPermission(request, response);
		else if (method.equals("insertPsermission"))
			insertPsermission(request, response);
		else if (method.equals("deletePermission"))
			deletePermission(request, response);
	}

	private void deletePermission(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String permissionId = request.getParameter("id");
		try {
			service.deletePermission(permissionId);
			request.setAttribute("message", "删除权限成功");
		} catch (RuntimeException e) {
			request.setAttribute("message", "删除权限失败");
		}
		request.getRequestDispatcher("/WEB-INF/message/message.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	private void insertPsermission(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Permission permission = new Permission();
		try {
			CopyBean.Copy(permission, request.getParameterMap());
			service.insertPermission(permission);
			request.setAttribute("message", "添加权限成功");
		} catch (RuntimeException e) {
			request.setAttribute("message", "添加权限失败");
		}
		request.getRequestDispatcher("/WEB-INF/message/message.jsp").forward(request, response);
	}

	private void showInsertPermission(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/manager/addPermission.jsp").forward(request, response);
	}

	private void showAllpermissoin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Permission> permission = service.findAllPermission();
		request.setAttribute("permission", permission);
		request.getRequestDispatcher("/WEB-INF/manager/permissionlist.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
