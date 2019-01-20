<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>MC Music 歌手管理</title>
		<link rel="stylesheet" href="css/base.css" />
		<link rel="stylesheet" href="layui/css/layui.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="layui/layui.js"></script>
	</head>

	<body>
 		<fieldset class="layui-elem-field layui-field-title">
			<legend>基本添加</legend>
		</fieldset>
		<a href="getSingerPo"><button class="layui-btn">歌手列表</button></a>
		<fieldset class="layui-elem-field layui-field-title">
			<legend>基本查询</legend>
		</fieldset>
		<form class="layui-form" action="" method="post">
			<div style="width: 800px;float: left;margin-right: 5px;">
				<input type="text" name="singerName" id="singerName" lay-verify="required" value="${singerName}" placeholder="请输入歌手名称" autocomplete="off" class="layui-input">
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
					<tr>
						<th>歌手图片</th>
						<th>歌手名称</th>
						<th>歌手性别</th>
						<th>歌手热度</th>
						<th>出道时间</th>
						<th>收藏次数</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody class="tb">
			 
					<c:forEach items="${pageBasePo.list }" var="singer">
					<tr>
						<td><img src="../${singer.photourl}" /></td>
						<td>${singer.singername }</td>
						<td>${singer.singersex==true?"男":"女" }</td>
						<td>${singer.accesscount }</td>
						<td><fmt:formatDate value="${singer.debutdate }" type="date"/></td>
						<td>${singer.collectioncount }</td>
						<td>
							<button onclick="confirmTrans(${singer.singerid })" class="layui-btn layui-btn-small">
							<i class="layui-icon">&#xe65c;</i></button></a>
							</td>
					</tr>
					</tr>
				    </c:forEach>
				</tbody>
			</table>
			<div id="ul"></div>
		</div>

		<script src="layui/layui.js"></script>
		<script>
			//JavaScript代码区域
			layui.use(['element', 'layer', 'laypage'], function() {
				var element = layui.element;

				var $ = layui.jquery,
					layer = layui.layer;

				var laypage = layui.laypage,
					layer = layui.layer;

				//触发事件
				 
					confirmTrans = function(id) {
						//配置一个透明的询问框
						parent.layer.msg('确定要恢复该歌手吗？', {
							time: 20000, //20s后自动关闭
							btn: ['确定', '返回'],
							yes: function(){
							     location.href="restoreSingerState?singerId="+id;
								 parent.layer.closeAll('dialog');
					        }
						});
					};
				 

			 
				
				
				laypage.render({
					elem: 'ul',
					count: 100,
					first: '首页',
					last: '尾页',
					prev: '<em>←</em>',
					next: '<em>→</em>'
				});
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
						    var singerName=$("#singerName").val();
					 		
						    var pageIndex =obj.curr;//当前页码
						    
						    if(!first){ //首次不执行
						    	 $.get(//ajax刷新数据
						    			 "banSingerByPag",
						    			 {singerName:singerName,pageIndex:pageIndex},
						    			  function(data){
						    				 $(".tb").html("");//清空子节点
						    				 data.list.forEach(function(value,index){//数据渲染
						    					/*  console.log(value); */
						    					 var date= new Date(value.debutdate);
						    					 var sex = value.singersex==true?"男":"女";
						    					 
						    					 date=date.toLocaleString();	
						    					 var $tr=$('<tr><td><img src="../'+value.photourl+'"/></td>'
						    					            +'<td>'+value.singername+'</td>'
						    					            +'<td>'+sex+'</td>'
						    								+'<td>'+value.accesscount+'</td>'
						    								+'<td>'+date+'</td>'
						    								+'<td>'+value.collectioncount+'</td>'
						    								+'<td><button onclick="confirmTrans('+value.singerid+')" class="layui-btn layui-btn-small"><i class="layui-icon">&#xe65c;</i><button></a></td></tr>');
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
