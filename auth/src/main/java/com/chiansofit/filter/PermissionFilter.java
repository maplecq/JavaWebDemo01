package com.chiansofit.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chiansofit.entity.Permission;
import com.chiansofit.entity.Resource;
import com.chiansofit.entity.User;
import com.chiansofit.services.Service;

public class PermissionFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest r, ServletResponse re, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) r;
		HttpServletResponse response = (HttpServletResponse) re;
		Service service = new Service();

		// 判断要访问的资源是否需要权限
		String requestURI = request.getRequestURI();
		requestURI = requestURI.substring(1);
		Resource resource = service.findResourceByURI(requestURI);
		// 如果不需要权限放行
		if (resource == null) {
			chain.doFilter(request, response);
			return;
		}
		Permission permission = resource.getPermission();
		// 如果需要权限验证用户是否登陆
		Object attribute = request.getSession().getAttribute("user");
		// 如果没有登录则跳转登录页面
		if (attribute == null) {
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
			return;
		}
		// 如果已经登录获取用户权限
		User user = (User) attribute;
		List<Permission> userPermission = service.getUserPermission(user);
		// 如果有权访问则放行
		if (userPermission.contains(permission)) {
			chain.doFilter(request, response);
			return;
		}
		// 如果没权访问则跳转消息显示页面
		request.setAttribute("message", "对不起您没有权限");
		request.getRequestDispatcher("/WEB-INF/message/message.jsp").forward(request, response);

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
