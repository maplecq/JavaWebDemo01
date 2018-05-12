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
<title>用户列表</title>
</head>

<body>
	<table width="90%">
		<tr>
			<td align="right"><a
				href="<%=basePath%>servlet/manager/UserServlet?method=showInsertUser">添加用户</a>
			</td>
		</tr>
	</table>

	<table frame="border" width="90%">
		<tr>
			<td>用户名称</td>
			<td>用户拥有的角色</td>
			<td>操作</td>
		</tr>
		<c:forEach var="u" items="${requestScope.user}">
			<tr>
				<td>${u.username }</td>
				<td><c:forEach var="r" items="${u.roles}">  
                        ${r.name }  
                    </c:forEach></td>
				<td><a
					href="<%=basePath %>servlet/manager/UserServlet?method=showUpdateUser&id=${u.id }">分配角色</a>
					<a
					href="<%=basePath %>servlet/manager/UserServlet?method=deleteUser&id=${u.id }">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
