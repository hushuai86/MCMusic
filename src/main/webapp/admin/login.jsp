<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
				background: url(../admin/img/bg.jpg);
				background-size: 100%;
			}
			
			.main {
				width: 600px;
				margin: 0 auto;
				margin-top: 150px;
				background-color: transparent;
			}
			
			.formbox {
				padding: 30px;
				padding-left: 0;
			}
			
			.formbox input {
				background-color: transparent;
			}
			.layui-input{
			    border-color:#666;
				width: 400px;
			}
			.layui-btn{
				margin-left: 100px;
				margin-top: 20px;
				margin-right:30px;
				border-radius:10px;
			}
			.layui-elem-field{
			  border:1px solid #666 ;
			}
		</style>
		<script src="js/jquery-1.8.3.min.js"></script>
		<script src="layui/layui.js"></script>
	</head>

	<body>
		<div class="main">
			<fieldset class="layui-elem-field">
				<legend id="le">登录</legend>
				<div class="formbox">
					<form class="layui-form">
						<div class="layui-form-item">
							<label class="layui-form-label">帐号</label>
							<div class="layui-input-block">
								<input type="text" id="loginid" name="loginid" required lay-verify="required" placeholder="帐号" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">密码</label>
							<div class="layui-input-inline">
								<input type="password" id="password" name="passwoed" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux"></div>
						</div>

						<div class="layui-form-item">
							<div class="layui-input-block">
								<button id="btn" class="layui-btn" lay-submit lay-filter="res">登录</button>
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
							</div>
						</div>

						
					</form>

				</div>
			</fieldset>	
			<script>
				//Demo
		         layui.use('layer', function(){
                       var layer = layui.layer;
                       layer.config({
                    	   offset: '280px'
                    	 });
                    });  
		         
				layui.use('form', function(){
					var form = layui.form;
					//监听提交
					form.on('submit(res)', function() {
						var loadIn = layer.load(1, {
							shade: [0.1, '#fff'] //0.1透明度的白色背景
						});
                        var loginid=$("#loginid").val();
                        var password=$("#password").val();
                        var btn= $("#btn").html();
                        if(btn=="登录"){          
						$.post(	
					         "adminLoginAjax",
							 {loginid:loginid,password:password},
							function(msg) {
								if (msg.code == "1") {
									parent.layer.msg("登录成功");
									setTimeout(function(){
										location.href =msg.msg;
									}, 2000);
								} else {
									$("#password").val("");
									$("#password").focus();
									layer.close(loadIn);
									parent.layer.msg(msg.msg);
								}

							}

						);
                        }else{           
                        	$.post(	
       					         "adminRegistAjax",
       							 {loginid:loginid,password:password},
       							function(msg) {
       									layer.msg("注册成功,正在登陆");
       									setTimeout(function() {
       										location.href =msg;
       									}, 3000);
       							}
       						);
                        }
						return false;
					});
				});

				$(document).ready(function() {
				 if(self!=top){    
					    //不是顶层页面 
					      parent.location.href="login.jsp";    
					} 
					layui.use('layer', function() {
						var layer =layui.layer;
						  $.post( 
			                  "adminAjax",
							   function(msg) {
			                   if(msg.code==0){
			                           $("#btn").html("注册");
			                           $("#le").html("注册");
			                           parent.layer.msg(msg.msg);
			                           }
			                		 });     

							});

					});
					
                
			</script>
		</div>

		<div style="position: fixed; bottom: 3;  color:black;width: 100%;text-align: center;">问题反馈：11218821@qq.com &nbsp;&nbsp;<a href="../index.html"style="color:red">回到首页</a></div>
	</body>

</html>