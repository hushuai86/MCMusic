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
<title>MC Music 专辑歌曲管理</title>
<link rel="stylesheet" href="css/base.css" />
<link rel="stylesheet" href="layui/css/layui.css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>
</head>

<body>
	<fieldset class="layui-elem-field layui-field-title">
		<legend>专辑名：${cdPo.cdname}</legend>
	</fieldset>
	<a href="toAdminCDAddSong?cdId=${cdPo.cdid}"><button class="layui-btn">添加歌曲</button></a>
	<a href="adminListCD"><button class="layui-btn">返回专辑管理</button></a>

	<fieldset class="layui-elem-field layui-field-title">
		<legend>基本查询</legend>
	</fieldset>
	<form class="layui-form" action="toAdminUpdateCDSong" method="post">
		<div style="width: 800px;float: left;margin-right: 5px;">
		     <input type="hidden" name="cdId" value="${cdPo.cdid}" >
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
					<th>歌手名</th>
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
					<td>${songVo.singPo.singername}</td>
					<td>${songVo.songPo.language}</td>
					<td>${songVo.songPo.playcount}</td>
					<td>${songVo.songPo.downloadcount}</td>
					<td>${songVo.songPo.collectioncount}</td>
					<td><fmt:formatDate value="${songVo.songPo.publishdate }" type="date"/></td>
					<td>
						<button onclick="confirmTrans(${songVo.songPo.songid})"
							class="layui-btn layui-btn-small">
							<i class="layui-icon"></i>
						</button> </td>
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

			//触发事件
			 
			confirmTrans = function(id) {
				//配置一个透明的询问框
				parent.layer.msg('确定要从专辑中删除吗？', {
					time: 20000, //20s后自动关闭
					btn: ['确定', '返回'],
					yes: function(){
					     location.href="deleteCDSong?songId="+id+"&cdId="+${cdPo.cdid};
					     parent.layer.closeAll('dialog');
			        }
				});
			};
		 
			
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
					    			 
					    					 date=date.toLocaleString();	
					    					 var $tr=$('<tr><td>'+value.songPo.songname+'</td>'
					    					            +'<td>'+value.singPo.singername+'</td>'		  
					    								+'<td>'+value.songPo.language+'</td>'
					    								+'<td>'+value.songPo.playcount+'</td>'
					    								+'<td>'+value.songPo.downloadcount+'</td>'
					    								+'<td>'+value.songPo.collectioncount+'</td>'
					    								+'<td>'+date+'</td>'
					    								+'<td>'
					    								+'<button onclick="confirmTrans('+value.songPo.songid+')" class="layui-btn layui-btn-small"><i class="layui-icon"></i><button></td></tr>');
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
