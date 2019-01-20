<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html>

	<head>

		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>MC Music carousel添加</title>
		<link rel="stylesheet" href="layui/css/layui.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="layui/layui.js"></script>
	</head>

	<body>
		<div class="center">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>carousel基本信息</legend>
			</fieldset>
			<div class="message">
				<form method="post" action="addCarousel" enctype="multipart/form-data">
					<input  type="hidden" name="url" id="url" value=""/>
					<div class="layui-form-item">
						<label class="layui-form-label">名称</label>
						<div class="layui-input-block">
							<input name="imagename"lay-verify="required" autocomplete="off" placeholder="请输入名称" class="layui-input" type="text">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">上传地址</label>
						<div class="layui-upload">
							<button name="file" type="button" class="layui-btn layui-btn-danger" id="test7"><i class="layui-icon"></i>上传图片</button>
							<div class="layui-upload-list">
								<img class="layui-upload-img" id="demo1">
								<p id="demoText" name="imgurl"></p>
							</div>
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">链接地址</label>
						<div class="layui-input-block">
							<input name="imagelink" lay-verify="required" autocomplete="off" placeholder="请输入内容地址" class="layui-input" type="text">
						</div>
					</div>

					<button class="layui-btn layui-btn-radius" style="position: absolute;top: 300px;left:200px;">提交</button>
					<button onclick="window.history.go(-1)"  class="layui-btn layui-btn-normal layui-btn-radius" style="position: absolute;top: 300px;left: 320px;">返回</button>
				</form>
			</div>

		</div>
		<script>
			//JavaScript代码区域
			layui.use('upload', function() {
				var $ = layui.jquery,
					upload = layui.upload;

				upload.render({
					elem: '#test7',
					url: 'upCarousel',
					size: 1024*4,//限制文件大小，单位 KB
					accept:'file',
					done: function(res){
						$("#url").val(res.token);
						console.log(res.token);
					}
				});
			});
			layui.use('laydate', function() {
				var laydate = layui.laydate;

				//常规用法
				laydate.render({
					elem: '#test1'
				});
			});
		</script>
	</body>


</html>