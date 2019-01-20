<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>MC Music song修改</title>
		<link rel="stylesheet" href="layui/css/layui.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="layui/layui.js"></script>
	</head>

	<body>

		<div class="center">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>修改song基本信息</legend>
			</fieldset>
			<div class="message">
				<form method="post" action="updateSongReturn">
				 <input  type="hidden" name="url1" id="url1"/>
				 <input  type="hidden" name="url2" id="url2"/>
				 <input  type="hidden" name="songid" id="songid" value="${songPo.songid }"/>
					<div class="layui-form-item">
						<label class="layui-form-label">歌曲名称</label>
						<div class="layui-input-block">
							<input name="songname" required lay-verify="title" value="${songPo.songname }" autocomplete="off" placeholder="请输入歌曲名称" class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">歌手编号</label>
						<div class="layui-input-inline" style="margin-top: 10px;">
							<input name="singerid" value="${songPo.singerid }" readonly lay-verify="title" autocomplete="off"
							 placeholder="请输入歌手名称" class="layui-input" type="text" >
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">专辑编号</label>
						<div class="layui-input-inline" style="margin-top: 10px;">
							<input name="cdid" lay-verify="title" value="${songPo.cdid }" readonly autocomplete="off"
						 	placeholder="请输入专辑编号" class="layui-input" type="text">  
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">歌曲语种</label>
						<div class="layui-input-inline" style="margin-top: 10px;">
							<select name="language" id="language">
							<option value="华语" selected="">华语</option>
							<option value="国语" selected="">国语</option>
							<option value="日语" selected="">日语</option>
							<option value="粤语" selected="">粤语</option>
							<option value="英语" selected="">英语</option>
						  
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">播放次数</label>
						<div class="layui-input-block">
							<input name="playcount" value="${songPo.playcount }" lay-verify="title" readonly  value="0" autocomplete="off"
							placeholder="请输入播放次数" class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">下载次数</label>
						<div class="layui-input-block">
							<input name="downloadcount" value="${songPo.downloadcount }" lay-verify="title" readonly  value="0" autocomplete="off"
							placeholder="请输入下载次数" class="layui-input" type="text"></div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">收藏次数</label>
						<div class="layui-input-block">
							<input name="collectioncount" value="${songPo.collectioncount }" lay-verify="title" readonly  value="0" autocomplete="off"
							placeholder="请输入收藏次数" class="layui-input" type="text" ></div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">发行时间</label>
						<div class="layui-input-inline">
							<input name="date" id="test1" required value="<fmt:formatDate value="${songPo.publishdate }" type="date"/>"  lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">歌曲地址</label>
						<div class="layui-input-inline">
							<button name="songUrl" type="button" value="${songPo.songurl }" class="layui-btn" id="test3"><i class="layui-icon"></i>上传文件</button>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">歌词地址</label>
						<div class="layui-input-inline">
							<button name="cyricurl" type="button"value="${songPo.cyricurl }" class="layui-btn" id="test4"><i class="layui-icon"></i>上传文件</button>
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">歌曲时长</label>
						<div class="layui-input-block">
							<input name="songtime" lay-verify="title" required value="${songPo.songtime }" autocomplete="off" placeholder="请输入歌曲时长" class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">歌曲类型</label>
						<div class="layui-input-inline" style="margin-top: 10px;">
							<select name="typeid" id="typeid">
							<option value="1">重金属</option><option value="2" >伤感</option>
							<option value="3">欢快</option><option value="4" >摇滚</option>
							<option value="5" >嘻哈</option><option value="6" >翻唱</option>
							<option value="7" >民谣</option><option value="8" >串烧</option>
							<option value="9" >苦情歌</option><option value="10" >童谣</option>
							<option value="11" >爵士</option><option value="12" >钢琴</option>
							<option value="13" >网络歌曲</option><option value="14" >兴奋</option>
							<option value="15" >纯音乐</option><option value="16" >KTV</option>
							<option value="17" >嗨歌</option><option value="18" >轻音乐</option>
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">歌曲状态</label>
						<div class="layui-input-inline" style="margin-top: 10px;">
							<select name="songstateid">
        						<option value="0" selected="">0</option>
        						<option value="1">1</option>
      						</select>
						</div>
					</div>
					<button class="layui-btn layui-btn-radius" style="position: absolute;top: 800px;left:160px;">提交</button>
					<button onclick="window.history.go(-1)"  class="layui-btn layui-btn-normal layui-btn-radius" style="position: absolute;top: 800px;left: 280px;">返回</button>
				</form>
			</div>

		</div>
		<script src="layui/layui.js"></script>
		<script>
	 
		var s1 = document.getElementById("language");
	 	 
	    for(i = 0;i<=s1.length;i++){
	        if(s1.options[i].value == '${songPo.language }'){	
	            s1.options[i].selected = true;
	            break;
	        }
	    };
		var s2 = document.getElementById("typeid");	
	 	
	    for(i = 0;i<=s2.length;i++){
	        if(s2.options[i].value == '${songPo.typeid }'){	
	        	
	            s2.options[i].selected = true;
	            break;
	        }
	    };
		
			//JavaScript代码区域
			layui.use('element', function() {
				var element = layui.element;

			});
			layui.use('upload', function() {
				var $ = layui.jquery,
					upload = layui.upload;

				//指定允许上传的文件类型
				var url= $("#url1").val();
				upload.render({
					elem: '#test3',
					url: 'upSong',
					accept: 'file', //普通文件
					done: function(res) {
						$("#url1").val(res.token);
						console.log(res)
					}
				});
			
				//指定允许上传的文件类型
				url= $("#url2").val();
				upload.render({
					elem: '#test4',
					url: 'upLyrics',
					accept: 'file', //普通文件
					done: function(res) {
						$("#url2").val(res.token);
						console.log(res)
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
		</script>
  </body>
</html>
