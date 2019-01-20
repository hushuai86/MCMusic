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
		<title>MC Music 修改密码</title>
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
				<form method="post">
					<div class="layui-form-item">
						<label class="layui-form-label">旧密码</label>
						<div class="layui-input-block">
							<input id="oldPassword" name="oldPassword" lay-verify="required"  autocomplete="off" placeholder="请输入旧密码" class="layui-input" type="password">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">新密码</label>
						<div class="layui-input-block">
							<input id="newPassword"name="newPassword" lay-verify="required"  autocomplete="off" placeholder="请输入新密码" class="layui-input" type="password">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">确认密码</label>
						<div class="layui-input-block">
							<input id="confirmPassword" name="confirmPassword" lay-verify="required"  autocomplete="off" placeholder="请确认新密码" class="layui-input" type="password">
						</div>
					</div>
					<button class="layui-btn layui-btn-radius" style="position: absolute;top: 250px;left: 160px;"lay-submit lay-filter="pwd">提交</button>
					<button type="reset"  class="layui-btn layui-btn-normal layui-btn-radius" style="position: absolute;top: 250px;left: 260px;">重置</button>
				</form>
			</div>

		</div>
		<script>
	
       layui.use('form', function(){
			var form = layui.form;
			//监听提交
			form.on('submit(pwd)', function() {
                var newPassword=$("#newPassword").val();
                var oldPassword=$("#oldPassword").val();
                var confirmPassword=$("#confirmPassword").val();
             if(newPassword==""||oldPassword==""||confirmPassword==""){
            	 parent.layer.msg("输入框未填写");
             }else{
             if(newPassword!=confirmPassword){ 
                	$("#newPassword").val("");
					$("#confirmPassword").val("");
					$("#oldPassword").focus();
					parent.layer.msg("两次密码输入不一致");
                }else if(newPassword==oldPassword){
                	$("#newPassword").val("");
					$("#confirmPassword").val("");
					$("#oldPassword").focus();
					parent.layer.msg("新密码与原密码相同");
                } else{
                   	$.post(	
					         "adminUpdatePwd",
							 {newPassword:newPassword,oldPassword:oldPassword},
							function(msg) {
								if (msg.code == "1"){
									parent.layer.msg("密码修改成功");
									setTimeout(function(){
										parent.location.href=msg.msg;
									}, 1000);
								} else {
									parent.layer.msg(msg.msg);
									$("#oldPassword").val("");
									$("#newPassword").val("");
									$("#confirmPassword").val("");
									$("#newPassword").focus();
									layer.close(loadIn);
									
								}

							}

						);
               }
             }
                
				return false;
			});
		});
		</script>
	</body>

</html>