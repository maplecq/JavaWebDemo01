<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="sitemesh-decorator"%>
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
<title><sitemesh-decorator:title /></title>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	text-align: center;
}

#container {
	width: 980px;
	border: 1px solid gray;
}

#top {
	border-bottom: 1px solid gray;
}

#left {
	float: left;
	width: 150px;
	border-right: 1px solid gray;
}

#main {
	float: left;
	padding: 40px 0px 0px 40px;
}
</style>
</head>
<body>
	<br />
	<br />
	<br />
	<div id="container">
		<div id="top">
			<h2>中浩集团网站后台管理系统</h2>
		</div>
		<div id="left">
			<br /> <br /> <br /> <a
				href="<%=basePath %>servlet/manager/ResourceServlet?method=showAllresource">资源管理</a><br />
			<br /> <br /> <a
				href="<%=basePath %>servlet/manager/PermissionServlet?method=showAllpermissoin">权限管理</a><br />
			<br /> <br /> <a
				href="<%=basePath %>servlet/manager/RoleServlet?method=showAllRole">角色管理</a><br />
			<br /> <br /> <a
				href="<%=basePath %>servlet/manager/UserServlet?method=showAllUser">用户管理</a><br />
			<br /> <br />
		</div>
		<div id="main">
			<sitemesh-decorator:body></sitemesh-decorator:body>
		</div>
	</div>
</body>
</html>