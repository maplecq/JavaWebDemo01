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
<title>登录页面</title>
</head>

<body>
	<form action="<%=basePath%>Welcome"
		method="post">
		用户名：<input type="text" name="username"><br> 密码:<input
			type="password" name="password"><br> <input
			type="submit" value="登录">
	</form>
</body>
</html>