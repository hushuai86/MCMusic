<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>

		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>MC Music 歌单管理</title>
		<link rel="stylesheet" href="css/base.css" />
		<link rel="stylesheet" href="layui/css/layui.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script src="layui/layui.js"></script>
	</head>

	<body>
		<fieldset class="layui-elem-field layui-field-title">
			<legend>基本添加</legend>
		</fieldset>
		<a href="addsonglist.jsp"><button class="layui-btn">添加歌单</button></a>

		<fieldset class="layui-elem-field layui-field-title">
			<legend>基本查询</legend>
		</fieldset>
		<form class="layui-form" action="getAllSongList" method="post">
			<div style="width: 800px;float: left;margin-right: 5px;">
				<input type="text" id="search" name="search"  value="${search}" placeholder="请输入专辑名称" autocomplete="off" class="layui-input">
			</div>
			<button type="submit" class="layui-btn layui-btn-primary">搜索</button>
		</form>
		<br /><br /><br />
		<fieldset class="layui-elem-field layui-field-title">
			<legend>专辑列表</legend>
		</fieldset>
		<div class="center">
			<table>
				<thead>
					<tr >
						<th width="120px">歌单封面</th>
						<th width="100px">歌单名称</th>
						<th width="100px">歌单热度</th>
						<th width="100px">收藏次数</th>
						<th width="100px">标签</th>
						<th width="100px">操作</th>
					</tr>
				</thead>
				<tbody class="tb">
				<c:forEach items="${pageBasePo.list}" var="songListVo">
					<tr>
						<td><img src="../${songListVo.songListPo.imgurl}"/></td>
						<td>${songListVo.songListPo.songlistname}</td>
						<td>${songListVo.songListPo.accesscount}</td>
						<td>${songListVo.songListPo.collectioncount}</td>
						<td>${songListVo.songListPo.tags}</td>
						<td>
							<a href="getSongListById?songListId=${songListVo.songListPo.songlistid}"><button class="layui-btn layui-btn-small"><i class="layui-icon"></i></button></a>
							<button onclick="confirmTrans(${songListVo.songListPo.songlistid})" class="layui-btn layui-btn-small"><i class="layui-icon"></i></button></td>
					</tr>
				</c:forEach>	
				</tbody>
			</table>
			<div id="ul"></div>

		</div>
		
		<script>
			//JavaScript代码区域
			layui.use(['element', 'layer', 'laypage'], function() {
				var element = layui.element;

				var $ = layui.jquery,
					layer = layui.layer;

				var laypage = layui.laypage,
					layer = layui.layer;

				//删除按钮触发事件
					confirmTrans = function(songListId) {
					//配置一个透明的询问框
					parent.layer.msg('确定要删除吗？', {
						btn : [ '确定', '返回' ],
					    yes: function(){
					     location.href="deleteSongListPo?songListId="+songListId;
						 parent.layer.closeAll('dialog');
				        }
					});
				}

				$('.layui-btn').on('click', function() {
					var othis = $(this),
						method = othis.data('method');
					active[method] ? active[method].call(this, othis) : '';
				});
				//分页
                var pageCount=5*${pageBasePo.pageCount};
                
				laypage.render({
					elem: 'ul',
					count:pageCount ,
					limit:5,
					first: '首页',
					last: '尾页',
					prev: '<em>←</em>',
					next: '<em>→</em>',
					jump: function(obj, first){//页面跳转执行函数	
						    var search=$("#search").val();
						    var pageIndex =obj.curr;//当前页码
					
						    if(!first){ //首次不执行
						    	 $.get(//ajax刷新数据
						    			 "getPageSongList",
						    			 {search:search,pageIndex:pageIndex},
						    			  function(data){
						    				 $(".tb").html("");//清空子节点
						    				 data.list.forEach(function(value,index){//数据渲染
						                        console.log(value);
						    					 var $tr=$('<tr><td><img src="../'+value.songListPo.imgurl+'"/></td>'
						    					            +'<td>'+value.songListPo.songlistname+'</td>'
						    					            +'<td>'+value.songListPo.accesscount+'</td>'
						    								+'<td>'+value.songListPo.collectioncount+'</td>'
						    								+'<td>'+value.songListPo.tags+'</td>'
						    								+'<td><a href="getSongListById?songListId='+value.songListPo.songlistid+'"><button class="layui-btn layui-btn-small"><i class="layui-icon"></i><button></a>'
						    								+'<button onclick="confirmTrans('+value.songListPo.songlistid+')"  class="layui-btn layui-btn-small"><i class="layui-icon"></i><button></td></tr>');
						    					 $(".tb").append($tr);
												});
						    			 }
						 
						       		 );
						    	}
						  }
					
					
				}); 

			});
		</script>
	</body>

</html>
