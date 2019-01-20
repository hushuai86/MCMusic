<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>歌单添加</title>
    

		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>MC Music songlist添加</title>
		<!-- <link rel="stylesheet" href="css/addsonglist.css" /> -->
		<link rel="stylesheet" href="layui/css/layui.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="layui/layui.js"></script>
		
  </head>
  
  <body>
    
		<div class="center">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>添加songlist基本信息</legend>
			</fieldset>
			<div class="message">
				<form method="post" action="admin/addSongList" enctype="multipart/form-data">
				
				 <input  type="hidden" name="url" id="url" value=""/>
				 
					<div class="layui-form-item">
						<label class="layui-form-label">歌单名称</label>
						<div class="layui-input-block">
							<input name="songlistname" lay-verify="title" required autocomplete="off" placeholder="请输入歌单名称" class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">用户编号</label>
						<div class="layui-input-block">
							<input name="userid" lay-verify="title" required autocomplete="off" placeholder="请输入用户id" class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">类型编号</label>
						<div class="layui-input-inline" style="margin-top: 10px;">
							<select name="typeid">
									<option value="1">重金属</option>
     								<option value="2">伤感</option>
     								<option value="3">欢快</option>
     								<option value="4">摇滚</option>
     								<option value="5">嘻哈</option>
     								<option value="6">翻唱</option>
     								<option value="7">民谣</option>
     								<option value="8">串烧</option>
     								<option value="9">苦情歌</option>
     								<option value="10">童谣</option>
     								<option value="11">爵士</option>
     								<option value="12">钢琴</option>
     								<option value="13">网络歌曲</option>
     								<option value="14">兴奋</option>
     								<option value="15">纯音乐</option>
     								<option value="16">KTV</option>
     								<option value="17">嗨歌</option>
     								<option value="18">轻音乐</option>
							</select>
						</div>
					</div>
					
				<div class="layui-form-item">
						<label class="layui-form-label">歌单封面</label>
						<div class="layui-upload">
							<button name="file" type="button" class="layui-btn layui-btn-danger" id="test7"><i class="layui-icon"></i>上传图片</button>
							<div class="layui-upload-list">
								<img class="layui-upload-img" id="demo1">
								<p id="demoText" name="imgurl"></p>
							</div>
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">歌单标签</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" required name="tags" class="layui-textarea" style="width: 300px;"></textarea>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">访问次数</label>
						<div class="layui-input-block">
							<input name="accesscount" lay-verify="title" value="0" readonly autocomplete="off" placeholder="请输入访问次数" class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">收藏次数</label>
						<div class="layui-input-block">
							<input name="collectioncount" lay-verify="title" value="0" readonly autocomplete="off" placeholder="请输入收藏次数" class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">歌单状态</label>
						<div class="layui-input-inline" style="margin-top: 10px;">
							<select name="songliststateid">
        									<option value="">请选择歌单状态</option>
        									<option value="0" selected="">0</option>
        									<option value="1">1</option>
      									</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">歌单简介</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" required name="introduce" class="layui-textarea" style="width: 300px;"></textarea>
						</div>
					</div>

					<button class="layui-btn layui-btn-radius" style="position: absolute;top: 670px;left:160px;">提交</button>
					<button onclick="window.history.go(-1)"  class="layui-btn layui-btn-normal layui-btn-radius" style="position: absolute;top: 670px;left: 280px;">返回</button>
				</form>
			</div>

		</div>
		<script>
			//JavaScript代码区域
			
			
			layui.use('element', function() {
				var element = layui.element;

			});
			
			//var url = $("#url").val();
			layui.use('upload', function() {
				var $ = layui.jquery,
					upload = layui.upload;

				upload.render({
					elem: '#test7',
					url: 'admin/upSongListFile',
					size: 1024*4 ,//限制文件大小，单位 KB
					field:'file',
					done: function(res){
						$("#url").val(res.token);
						$("#img").attr('src','../'+res.token); 
					
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
