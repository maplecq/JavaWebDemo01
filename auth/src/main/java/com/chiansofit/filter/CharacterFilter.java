package com.chiansofit.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest r, ServletResponse re, FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) r;
		HttpServletResponse response = (HttpServletResponse) re;
		response.setCharacterEncoding("utf-8");
		chain.doFilter((ServletRequest) Proxy.newProxyInstance(CharacterFilter.class.getClassLoader(),
				request.getClass().getInterfaces(), new InvocationHandler() {
					@SuppressWarnings("unchecked")
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if (method.getName().equals("getParameter")) {
							String value = (String) method.invoke(request, args);
							String newValue = new String(value.getBytes("iso8859-1"), "utf-8");
							return newValue;
						} else if (method.getName().equals("getParameterMap")) {
							Map<String, String[]> values = (Map<String, String[]>) method.invoke(request, args);
							Map<String, String[]> newValues = new HashMap<String, String[]>();
							for (Map.Entry<String, String[]> entry : values.entrySet()) {
								String[] value = entry.getValue();
								String[] newValue = new String[value.length];
								for (int i = 0; i < value.length; i++) {
									newValue[i] = new String(value[i].getBytes("iso8859-1"), "utf-8");
								}
								newValues.put(entry.getKey(), newValue);
							}
							return newValues;
						} else if (method.getName().equals("getParameterValues")) {
							String[] values = (String[]) method.invoke(request, args);
							if (values == null)
								return null;
							String[] newValues = new String[values.length];
							for (int i = 0; i < values.length; i++) {
								newValues[i] = new String(values[i].getBytes("iso8859-1"), "utf-8");
							}
							return newValues;
						}
						return method.invoke(request, args);
					}
				}), response);

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
