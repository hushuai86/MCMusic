<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>MC Music CD管理</title>
		<link rel="stylesheet" href="css/base.css" />
		<link rel="stylesheet" href="layui/css/layui.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="layui/layui.js"></script>
	</head>
	<body>
		<fieldset class="layui-elem-field layui-field-title">
			<legend>基本操作</legend>
		</fieldset>
		<a href="addCD.jsp"><button class="layui-btn">添加专辑</button></a>
		<button id="bt" class="layui-btn" onclick="deletePageCD()" >删除本页数据</button>
		<a href="toAdminDeletedCD"><button class="layui-btn">已删除专辑</button></a>
		<fieldset class="layui-elem-field layui-field-title">
			<legend>基本查询</legend>
		</fieldset>
		<form class="layui-form" action="adminListCD">
			<div style="width: 800px;float: left;margin-right: 5px;">
				<input type="text" name="search"  value="${search}" placeholder="请输入专辑名称" autocomplete="off" class="layui-input">
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
					<th>专辑封面</th>
					<th>专辑名称</th>
					<th>歌手名称</th>
					<th>歌曲数量</th>
					<th>发行时间</th>
					<th>收藏次数</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="tb">
				<c:forEach items="${pageBasePo.list}" var="cdVo">
					<tr>
					    <input type="hidden" id="cdId" value="${cdVo.CDPo.cdid}"/>
						<td><img src="../${cdVo.CDPo.coverurl}" /></td>
						<td>${cdVo.CDPo.cdname}</td>
						<td>${cdVo.singerPo.singername}</td>
						<td>${cdVo.CDPo.songcount}</td>
						<td><fmt:formatDate value="${cdVo.CDPo.publishdate}"
								type="date" /></td>
						<td>${cdVo.CDPo.collectioncount}</td>
						<td><a href="toAdminUpdateCD?cdId=${cdVo.CDPo.cdid}">
						      <button
									class="layui-btn layui-btn-small">
									<i class="layui-icon"></i>
							   <button>
							</a>
							<button onclick="confirmTrans(${cdVo.CDPo.cdid})"
								class="layui-btn layui-btn-small">
								<i class="layui-icon"></i>
							<button>
							<a href="toAdminUpdateCDSong?cdId=${cdVo.CDPo.cdid}">
							<button
								class="layui-btn layui-btn-small">
								<i class="layui-icon">&#xe6fc;</i>
							<button>
							</a>
								</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="ul"></div>


	</div>
	<script>
		//JavaScript代码区域
		//删除整页数据
	  deletePageCD=function(){
		  var search=$("#search").val();
		  var pageIndex=$("#pageIndex").val();
		  parent.layer.msg('确定删除整页数据吗？', {
				time: 20000, //20s后自动关闭
				btn: ['确定', '返回'],
				yes: function(){
					 $.post("deletePageCD",
						    {pageIndex:pageIndex,search:search},
						    function(data){
						    		location.href=data;
						    	});
					 parent.layer.closeAll('dialog');
	                         }
	             });
	}

		layui.use([ 'element', 'layer', 'laypage' ],
						function() {
							var laypage = layui.laypage, layer = layui.layer;

							//删除按钮触发事件
								confirmTrans = function(id) {
									//配置一个透明的询问框
									parent.layer.msg('确定要删除吗？', {
										btn : [ '确定', '返回' ],
									    yes: function(){
									     location.href="adminDeleteCD?id="+id;
									     parent.layer.closeAll('dialog');
								        }
									});
								}



							//日期格式化
							Date.prototype.toLocaleString = function() {
								return this.getFullYear() + "年"
										+ (this.getMonth() + 1) + "月"
										+ this.getDate() + "日 ";
							};
							//分页
							var pageCount = 5 * ${pageBasePo.pageCount};
							var last = ${pageBasePo.pageCount};
							laypage
									.render({
										elem : 'ul',
										limit : 5,
										count : pageCount,
										first : '首页',
										last : last,
										prev : '<em>←</em>',
										next : '<em>→</em>',
										jump : function(obj, first) {//页面跳转执行函数	
											var search = $("#search").val();
											var pageIndex = obj.curr;//当前页码

											if (!first) { //首次不执行
												$.get(
														//ajax刷新数据
																"adminPageCD",
																{
																	search : search,
																	pageIndex : pageIndex
																},
																function(data) {
																	$(".tb").html("");//清空子节点
																	data.list.forEach(function(value,index) {//数据渲染                
																				var date = new Date(value.cdpo.publishdate);
																				date = date.toLocaleString(); //日期格式化
																				var $tr = $('<tr><td><img src="../'+value.cdpo.coverurl+'"/></td>'
																						+ '<td>'
																						+ value.cdpo.cdname
																						+ '</td><td>'
																						+ value.singerPo.singername
																						+ '</td><td>'				
																						+ value.cdpo.songcount
																						+ '</td><td>'
																						+ date
																						+ '</td><td>'
																						+ value.cdpo.collectioncount
																						+ '</td><td><a href="toAdminUpdateCD?cdId='
																						+ value.cdpo.cdid
																						+ '"><button class="layui-btn layui-btn-small"><i class="layui-icon"></i><button></a>'
																						+ '<button onclick="confirmTrans('+value.cdpo.cdid
																						+')"class="layui-btn layui-btn-small"><i class="layui-icon"></i><button>'						
																						+'<a href="toAdminUpdateCDSong?cdId='
																						+ value.cdpo.cdid
																						+'"><button class="layui-btn layui-btn-small"><i class="layui-icon">&#xe6fc;</i><button></a></td></tr>');
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