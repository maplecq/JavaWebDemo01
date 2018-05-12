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
<title>添加权限</title>
</head>

<body>
	<form
		action="<%=basePath %>servlet/manager/PermissionServlet?method=insertPsermission"
		method="post">
		<table>
			<tr>
				<td>权限名称</td>
				<td><input type="text" name="name"></td>
			</tr>

			<tr>
				<td>权限描述</td>
				<td><textarea rows="3" cols="50" name="description"></textarea>
				</td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="添加权限"></td>
			</tr>
		</table>
	</form>
</body>