<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html>

	<head>

		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>MC Music carousel修改</title>
		<link rel="stylesheet" href="css/addcarousel.css" />
		<link rel="stylesheet" href="layui/css/layui.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="layui/layui.js"></script>
	</head>

	<body>
		<div class="center">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>修改carousel</legend>
			</fieldset>
			<div class="message">
				<form method="post" method="post" action="updateImageReturn">
					 <input  type="hidden" name="url" id="url" value=""/>
					<input type="hidden" name="imageid" value="${imagePo.imageid}">
					<div class="layui-form-item">
						<label class="layui-form-label">名称</label>
						<div class="layui-input-block">
							<input name="imagename" required value="${imagePo.imagename}" lay-verify="title" autocomplete="off" placeholder="请输入名称" class="layui-input" type="text">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">当前封面</label>
						<div class="layui-upload-list">
								<img class="layui-upload-img" id="imgurl" src="../${imagePo.imageurl}"  style="width:40px;height:40px">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">上传封面</label>
						<div class="layui-upload">
							<button name="file" type="button" class="layui-btn layui-btn-danger" id="test7"><i class="layui-icon"></i>上传图片</button>
							<div class="layui-upload-list">
								<img class="layui-upload-img" >
							</div>
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">链接地址</label>
						<div class="layui-input-block">
							<input name="imagelink" required value="${imagePo.imagelink}" lay-verify="title" autocomplete="off" placeholder="请输入内容地址" class="layui-input" type="text">
						</div>
					</div>

					<button class="layui-btn layui-btn-radius" style="position: absolute;top: 300px;left:200px;">保存</button>
					<button onclick="window.history.go(-1)"  class="layui-btn layui-btn-normal layui-btn-radius" style="position: absolute;top: 300px;left: 320px;">返回</button>
				</form>
			</div>

		</div>
		<script>
			//JavaScript代码区域
			layui.use('element', function() {
				var element = layui.element;

			});
			
			layui.use('input', function() {
				var $= layui.input;
				input=layui.input;
				input.render({
					elem:'#input',					
					url:'getTypeNameByTypeId',		
					done:function(res){
					{imageId:${imagePo.imageid}};
					$("#input").append("<option value='"+res.tyid+"'>"+res.name+"</option>");
					}
				});
			});
			
			layui.use('upload', function() {
				var $ = layui.jquery,
					upload = layui.upload;	
					upload.render({
						elem: '#test7',
						url: 'upCarousel',
						accept: 'file' //普通文件
							,
						done: function(res) {
							$("#url").val(res.token);
							$("#imgurl").attr('src','../'+res.token);		
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
</body>

</html>
