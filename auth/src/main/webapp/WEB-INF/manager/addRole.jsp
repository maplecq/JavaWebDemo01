<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>添加角色</title>
</head>

<body>
	<form
		action="<%=basePath%>servlet/manager/RoleServlet?method=insertRole"
		method="post">
		<table>
			<tr>
				<td>角色名称</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>角色描述</td>
				<td><textarea rows="3" cols="50" name="description"></textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="添加角色"></td>
			</tr>
		</table>
	</form>
</body>
</html>
