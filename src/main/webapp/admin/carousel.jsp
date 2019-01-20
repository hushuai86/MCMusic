<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

	<head>

		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>MC Music carousel管理</title>
		<link rel="stylesheet" href="css/base.css" />
		<link rel="stylesheet" href="layui/css/layui.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="layui/layui.js"></script>
	</head>

	<body>
		<fieldset class="layui-elem-field layui-field-title">
			<legend>基本添加</legend>
		</fieldset>
		<a href="addcarousel.jsp"><button class="layui-btn">添加轮播图片</button></a>
		<br /><br /><br />
		<form class="layui-form" action="getAllCarousel" method="post">
			<div style="width: 800px;float: left;margin-right: 5px;">
				<input type="text" id="search" name="search"  value="${search}" required lay-verify="required" placeholder="请输入专辑名称" autocomplete="off" class="layui-input">
			</div>
			<button type="submit" class="layui-btn layui-btn-primary">搜索</button>
		</form>
		<br /><br /><br />
		
		<fieldset class="layui-elem-field layui-field-title">
			<legend>滚动图片列表</legend>
		</fieldset>
		
		<div class="center">

			<table>
				<thead>
					<tr>
						<th>图片</th>
						<th>名称</th>
						<th>链接地址</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody class="tb">
					<c:forEach items="${ pageBasePo.list}" var="imagePo">
					
					<tr>
						<td>
							<font class="lookImg" style="cursor:pointer;"><img src="img/picture.png" height="40"></font>
							<div style="display: none;position:absolute;z-index: 9999;"><img src="../${imagePo.imageurl}" style="height: 300px;
							width: 1200px;"/></div>
						</td>
						<td>${imagePo.imagename}</td>
						<td>
							${imagePo.imagelink}
						</td>
						<td>
							<a href="getImageById?imageId=${imagePo.imageid}"><button class="layui-btn layui-btn-small"><i class="layui-icon"></i></button></a>
							<button onclick="confirmTrans(${imagePo.imageid})" class="layui-btn layui-btn-small"><i class="layui-icon"></i></button></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- <div id="ul"></div> -->

		</div>
		<script>
		$(".lookImg").hover(function(){
				$(this).next().show();
			},function(){
				$(this).next().hide();
			})
		</script>
		
		
		
		<script>
			//JavaScript代码区域
		layui.use(['element', 'layer', 'laypage'], function() {
				var element = layui.element;

				var $ = layui.jquery,
					layer = layui.layer;

				var laypage = layui.laypage,
					layer = layui.layer;

			//删除按钮触发事件
					confirmTrans = function(imageId) {
					//配置一个透明的询问框
					parent.layer.msg('确定要删除吗？', {
						btn : [ '确定', '返回' ],
					    yes: function(){
					     location.href="deleteCarousel?imageId="+imageId;
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
					count:pageCount,
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
		    			 "pageCarousel",
		    			 {search:search,pageIndex:pageIndex},
		    			  function(data){
		    				 $(".tb").html("");//清空子节点
		    				 data.list.forEach(function(value,index){//数据渲染
		                       //控制台输出测试 console.log(value);
	    					 var $tr=$('<tr><td>'+value.imageid+'</td>'
   					            +'<td>'+value.imagename+'</td>'
   					            +'<td><img src="..'+value.imageurl+'"/></td>'
   								+'<td>'+value.imagelink+'</td>'
								+'<td><a href="getImageById?imageId='+value.imageid+'"><button class="layui-btn layui-btn-small"><i class="layui-icon"></i><button></a>'
						    	+'<button onclick="confirmTrans('+value.imageid+')" class="layui-btn layui-btn-small"><i class="layui-icon"></i><button></td></tr>');
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
