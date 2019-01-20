<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>MC Music song添加</title>
<link rel="stylesheet" href="layui/css/layui.css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>
<style type="text/css">
.hover {
	background-color: #ADD8E6;
	cursor: pointer;
}

#singername_list {
	position: absolute;
	z-index: 1;
}

#singername_list table {
	width: 190px;
	background-color: #00C5CD;
}

#cdname_list
 {
	position: absolute;
	z-index: 1;
}

#cdname_list table {
	width: 190px;
	background-color: #00C5CD;
}
</style>
</head>

<body>

	<div class="center">
		<fieldset class="layui-elem-field layui-field-title">
			<legend>歌曲基本信息</legend>
		</fieldset>
		<div class="message">
			<form method="post" action="addSong">
				<input type="hidden" name="url1" id="url1" /> <input type="hidden"
					name="url2" id="url2" />
				<div class="layui-form-item">
					<label class="layui-form-label">歌曲名称</label>
					<div class="layui-input-block">
						<input name="songname" lay-verify="title" autocomplete="off"
							placeholder="请输入歌曲名称" required class="layui-input" type="text"
							value="${songPo.songname }">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">所属歌手</label>
					<div class="layui-input-inline" style="margin-top: 10px;">
						<input name="singername" id="singername" lay-verify="title"
							autocomplete="off" placeholder="请输入歌手名称" required
							class="layui-input" type="text" onkeyup="select1()">
							
						<div id="singername_list"></div>
						
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">所属专辑</label>
					<div class="layui-input-inline" style="margin-top: 10px;">
						<input id="cdname" name="cdname" lay-verify="title" autocomplete="off"
							placeholder="请输入专辑名称" class="layui-input" type="text" onkeyup="select2()">
							<div id="cdname_list"></div>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">歌曲语种</label>
					<div class="layui-input-inline" style="margin-top: 10px;">
						<select name="language">
							<option value="华语" selected="">华语</option>
							<option value="国语" selected="">国语</option>
							<option value="日语" selected="">日语</option>
							<option value="粤语" selected="">粤语</option>
							<option value="英语" selected="">英语</option>

						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">播放次数</label>
					<div class="layui-input-block">
						<input name="playcount" lay-verify="title" readonly value="0"
							autocomplete="off" placeholder="请输入播放次数" class="layui-input"
							type="text">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">下载次数</label>
					<div class="layui-input-block">
						<input name="downloadcount" lay-verify="title" readonly value="0"
							autocomplete="off" placeholder="请输入下载次数" class="layui-input"
							type="text">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">收藏次数</label>
					<div class="layui-input-block">
						<input name="collectioncount" lay-verify="title" readonly
							value="0" autocomplete="off" placeholder="请输入收藏次数"
							class="layui-input" type="text">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">发行时间</label>
					<div class="layui-input-inline">
						<input name="date" id="test1" lay-verify="date"
							placeholder="yyyy-MM-dd" required autocomplete="off"
							class="layui-input" type="text" >
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">歌曲地址</label>
					<div class="layui-input-inline">
						<button name="songurl" type="button" class="layui-btn" id="test3">
							<i class="layui-icon"></i>上传文件
						</button>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">歌词地址</label>
					<div class="layui-input-inline">
						<button name="cyricurl" type="button" class="layui-btn" id="test4">
							<i class="layui-icon"></i>上传文件
						</button>
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">歌曲时长</label>
					<div class="layui-input-block">
						<input name="songtime"  lay-verify="title" autocomplete="off"
							placeholder="请输入歌曲时长" required class="layui-input" type="text">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">歌曲类型</label>
					<div class="layui-input-inline" style="margin-top: 10px;">
						<select name="typeid">
							<option value="1" selected="">重金属</option>
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
					<label class="layui-form-label">歌曲状态</label>
					<div class="layui-input-inline" style="margin-top: 10px;">
						<select name="songstateid">
							<option value="0" selected="">0</option>
							<option value="1">1</option>
						</select>
					</div>
				</div>
				<button class="layui-btn layui-btn-radius"
					style="position: absolute;top: 800px;left:160px;">提交</button>
				<button onclick="window.history.go(-1)"
					class="layui-btn layui-btn-normal layui-btn-radius"
					style="position: absolute;top: 800px;left: 280px;">返回</button>
			</form>
		</div>

	</div>
	<script src="layui/layui.js"></script>
	<script>
		//歌名  动态模糊查询 动态显示 
		function select1() {
			$('#singername_list').empty();
			
			var singername = $("#singername").val();
				$.post("selectSingerNameBySearch",//url
				{
					singername : singername
				},//参数
				function(data) {
					$('#singername_list').empty();
					if (data.length == 0) {
						parent.layer.msg("歌手名不存在");
							$('#singername').val("");   
						return;
					} else {
						var layer;
						layer = "<table>"; //创建一个table
						var len = data.length;
						if (len > 6) {
							len = 6;
						}
						for ( var i = 0; i < len; i++) {
							layer += "<tr><td class='line'>"
									+ data[i].singername + "</td></tr>";
						}
						layer += "</table>";

						$('#singername').empty(); //先清空#singername下的所有子元素
						$('#singername_list').append(layer);//将刚才创建的table插入到#singername内

						$('.line').hover(function() { //监听提示框的鼠标悬停事件
							$(this).addClass("hover");
						}, function() {
							$(this).removeClass("hover");
						});
						$('.line').click(function() { //监听提示框的鼠标单击事件
							$('#singername').val($(this).text());
							$('#singername_list').empty();
						});
					}
				});
		};
 
		function select2() {
			$('#cdname_list').empty();
			var singername = $("#singername").val();
			var cdname = $("#cdname").val();
			if(singername==""){
				parent.layer.msg("请先填写歌手名");
				$('#cdname').val("");
			}
				$.post("selectCdNameBySearch",//url
				{
					cdname : cdname,singername:singername
				},//参数
				function(data) {
					$('#cdname_list').empty();
					if (data.length == 0) {
						parent.layer.msg("该歌手没有该专辑");
							$('#cdname').val("");
	                        
						return;
					} else {
						var layer;
						layer = "<table>"; //创建一个table
						var len = data.length;
						if (len > 6) {
							len = 6;
						}
						for ( var i = 0; i < len; i++) {
							layer += "<tr><td class='line'>"
									+ data[i].cdname + "</td></tr>";
						}
						layer += "</table>";

						$('#cdname').empty(); //先清空#singername下的所有子元素
						$('#cdname_list').append(layer);//将刚才创建的table插入到#singername内

						$('.line').hover(function() { //监听提示框的鼠标悬停事件
							$(this).addClass("hover");
						}, function() {
							$(this).removeClass("hover");
						});
						$('.line').click(function() { //监听提示框的鼠标单击事件
							$('#cdname').val($(this).text());
							$('#cdname_list').empty();
						});
					}
				});
		};
		
		//JavaScript代码区域
		layui.use('upload', function() {
			var $ = layui.jquery, upload = layui.upload;

			//指定允许上传的文件类型
			var url = $("#url1").val();
			upload.render({
				elem : '#test3',
				url : 'upSong',
				accept : 'file', //普通文件
				done : function(res) {
					$("#url1").val(res.token);
					console.log(res)
				}
			});

			//指定允许上传的文件类型
			url = $("#url2").val();
			upload.render({
				elem : '#test4',
				url : 'upLyrics',
				accept : 'file', //普通文件
				done : function(res) {
					$("#url2").val(res.token);
				}
			});
		});
		layui.use('laydate', function() {
			var laydate = layui.laydate;

			//常规用法
			laydate.render({
				elem : '#test1'
			});
		});
	</script>
</body>
</html>
