<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>MC Music 用户管理</title>
		<link rel="stylesheet" href="css/base.css"/>
		<link rel="stylesheet" href="layui/css/layui.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="layui/layui.js"></script>
	</head>

	<body>
		<fieldset class="layui-elem-field layui-field-title">
			<legend>基本操作</legend>
		</fieldset>
		<a href="addUser.jsp"><button class="layui-btn">添加用户</button></a>
		<a href="toFreezeUser"><button class="layui-btn">已冻结用户</button></a>
		<a href="toEmailUser"><button class="layui-btn">邮箱已激活用户</button></a>
		<fieldset class="layui-elem-field layui-field-title">
			<legend>基本查询</legend>
		</fieldset>
		<form class="layui-form" action="adminListUser" method="post">
			<div style="width: 800px;float: left;margin-right: 5px;">
				<input type="text" id="search" name="search" value="${search}" lay-verify="required" placeholder="请输入关键字" autocomplete="off" class="layui-input">
			</div>
			<button type="submit" class="layui-btn layui-btn-primary">搜索</button>

		</form>
		<br /><br /><br />
		<fieldset class="layui-elem-field layui-field-title">
			<legend>专辑列表</legend>
		</fieldset>
		<div class="center">
			<table>
				<thead>
					<tr>
					    <th>用户头像</th>
						<th>用户账号</th>
						<th>用户名称</th>
						<th>用户性别</th>
						<th>用户邮箱</th>
						<th>用户手机</th>
						<th>注册日期</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody class="tb">
			<c:forEach items="${pageBasePo.list}" var="user">
					<tr>
					    <td><img src="../${user.headsculptureurl}"/></td>
						<td>${user.loginid}</td>
						<td>${user.username}</td>
						<td>${user.usersex}</td>
						<td>${user.email}</td>
						<td>${user.phone}</td>
						<td><fmt:formatDate value="${user.registationdate}"
								type="date" /></td>
						<td><a href="toAdminUpdateUser?userId=${user.userid}&type=all"><button
									class="layui-btn layui-btn-small">
									<i class="layui-icon"></i>
									<button></a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<div id="ul"></div>

		</div>
		<script type="text/javascript">
		//JavaScript代码区域
		layui.use(['element', 'layer', 'laypage'],
						function() {
							var element = layui.element;
							var $ = layui.jquery, layer = layui.layer;
							var laypage = layui.laypage, layer = layui.layer;
							//删除按钮触发事件
								confirmTrans = function(id) {
									//配置一个透明的询问框
									parent.layer.msg('确定要删除吗？', {
										btn : [ '确定', '返回' ],
									    yes: function(){
									     location.href="adminDeleteUser?userId="+id;
								        }
									});
								}



							//日期格式化
							Date.prototype.toLocaleString = function() {
								return this.getFullYear() + "年"
										+ (this.getMonth() + 1) + "月"
										+ this.getDate() + "日 ";
							};
							//分页
							var pageCount = 5 * ${pageBasePo.pageCount};
							var last = ${pageBasePo.pageCount};
							laypage
									.render({
										elem : 'ul',
										limit : 5,
										count : pageCount,
										first : '首页',
										last : last,
										prev : '<em>←</em>',
										next : '<em>→</em>',
										jump : function(obj, first) {//页面跳转执行函数	
											var search = $("#search").val();
											var pageIndex = obj.curr;//当前页码
											if (!first) { //首次不执行
												$.get(//ajax刷新数据
													"adminPageUser",
													{search : search,
													pageIndex : pageIndex},
													function(data) {
														$(".tb").html("");//清空子节点
														data.list.forEach(function(value,index) {//数据渲染
																	var date = new Date(value.registationdate);
																	date = date.toLocaleString(); //日期格式化
																	var $tr = $('<tr><td><img src="../'+value.headsculptureurl+'"/></td>'
																						+ '<td>'
																						+ value.loginid
																						+ '</td>'
																						+ '<td>'
																						+ value.username
																						+ '</td>'
																						+ '<td>'
																						+ value.usersex
																						+ '</td>'
																						+ '<td>'
																						+ value.email
																						+ '</td>'
																						+ '<td>'
																						+ value.phone
																						+ '</td>'
																						+ '<td>'
																						+ date
																						+ '</td>'
																						+ '<td><a href="toAdminUpdateUser?userId='
																						+ value.userid
																						+ '&type=all'
																						+ '"><button class="layui-btn layui-btn-small"><i class="layui-icon"></i><button></a>'
																						+ '</td></tr>');
																				$(".tb").append($tr);
																			});
																}

														);
											}
										}
									});

						});
	</script>
	</body>

</html>