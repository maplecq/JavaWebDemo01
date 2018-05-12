<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>角色列表</title>
</head>

<body>
	<table width="90%">
		<tr>
			<td align="right"><a
				href="<%=basePath%>servlet/manager/RoleServlet?method=showInsertRole">添加角色</a>
			</td>
		</tr>
	</table>

	<table frame="border" width="90%">
		<tr>
			<td>角色名称</td>
			<td>角色描述</td>
			<td>角色拥有的权限</td>
			<td>操作</td>
		</tr>
		<c:forEach var="r" items="${role}">
			<tr>
				<td>${r.name }</td>
				<td>${r.description }</td>
				<td><c:forEach var="p" items="${r.permissions}">  
                        ${p.name }  
                    </c:forEach></td>
				<td><a
					href="<%=basePath %>servlet/manager/RoleServlet?method=showUpdateRole&id=${r.id }">分配权限</a>
					<a
					href="<%=basePath %>servlet/manager/RoleServlet?method=deleteRole&id=${r.id }">删除</a>
				</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>
