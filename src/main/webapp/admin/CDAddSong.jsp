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
<title>MC Music 专辑歌曲添加</title>
<link rel="stylesheet" href="css/base.css" />
<link rel="stylesheet" href="layui/css/layui.css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>
</head>

<body>
	<fieldset class="layui-elem-field layui-field-title">
		<legend>歌手名：${pageBasePo.list[1].singPo.singername}</legend>
	</fieldset>
	<a  href="toAdminUpdateCDSong?cdId=${cdPo.cdid}"><button class="layui-btn">返回专辑</button></a>
	<fieldset class="layui-elem-field layui-field-title">
		<legend>基本查询</legend>
	</fieldset>
	<form class="layui-form" action="toAdminUpdateCDSong" method="post">
	    <input type="hidden" name="cdId" value="${cdPo.cdid}" >
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
					<th>语种</th>
					<th>播放次数</th>
					<th>下载次数</th>
					<th>收藏次数</th>
					<th>发行时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="tb">
			 <c:forEach items="${pageBasePo.list}" var="songVo">
		         <tr>
					<td>${songVo.songPo.songname}</td>	
					<td>${songVo.songPo.language}</td>
					<td>${songVo.songPo.playcount}</td>
					<td>${songVo.songPo.downloadcount}</td>
					<td>${songVo.songPo.collectioncount}</td>
					<td><fmt:formatDate value="${songVo.songPo.publishdate }" type="date"/></td>
					<td>
					<a href="adminCDAddSong?songId=${songVo.songPo.songid}&cdId=${cdPo.cdid}">
						<button 
							class="layui-btn layui-btn-small">
							<i class="layui-icon">&#xe654;</i>
						</button> 
					</a>
					</td>
				</tr>
		      </c:forEach>
			</tbody>
		</table>
		<div id="ul" style="margin-left: 600px">
			
		</div>
		
	</div>

	<script src="layui/layui.js"></script>
	<script>
	
	
	
		//JavaScript代码区域
		layui.use([ 'element', 'layer', 'laypage' ], function() {
			var element = layui.element;

			var $ = layui.jquery, layer = layui.layer;

			var laypage = layui.laypage, layer = layui.layer;

			 

			
			
			//日期格式化
		    Date.prototype.toLocaleString = function() {
            return this.getFullYear() + "年" + (this.getMonth() + 1) + "月" + this.getDate() + "日 ";
            };
			//分页
            var pageCount=5*${pageBasePo.pageCount};
            if(pageCount==0){
   			 $("#ul").css("display","none");	 
   		 }
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
					    var pageIndex =obj.curr;//当前页码
					    var cdId = ${cdPo.cdid};
					    if(!first){ //首次不执行
					    	 $.post(//ajax刷新数据
					    			 "adminPageCDSong",
					    			 {pageIndex:pageIndex,search:search,cdId:cdId},
					    			  function(data){
					    				 $(".tb").html("");//清空子节点
					    				 data.list.forEach(function(value,index){//数据渲染
					    					/*  console.log(value); */
					    					 var date= new Date(value.songPo.publishdate);
					    			         var cdId = $("#cdId").val();
					    					 date=date.toLocaleString();	
					    					 var $tr=$('<tr><td>'+value.songPo.songname+'</td>'	  
					    								+'<td>'+value.songPo.language+'</td>'
					    								+'<td>'+value.songPo.playcount+'</td>'
					    								+'<td>'+value.songPo.downloadcount+'</td>'
					    								+'<td>'+value.songPo.collectioncount+'</td>'
					    								+'<td>'+date+'</td>'
					    								+'<td><a href="adminCDAddSong?songerId='+value.songPo.songid+'&cdid='+cdId+'">'
					    								+'<button onclick="confirmTrans('+value.songPo.songid+')" class="layui-btn layui-btn-small"><i class="layui-icon">&#xe654;</i><button></a></td></tr>');
					    					 $(".tb").append($tr);
											});
					    			 });
					    }
					  }
			});

		});
	</script>
</body>
</html>
