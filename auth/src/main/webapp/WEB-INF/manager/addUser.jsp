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
<title>添加用户</title>
</head>

<body>
	<form action="<%=basePath%>servlet/manager/UserServlet?method=addUser"
		method="post">
		<table>
			<tr>
				<td>用户名称</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>用户密码</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="添加用户"></td>
			</tr>
		</table>
	</form>
</body>
</html>
