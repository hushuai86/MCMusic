<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>MC Music CD修改</title>
		<link rel="stylesheet" href="layui/css/layui.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="layui/layui.js"></script>
		<style type="text/css">
		#singerNameList{
		  position: absolute;
		  left:110px;
		  top:307px;
		  background-color:white;
		  border-radius:4px;
		  border: 1px solid #999;
		  border-top: hidden;
		  z-index: 1;
		}
		.tab{
		  width:100px;
		  font-size: 15px;
		}
		.line{
		 height:23px;
		 padding-left:10px;
		}
		.line:HOVER{
	      background-color: #E8F2FE;
	      cursor: pointer;
        }
		</style>
	</head>
	<body>
		<div class="center">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>修改CD基本信息</legend>
			</fieldset>
			<div class="message">
				<form method="post" action="adminUpdateCD">
				     <input  type="hidden" name="url" id="url"/>
				     <input  type="hidden" name="cdid" value="${cdPo.cdid}"/>
					<div class="layui-form-item">
						<label class="layui-form-label">专辑名称</label>
						<div class="layui-input-block">
							<input name="cdname"required lay-verify="title" autocomplete="off" placeholder="请输入专辑名称" class="layui-input" type="text" value="${cdPo.cdname}"/>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">收藏次数</label>
						<div class="layui-input-block">
							<input name="collectioncount" lay-verify="title" readonly autocomplete="off" placeholder="请输入收藏次数" class="layui-input" type="text"value="${cdPo.collectioncount}"/>
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">歌曲数量</label>
						<div class="layui-input-block">
							<input name="songcount" lay-verify="title" readonly autocomplete="off" placeholder="请输入歌曲数量" class="layui-input" type="text"value="${cdPo.songcount}"/>
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">发行时间</label>
						<div class="layui-input-inline">
							<input name="date" id="date" required lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" type="text"value="<fmt:formatDate value="${cdPo.publishdate}" type="date"/>"/>
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">歌手姓名</label>
						<div class="layui-input-block">
							<input id="singername" required name="singername" readonly  lay-verify="title" autocomplete="off" placeholder="请输入歌手姓名" class="layui-input" type="text"value="${singername}"/>
						</div>
						 <div id="singerNameList"></div>
					</div>
                  
                    <div class="layui-form-item">
                        <label class="layui-form-label">专辑封面</label>
						<label class="layui-form-label"><img id="img" src="../${cdPo.coverurl}" style="height:40px;width: 40px"></label>
					</div>
					
					<div class="layui-form-item">
					 <label class="layui-form-label">上传封面</label>
						<div class="layui-upload">
							<button name="file"type="button" class="layui-btn layui-btn-danger" id="test7"><i class="layui-icon"></i>上传图片</button>
							<div class="layui-upload-list">
								<img class="layui-upload-img" id="demo1">
								<p id="demoText" name="coverUrl"></p>
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">专辑状态</label>
						<div class="layui-input-inline">
							<select id="cdstateid" name="cdstateid">
        									<option value="">请选择状态</option>
        									<option value="0">0</option>
        									<option value="1">1</option>
      						</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">专辑简介</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" required name="introduce" class="layui-textarea" style="width: 300px;">${cdPo.introduce}</textarea>
						</div>
					</div>
					<button class="layui-btn layui-btn-radius" style="position: absolute;top: 650px;left:160px;">提交</button>
					<button onclick="window.history.go(-1)"  class="layui-btn layui-btn-normal layui-btn-radius" style="position: absolute;top: 650px;left: 280px;">返回</button>
				</form>
			</div>

		</div>
		<script>
			//JavaScript代码区域
			//下拉框选中
			$(function(){
				$("#cdstateid").find("option[value=${cdPo.cdstateid}]").attr("selected",true);
			});
			//文件上传		
			var url = $("#url").val();
			layui.use('upload', function() {
				var $ = layui.jquery,
					upload = layui.upload;

				upload.render({
					elem: '#test7',
					url: 'upCDImage',
					size: 1024*4 ,//限制文件大小，单位 KB
					field:'file',
					done: function(res){
						$("#url").val(res.token);
						$("#img").attr('src','../'+res.token); 
					}
				});
			});
			//日期
			layui.use('laydate', function() {
				var laydate = layui.laydate;
				
				//常规用法
				laydate.render({
					elem: '#date'
				});
			});
			
			//歌手名异步显示
			$("#singername").bind('keyup', function() {
				var search=$("#singername").val();
				$("#singerNameList").html("");
				$.post(
					"searchSinger",
					{search:search},
					function(data){
						  var list= data.list;
						  $("#singerNameList").html("");
	    				  if(list.length==0){
	    					  parent.layer.msg("歌手名不存在");
	    					  $("#singername").val("");
	    				  }else{
	    							var table= "<table class='tab'>";  
	    						    list.forEach(function(value,index){
	    						    	table += "<tr><td class='line'>"+value.singername+"</td></tr>";	 
	    							});
	    						        table+= "</table>";				    
	    							    $("#singerNameList").append(table); 
	    							    $(".line").click(function(){ 				 
	    							    	$("#singername").val($(this).text());
	    							    	$("#singerNameList").html("");
	    							    });
	    					 } 
	    			  });
				 });
		</script>
	</body>

</html>