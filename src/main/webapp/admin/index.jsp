<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  	
%>
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
	</head>
	<body class="layui-layout-body">
		<div class="layui-layout layui-layout-admin ">
			<div class="layui-header layui-bg-green">
				<div class="layui-logo" style="color: white;">MC在线音乐后台</div>
				<!-- 头部区域（可配合layui已有的水平导航） -->
				<ul class="layui-nav layui-layout-left ">
					<li class="layui-nav-item"><a href="">首页</a></li>
					<li class="layui-nav-item"><a href="../index.html">前端首页</a></li>
					</li>
				</ul>
				<ul class="layui-nav layui-layout-right">
					<li class="layui-nav-item">
						<a href="javascript:;">
							<img src="http://t.cn/RCzsdCq" class="layui-nav-img"> ${admin.adminname}
						</a>
						<dl class="layui-nav-child">
							<dd><a onclick="tabChange('基本资料','tabAdmin','toAdminUpdate')">基本资料</a></dd>
							<dd><a onclick="tabChange('安全设置','tabRepwd','repwd.jsp')">安全设置</a></dd>
						</dl>
					</li>
					<li class="layui-nav-item"><a href="adminLogout">退了</a></li>
				</ul>
			</div>

			<div class="layui-side layui-bg-black">
				<div class="layui-side-scroll">
					<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
					<ul class="layui-nav layui-nav-tree" lay-filter="test">
						<li class="layui-nav-item">
							<a href="javascript:;">网站管理</a>
							<dl class="layui-nav-child">
								<dd><a href="javascript:;" onclick="tabChange('首页轮播','tabCarousel','getAllCarousel')">首页轮播</a></dd>
								<dd><a href="javascript:;">页面底部</a></dd>
							</dl>
						</li>
						<li class="layui-nav-item"><a onclick="tabChange('歌曲管理','tabSong','getSongPo')">歌曲管理</a></li>
						<li class="layui-nav-item"><a onclick="tabChange('专辑管理','tabCD','adminListCD')">专辑管理</a></li>
						<li class="layui-nav-item"><a onclick="tabChange('歌单管理','tabSongList','getAllSongList')">歌单管理</a></li>
						<li class="layui-nav-item"><a onclick="tabChange('歌手管理','tabSinger','getSingerPo')">歌手管理</a></li>
						<li class="layui-nav-item"><a onclick="tabChange('用户管理','tabUser','adminListUser')">用户管理</a></li>
					</ul>
				</div>
			</div>

			<div class="layui-body">
				<!-- 内容主体区域 -->
				<div style="padding: 15px;">
					<div class="layui-tab" lay-filter="demo" lay-allowclose="true">
						<ul class="layui-tab-title">
							<li class="layui-this" lay-id="tabIndex">首页</li>
						</ul>
						<div class="layui-tab-content">
							<div class="layui-tab-item layui-show">
								<iframe scrolling="no" frameborder="0" src="adminIndex.jsp" style="height:930px;" width="1300px"></iframe>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="layui-footer">
				<!-- 底部固定区域 -->
				© layui.com - 底部固定区域
			</div>
		</div>
      <script src="layui/layui.js"></script>
		<script>
			//JavaScript代码区域
			layui.use(['element','layer'], function() {
				var $ = layui.jquery,
				element = layui.element;
			    layer = layui.layer;
        	  
			tabChange = function(title, id, src) {
				layui.use('element', function() {
					if (!$('li[lay-id=' + id + ']').is('li')) {
						//删除
						element.tabDelete('demo', id);
						//添加
						element.tabAdd('demo', {
							title: title,
							content: '<iframe scrolling="no" frameborder="0" src="' + src + '" style="height: 1000px;" width="1300px;"></iframe>' //支持传入html
								,
							id: id
						});
					}
					//切换
					element.tabChange('demo', id);
				});
			}
			});
		</script>
	</body>

</html>