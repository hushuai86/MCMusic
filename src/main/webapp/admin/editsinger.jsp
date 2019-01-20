<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>MC Music singer修改</title>
		<link rel="stylesheet" href="layui/css/layui.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="layui/layui.js"></script>
	</head>

	<body>

		<div class="center">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>歌手基本信息</legend>
			</fieldset>
			<div class="message">
				<form method="post" action="updateSingerReturn" >
					<input  type="hidden" name="url" id="url"/>
					<input  type="hidden" name="singerid" id="singerid" value="${singerPo.singerid }"/>
					<div class="layui-form-item">
						<label class="layui-form-label">歌手名称</label>
						<div class="layui-input-block">
							<input name="singername" required lay-verify="title" autocomplete="off" placeholder="请输入歌手名称" class="layui-input" type="text" value="${singerPo.singername }">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">歌手热度</label>
						<div class="layui-input-block">
							<input name="accesscount"  lay-verify="title" readonly  autocomplete="off" placeholder="请输入歌手热度" class="layui-input" type="text" value="${singerPo.accesscount }">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">收藏次数</label>
						<div class="layui-input-block">
							<input name="collectioncount" lay-verify="title" readonly  autocomplete="off" placeholder="请输入收藏次数" class="layui-input" type="text" value="${singerPo.collectioncount }">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">地区</label>
						<div class="layui-input-inline" style="margin-top: 10px;">
							<select name="areaid" id="areaid">
        									<option value="1">中国</option><option value="2">中国香港</option><option value="3">新加坡</option>
        									<option value="4">美国</option><option value="5">马来西亚</option><option value="6">中国台湾</option>
        									<option value="7">英国</option><option value="8">法国</option><option value="9">加拿大</option>
        									<option value="10">瑞典</option><option value="11">哈斯克斯坦</option><option value="12">德国</option>
        									<option value="13">韩国</option><option value="14">日本</option><option value="15">瑞士</option>
        									<option value="16">泰国</option><option value="17">中国内地</option><option value="18">克罗地亚</option>
        									<option value="19">巴巴多斯</option><option value="20">意大利</option><option value="21">牙买加</option>
        									<option value="22">西班牙</option><option value="23">澳大利亚</option><option value="24">爱尔兰</option>
        									<option value="25">罗威</option><option value="26">欧美</option>
      					    </select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">歌手性别</label>
						<div class="layui-input-block" >
							<input style="margin-top: 10px;" name=singersex value="1" title="男"   type="radio">男
							<input name="singersex" value="0" title="女" type="radio">女
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">歌手生日</label>
						<div class="layui-input-inline">
							<input name="date1" id="test1" required value="<fmt:formatDate value="${singerPo.birthday }" type="date"/>" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" type="text">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">歌手图片</label>
						<img  name="photo" id="photo" style="width: 40px; height: 38px; float: left;"  > 
						  
						<div class="layui-upload">&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="layui-btn layui-btn-danger" id="test7"><i class="layui-icon"></i>上传图片</button>
							<div class="layui-upload-list">
								<img class="layui-upload-img" id="demo1">
								<p id="demoText" name="photourl"></p>
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">出道时间</label>
						<div class="layui-input-inline">
							<input name="date2" id="test2" required value="<fmt:formatDate value="${singerPo.debutdate }" type="date"/>" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">歌手简介</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" required name="introduce"  class="layui-textarea" style="width: 300px;">${singerPo.introduce }</textarea>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">歌手状态</label>
						<div class="layui-input-inline" style="margin-top: 10px;">
							<select name="singerstateid" id="singerstateid">
        									
        									<option value="0" selected="">0</option>
        									<option value="1">1</option>
      									</select>
						</div>
					</div>
					<button class="layui-btn layui-btn-radius" style="position: absolute;top: 700px;left:160px;">提交</button>
					<button onclick="window.history.go(-1)"  class="layui-btn layui-btn-normal layui-btn-radius" style="position: absolute;top: 700px;left: 280px;">返回</button>
				</form>

			</div>

		</div>
		<script src="layui/layui.js"></script>
		<script>
			//JavaScript代码区域
			var s1 = document.getElementById("areaid");
	
		    var singersex=document.getElementsByName("singersex");   

		    var photo = document.getElementById("photo");
		    $("#photo").attr("src",'../${singerPo.photourl}');
		    
		    for(i = 0;i<=s1.length;i++){
		        if(s1.options[i].value == '${singerPo.areaid }'){	
		            s1.options[i].selected = true;
		            break;
		        }
		    }
 		 	
            if(${singerPo.singersex}?1:0 == 1){	   
                singersex[0].checked = true;  
            }else{ 		
            	 singersex[1].checked = true;  
            }
             
		  	
			
			layui.use('element', function() {
				var element = layui.element;

			});
			var url = $("#url").val();
		 	
			layui.use('upload', function() {
				var $ = layui.jquery,
					upload = layui.upload;

				upload.render({
					elem: '#test7',
					url: 'upSinger',
					size: 4096, //限制文件大小，单位 KB
					field:'file',
					done: function(res) {
						$("#url").val(res.token);
						console.log(res.token);
						$("#photo").attr("src",'..'+res.token);
					}
				});
			});
			layui.use('laydate', function() {
				var laydate = layui.laydate;

				//常规用法
				laydate.render({
					elem: '#test1'
				});
			});

			layui.use('laydate', function() {
				var laydate = layui.laydate;

				//常规用法
				laydate.render({
					elem: '#test2'
				});
			});
		</script>
  </body>
</html>
