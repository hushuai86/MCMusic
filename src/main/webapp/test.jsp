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
		<title>MC Music CD添加</title>
		<link rel="stylesheet" href="layui/css/layui.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="layui/layui.js"></script>
	</head>

	<body>
		<div class="center">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>CD基本信息</legend>
			</fieldset>
			<div class="message">
				<form method="post" action="addCD" enctype="multipart/form-data">
					<div class="layui-form-item">
						<label class="layui-form-label">专辑名称</label>
						<div class="layui-input-block">
							<input name="cdname" lay-verify="title" autocomplete="off" placeholder="请输入专辑名称" class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">收藏次数</label>
						<div class="layui-input-block">
							<input name="collectioncount" lay-verify="title" autocomplete="off" placeholder="请输入收藏次数" class="layui-input" type="text"
							value="0">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">歌曲数量</label>
						<div class="layui-input-block">
							<input name="songcount" lay-verify="title" autocomplete="off" placeholder="请输入歌曲数量" class="layui-input" type="text">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">发行时间</label>
						<div class="layui-input-inline">
							<input name="publishdate" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" type="text">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">歌手ID</label>
						<div class="layui-input-block">
							<input name="singid" lay-verify="title" autocomplete="off" placeholder="请输入歌手ID" class="layui-input" type="text">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">专辑封面</label>
						<div class="layui-upload">
							<button name="file" type="button" class="layui-btn layui-btn-danger" id="test7"><i class="layui-icon"></i>上传图片</button>
							<div class="layui-upload-list">
								<img class="layui-upload-img" id="demo1">
								<p id="demoText" name="coverUrl"></p>
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">专辑状态</label>
						<div class="layui-input-inline">
							<select name="cdstateid">
        									<option value="">请选择状态</option>
        									<option value="0" selected="">0</option>
        									<option value="1">1</option>
      						</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">专辑简介</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" name="introduce" class="layui-textarea" style="width: 300px;"></textarea>
						</div>
					</div>
					<button  class="layui-btn layui-btn-radius" style="position: absolute;top: 600px;left:160px;">提交</button>
					<button onclick="window.history.go(-1)"  class="layui-btn layui-btn-normal layui-btn-radius" style="position: absolute;top: 600px;left: 280px;">返回</button>
				</form>
			</div>

		</div>
		<script src="../src/layui.js"></script>
		<script>
			//JavaScript代码区域
			layui.use('element', function() {
				var element = layui.element;

			});
			layui.use('upload', function() {
				var $ = layui.jquery,
					upload = layui.upload;

				upload.render({
					elem: '#test7',
					url: 'addCD',
					size: 1024*4 ,//限制文件大小，单位 KB
					field:'file',
					done: function(res) {
						console.log(res)
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