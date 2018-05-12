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
<title>添加资源</title>
</head>

<body>
	<form
		action="<%=basePath%>servlet/manager/ResourceServlet?method=insertResource"
		method="post">
		<table>
			<tr>
				<td>资源uri</td>
				<td><input type="text" name="uri"></td>
			</tr>
			<tr>
				<td>资源描述</td>
				<td><textarea rows="3" cols="50" name="description"></textarea>
				</td>
			</tr>
			<tr>
				<td>资源控制权限</td>
				<td><c:forEach var="p" items="${requestScope.permission}">
						<input type="radio" name="pid" value="${p.id }">${p.name }    
                </c:forEach></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="添加资源"></td>
			</tr>
		</table>
	</form>
</body>
</html>
