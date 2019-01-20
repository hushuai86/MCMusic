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
		<a href="adminListCD"><button class="layui-btn">返回专辑管理</button></a>
        <button id="bt" class="layui-btn" onclick="recoverPageCD()" >恢复本页数据</button>
		<fieldset class="layui-elem-field layui-field-title">
			<legend>基本查询</legend>
		</fieldset>
		<form class="layui-form" action="toAdminDeletedCD">
			<div style="width: 800px;float: left;margin-right: 5px;">
				<input type="text" name="search" required lay-verify="required" value="${search}" placeholder="请输入专辑名称" autocomplete="off" class="layui-input">
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
						<td>
							<button onclick="confirmTrans(${cdVo.CDPo.cdid})"
								class="layui-btn layui-btn-small">
								<i class="layui-icon">&#xe65c;</i>
							<button>
					
								</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div id="ul"></div>


	</div>
	<script>
		//JavaScript代码区域

		layui.use([ 'element', 'layer', 'laypage' ],
						function() {
							var laypage = layui.laypage, layer = layui.layer;

							//删除按钮触发事件
								confirmTrans = function(id) {
									//配置一个透明的询问框
									parent.layer.msg('确定要恢复吗？', {
										btn : [ '确定', '返回' ],
									    yes: function(){
									     location.href="adminRecoverCD?cdId="+id;
									     parent.layer.closeAll('dialog');
								        }
									});
								}
								 //恢复整页数据
								 recoverPageCD=function(){
									  var search=$("#search").val();
									  var pageIndex=$("#pageIndex").val();
									  parent.layer.msg('确定要恢复整页吗？', {
											btn : [ '确定', '返回' ],
										    yes: function(){
										    	$.post(
												    	"recoverPageCD",
												    	{search:search,pageIndex:pageIndex},
												    	function(data){
												    		location.href=data;
												    	});
										    	 parent.layer.closeAll('dialog');
									        }
									     }
								      );
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
							laypage.render({
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
																"adminDeletedCDPage",
																{
																	search : search,
																	pageIndex : pageIndex
																},
																function(data) {
																	$(".tb").html("");//清空子节点
																	data.list.forEach(function(value,index) {//数据渲染    
 																			   console.log(value);
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
																						+ '</td><td>'
																						+ '<button onclick="confirmTrans('+value.cdpo.cdid
																						+')"class="layui-btn layui-btn-small"><i class="layui-icon">&#xe65c;</i><button>'						
																						+'</td></tr>');
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