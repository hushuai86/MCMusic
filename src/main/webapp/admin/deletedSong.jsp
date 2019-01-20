<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>MC Music 已删除歌曲管理</title>
<link rel="stylesheet" href="css/base.css" />
<link rel="stylesheet" href="layui/css/layui.css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>
</head>

<body>
	<fieldset class="layui-elem-field layui-field-title">
		<legend>基本操作</legend>
	</fieldset>
	<a href="getSongPo"><button class="layui-btn">返回歌曲管理</button></a>
	<button id="bt" class="layui-btn" onclick="recoverPageSong()" >恢复本页数据</button>
	<input id="pageIndex" type="hidden"/>
	
	<fieldset class="layui-elem-field layui-field-title">
		<legend>基本查询</legend>
	</fieldset>
	<form class="layui-form" action="toAdminRecoverSong" method="post">
		<div style="width: 800px;float: left;margin-right: 5px;">
			<input type="text"  id="search" value="${search}" name="search"  lay-verify="required"
				placeholder="请输入歌曲名称" autocomplete="off" class="layui-input">
		</div>
		<button type="submit" class="layui-btn layui-btn-primary">搜索</button>
	</form>	
	<br />
	<br />
	<br />
	<fieldset class="layui-elem-field layui-field-title">
		<legend>歌曲列表</legend>
	</fieldset>
	<div class="center">
		<table>
			<thead>
				<tr>
					<th>歌曲名称</th>
					<th>歌手名称</th>
					<th>专辑名称</th>
					<th>语种</th>
					<th>播放次数</th>
					<th>下载次数</th>
					<th>收藏次数</th>
					<th>发行时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="tb">
			 <c:forEach items="${pageBasePo.list }" var="song">
		         <tr>
					<td>${song.songPo.songname }</td>
					<td>${song.singPo.singername }</td>
					<td>${song.cdPo.cdname }</td>
					<td>${song.songPo.language }</td>
					<td>${song.songPo.playcount }</td>
					<td>${song.songPo.downloadcount }</td>
					<td>${song.songPo.collectioncount }</td>
					<td><fmt:formatDate value="${song.songPo.publishdate }" type="date"/></td>	
					<td>
						<button onclick="confirmTrans(${song.songPo.songid})"
							class="layui-btn layui-btn-small">
							<i class="layui-icon">&#xe65c;</i>
						</button> </td>
				 </tr>
		      </c:forEach>
			</tbody>
		</table>
		<div id="ul">
			
		</div>
		
	</div>

	<script>	
	   
	    
		//JavaScript代码区域
		layui.use([ 'element', 'layer', 'laypage' ], function() {
			var element = layui.element;
			var $ = layui.jquery, layer = layui.layer;
			var laypage = layui.laypage, layer = layui.layer;

			 

			$('.layui-btn').on('click', function() {
				var othis = $(this), method = othis.data('method');
				active[method] ? active[method].call(this, othis) : '';
			});
			
			//按钮触发事件
			confirmTrans = function(id) {
				//配置一个透明的询问框
				   parent.layer.msg('确定要恢复吗？', {
					time: 20000, //20s后自动关闭
					btn: ['确定', '返回'],
					yes: function(){
					     location.href="adminRecoverSong?songId="+id;
					     parent.layer.closeAll('dialog');
			        }
				});
			};
		 //恢复整页数据
			 recoverPageSong=function(){
				  var search=$("#search").val();
				  var pageIndex=$("#pageIndex").val();
				  
				  parent.layer.msg('确定要恢复整页吗？', {
						time: 20000, //20s后自动关闭
						btn: ['确定', '返回'],
						yes: function(){
							 $.post(
								    	"recoverPageSong",
								    	{search:search,pageIndex:pageIndex},
								    	function(data){
								    		location.href=data;
								    	}
								    	
								      );
							 parent.layer.closeAll('dialog');

				        }
					});
			      }
			
			//日期格式化
		    Date.prototype.toLocaleString = function() {
            return this.getFullYear() + "年" + (this.getMonth() + 1) + "月" + this.getDate() + "日 ";
            };
			//分页
            var pageCount=5*${pageBasePo.pageCount};
            
			laypage.render({
				elem: 'ul',
				limit:5,
				count: pageCount,
				first: '首页',
				last: '尾页',
				prev: '<em>←</em>',
				next: '<em>→</em>',
				jump: function(obj, first){//页面跳转执行函数	
					    var search=$("#search").val();
					    $("#pageIndex").val(obj.curr);
					    var pageIndex =obj.curr;//当前页码  
					    if(!first){ //首次不执行
					    	 $.get(//ajax刷新数据
					    			 "adminRecoverCDPage",
					    			 {search:search,pageIndex:pageIndex},
					    			  function(data){			
					    				 $(".tb").html("");//清空子节点
					    				 data.list.forEach(function(value,index){//数据渲染
					    					/*  console.log(value); */
					    					 var date= new Date(value.songPo.publishdate);
					    			 
					    					 date=date.toLocaleString();	
					    					 var $tr=$('<tr><td>'+value.songPo.songname+'</td>'
					    					            +'<td>'+value.singPo.singername+'</td>'
					    					            +'<td>'+value.cdPo.cdname+'</td>'
					    								+'<td>'+value.songPo.language+'</td>'
					    								+'<td>'+value.songPo.playcount+'</td>'
					    								+'<td>'+value.songPo.downloadcount+'</td>'
					    								+'<td>'+value.songPo.collectioncount+'</td>'
					    								+'<td>'+date+'</td>'
					    								+'<td>'
					    								+'<button onclick="confirmTrans('+value.songPo.songid+')" class="layui-btn layui-btn-small"><i class="layui-icon">&#xe65c;</i><button></td></tr>');
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
