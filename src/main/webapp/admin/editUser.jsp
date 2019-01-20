<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<title>MC Music user修改</title>
		<link rel="stylesheet" href="layui/css/layui.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="layui/layui.js"></script>
	</head>

	<body>

		<div class="center">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>修改用户基本信息</legend>
			</fieldset>
			<div class="message">
				<form action="adminUpdateUser" method="post">
				    <input  type="hidden" name="url" id="url"/>
				    <input  type="hidden" name="userid" value="${userPo.userid}"/>
				    <input  type="hidden" name="type" value="${type}"/>
					<div class="layui-form-item">
						<label class="layui-form-label">用户账号</label>
						<div class="layui-input-block">
							<input name="loginid" lay-verify="title" required value="${userPo.loginid}" autocomplete="off" placeholder="请输入用户账号" class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">用户密码</label>
						<div class="layui-input-block">
							<input name="password" lay-verify="title" autocomplete="off" placeholder="不输入则不修改密码" class="layui-input" type="password">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">用户名称</label>
						<div class="layui-input-block">
							<input name="username" lay-verify="title" required value="${userPo.username}"autocomplete="off" placeholder="请输入用户密码" class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">用户性别</label>
						<div class="layui-input-block" >
							<input style="margin-top: 10px;" name="usersex" value="男" title="男" checked="" type="radio">男
							<input name="usersex" value="女" title="女" type="radio">女
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">个人签名</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" required name="sign" class="layui-textarea" style="width: 300px;">${userPo.sign}</textarea>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">用户邮箱</label>
						<div class="layui-input-block">
							<input name="email" lay-verify="title" required value="${userPo.email}" autocomplete="off" placeholder="请输入用户邮箱" class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">用户手机</label>
						<div class="layui-input-block">
							<input name="phone" required value="${userPo.phone}" lay-verify="title" autocomplete="off" placeholder="请输入用户邮箱" class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">用户类型</label>
						<div class="layui-input-inline" style="margin-top: 10px">
							<select id="usertype" name="usertype">
        									<option value="">请选择用户类型</option>
        									<option value="0" selected="">0</option>
        									<option value="1">1</option>
      						</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">用户头像</label>
						<label class="layui-form-label"><img id="img" src="../${userPo.headsculptureurl}" style="height:40px;width: 40px"></label>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">上传头像</label>

					    <div class="layui-upload">
							<button name="file"type="button" class="layui-btn layui-btn-danger" id="test7"><i class="layui-icon"></i>上传图片</button>
							<div class="layui-upload-list">
								<img class="layui-upload-img" id="demo1">
								<p id="demoText" name="coverUrl"></p>
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">注册日期</label>
						<div class="layui-input-inline">
							<input name="date" id="date" value="<fmt:formatDate value="${userPo.registationdate}"
								type="date" />" lay-verify="date" required placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">用户状态</label>
						<div class="layui-input-inline" style="margin-top: 10px">
							<select id="userstateid" name="userstateid">
        									<option value="">请选择用户状态</option>
        									<option value="0" selected="">0</option>
        									<option value="1">1</option>
        									<option value="2">2</option>
      						</select>
						</div>
					</div>	
					<button class="layui-btn layui-btn-radius" style="position: absolute;top: 820px;left:160px;">提交</button>
					<button onclick="window.history.go(-1)"  class="layui-btn layui-btn-normal layui-btn-radius" style="position: absolute;top:820px;left: 280px;">返回</button>
				</form>
			</div>

		</div>
		<script>
			//JavaScript代码区域
			
			//下拉框选中
			$(function(){
				$("#userstateid").find("option[value=${userPo.userstateid}]").attr("selected",true);
				$("#usertype").find("option[value=${userPo.usertype}]").attr("selected",true);
			});
            
			layui.use('element', function() {
				var element = layui.element;

			});
			
			layui.use('element', function() {
				var element = layui.element;

			});
			var url = $("#url").val();
			layui.use('upload', function() {
				var $ = layui.jquery,
					upload = layui.upload;

				upload.render({
					elem: '#test7',
					url: 'upUserImage',
					size: 1024*4 ,//限制文件大小，单位 KB
					field:'file',
					done: function(res){
						$("#url").val(res.token);
						$("#img").attr('src','../'+res.token); 
						console.log(res.token);
					}
				});
			});
			layui.use('laydate', function() {
				var laydate = layui.laydate;
				
				//常规用法
				laydate.render({
					elem: '#date'
				});
			});
		</script>
	</body>

</html>
