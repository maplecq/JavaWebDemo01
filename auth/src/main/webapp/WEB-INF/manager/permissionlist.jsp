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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">   
<title>权限列表</title>
</head>
<body>
<table width="90%">
		<tr>
			<td align="right"><a
				href="<%=basePath%>servlet/manager/PermissionServlet?method=showInsertPermission">添加权限</a>
			</td>
		</tr>
	</table>

	<table frame="border" width="90%">
		<tr>
			<td>权限名称</td>
			<td>权限描述</td>
			<td>操作</td>
		</tr>
		<c:forEach var="p" items="${requestScope.permission}">
			<tr>
				<td>${p.name }</td>
				<td>${p.description }</td>
				<td>
					<a
					href="<%=basePath %>servlet/manager/PermissionrServlet?method=deletePermission&id=${p.id }">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</body>
</html>