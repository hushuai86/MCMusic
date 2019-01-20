<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>MC Music 个人信息管理</title>
		<link rel="stylesheet" href="layui/css/layui.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="layui/layui.js"></script>
	</head>

	<body>
		<div class="center">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>基本信息</legend>
			</fieldset>
			<div class="message">
				<form id="form1" method="post">
				<input type="hidden" id="adminid" name="adminid"  value="${admin.adminid}"/>
					<div class="layui-form-item">
						<label class="layui-form-label">管理员账号</label>
						<div class="layui-input-block">
							<input id="loginid" name="loginid" lay-verify="required" value="${admin.loginid}"  autocomplete="off" placeholder="请输入用户账号" class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">管理员名称</label>
						<div class="layui-input-block">
							<input id="adminname" name="adminname" lay-verify="required" value="${admin.adminname}" lay-verify="required" autocomplete="off" placeholder="请输入用户名称" class="layui-input" type="text">
						</div>
					</div>
					<button class="layui-btn layui-btn-radius" style="position: absolute;top: 200px;left: 160px;"lay-submit lay-filter="info">提交</button>
					<button type="reset"   class="layui-btn layui-btn-normal layui-btn-radius" style="position: absolute;top: 200px;left: 260px;">重置</button>
				</form>
			</div>

		</div>
		<script>
			//JavaScript代码区域
          
		layui.use('form', function(){
			var form = layui.form;
			//监听提交
			form.on('submit(info)', function() {
				 var adminid=$("#adminid").val();
                var loginid=$("#loginid").val();
                var adminname=$("#adminname").val();
                 if(loginid==""||adminname==""){
                	 parent.layer.msg("输入框未填写");
                 }
                 else{
                	  	$.post(	
   					         "adminUpdateInfo",
   							 {adminid:adminid,loginid:loginid,adminname:adminname},
   							function(msg){		
   								 parent.layer.msg("修改成功");
   									setTimeout(function(){
   										parent.location.reload();
   									}, 1000);
   								});
                 }
                 
				return false;
			});
		});
		</script>
	</body>

</html>