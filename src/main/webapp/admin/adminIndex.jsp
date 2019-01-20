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
		<title>MC Music管理系统</title>
		<link rel="stylesheet" href="css/base.css" />
		<link rel="stylesheet" href="layui/css/layui.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="layui/layui.js"></script>
		<script type="text/javascript">
			$(function(){
				$.post(
					"adminIndex",
					function(info){
						$("#songCount").html(info.songCount);
						$("#singerCount").html(info.singerCount);
						$("#songListCount").html(info.songListCount);
						$("#cdCount").html(info.cdCount);
						$("#userCount").html(info.userCount);
					}
					);
				$(".center div img").hover(function() {
					$(this).siblings().css("display", "inline");
				}, function() {
					$(this).siblings().css("display", "none");
				});

				$(".top div img").hover(function() {

					$(this).siblings().css("display", "inline");
				}, function() {
					$(this).siblings().css("display", "none");
				});

			});
		</script>
	</head>

	<body>
		<fieldset class="layui-elem-field layui-field-title">
		  <legend>基本添加</legend>
		</fieldset>
		<a href="addsong.jsp"><button class="layui-btn" >添加歌曲</button></a>
		<a href="addsinger.jsp"><button class="layui-btn">添加歌手</button></a>
		<a href="addCD.jsp"><button class="layui-btn" >添加专辑</button></a>
		<a href="addsonglist.jsp"><button class="layui-btn">添加歌单</button></a>
		<a href="addUser.jsp"><button class="layui-btn" >添加用户</button></a>
		<br /><br /><br />
		<a href="getSongPo"><div style="height: 200px; width:250px;border: solid #BBB 1px;text-align: center;color: #009688;
			font-size: 20px;float: left;margin-right: 20px;">
			<br /><br />
		<i class="layui-icon" style="font-size: 80px; color: #009688;">&#xe652;</i><br />
			歌曲<span id="songCount"></span>
		</div></a>
		<a href="adminListCD"><div style="height: 200px; width:250px;border: solid #BBB 1px;text-align: center;color: #009688;
			font-size: 20px;float: left;margin-right: 20px;">
			<br /><br />
			<i class="layui-icon" style="font-size: 80px; color: #009688;">&#xe643;</i><br />
			专辑<span id="cdCount"></span>
		</div></a>
		<a href="getSingerPo"><div style="height: 200px; width:250px;border: solid #BBB 1px;text-align: center;color: #009688;
			font-size: 20px;float: left;margin-right: 20px;">
			<br /><br />
			<i class="layui-icon" style="font-size: 80px; color: #009688;">&#xe613;</i><br />
			歌手<span id="singerCount"></span>
		</div></a>
		<a href="getAllSongList"><div style="height: 200px; width:250px;border: solid #BBB 1px;text-align: center;color: #009688;
			font-size: 20px;float: left;margin-right: 20px;">
			<br /><br />
			<i class="layui-icon" style="font-size: 80px; color: #009688;">&#xe6fc;</i><br />
			歌单<span id="songListCount"></span>
		</div></a>
		<a href="adminListUser"><div style="height: 200px; width:250px;border: solid #BBB 1px;text-align: center;color: #009688;
			font-size: 20px;float: left;margin-right: 20px;margin-top: 20px;">
			<br /><br />
			<i class="layui-icon" style="font-size: 80px; color: #009688;">&#xe612;</i><br/>
			用户<span id="userCount"></span>
		</div></a>
		
	</body>

</html>