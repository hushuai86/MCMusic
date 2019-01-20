<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="layui/css/layui.css" />
		<style>
			body {
				width: 100%;
				height: 100%;
				margin: 0;
				padding: 0;
				background: url(img/error.jpg);
				background-size: 100%;
			}
			.return{
				margin-top: 280px;
				margin-left: 850px;
			}
			.return a{
				font:;
				font-size: 18px;
				font-weight: bold;
			}
		</style>
	</head>
	<body>
		<div class="return">
			<a href="index.html">返回首页</a>
		</div>
	</body>
</html>
