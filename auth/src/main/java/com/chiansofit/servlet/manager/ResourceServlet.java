package com.chiansofit.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chiansofit.entity.Permission;
import com.chiansofit.entity.Resource;
import com.chiansofit.services.Service;
import com.chiansofit.util.CopyBean;

public class ResourceServlet extends HttpServlet {
	private Service service = new Service();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("showAllresource"))
			showAllresource(request, response);
		else if (method.equals("showInsertResource"))
			showInsertResource(request, response);
		else if (method.equals("insertResource"))
			insertResource(request, response);
		else if (method.equals("showUpdateResource"))
			showUpdateResource(request, response);
		else if (method.equals("updateResource"))
			updateResource(request, response);
		else if (method.equals("deleteResource"))
			deleteResource(request, response);
	}

	private void deleteResource(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			service.deleteResource(id);
			request.setAttribute("message", "删除资源成功");
		} catch (RuntimeException e) {
			request.setAttribute("message", "删除资源失败");
		}
		request.getRequestDispatcher("/WEB-INF/message/message.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	private void updateResource(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Resource resource = new Resource();
		try {
			CopyBean.Copy(resource, request.getParameterMap());
			String permissionId = request.getParameter("pid");
			service.updateResource(resource, permissionId);
			request.setAttribute("message", "修改资源成功");
		} catch (RuntimeException e) {
			request.setAttribute("message", "修改资源失败，原因：" + e.getMessage());
		}
		request.getRequestDispatcher("/WEB-INF/message/message.jsp").forward(request, response);
	}

	private void showUpdateResource(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Permission> permission = service.findAllPermission();
		String resourceId = request.getParameter("id");
		Resource resource = service.findResourceById(resourceId);
		request.setAttribute("permission", permission);
		request.setAttribute("resource", resource);
		request.getRequestDispatcher("/WEB-INF/manager/updateResource.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	private void insertResource(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Resource resource = new Resource();
			CopyBean.Copy(resource, request.getParameterMap());
			String permissionId = request.getParameter("pid");
			service.insertResource(resource, permissionId);
			request.setAttribute("message", "添加资源成功");
		} catch (RuntimeException e) {
			request.setAttribute("message", "添加资源失败");
		}
		request.getRequestDispatcher("/WEB-INF/message/message.jsp").forward(request, response);
	}

	private void showInsertResource(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Permission> permission = service.findAllPermission();
		request.setAttribute("permission", permission);
		request.getRequestDispatcher("/WEB-INF/manager/addResource.jsp").forward(request, response);
	}

	private void showAllresource(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Resource> resources = service.findAllResource();
		request.setAttribute("resources", resources);
		request.getRequestDispatcher("/WEB-INF/manager/resourcelist.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
