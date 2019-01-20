<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		 <base href="<%=basePath%>">
		<meta charset="UTF-8">
		<link rel="stylesheet" href="layui/css/layui.css" />
		<link rel="stylesheet" href="css/default.css" />
		<link rel="stylesheet" href="css/mc.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.js" ></script>
		<script type="text/javascript" src="layui/layui.js" ></script>
		<script type="text/javascript" src="js/default.js" ></script>
		<title></title>
	</head>
	<style>
		body{
			overflow-x: hidden;
		}
	</style>
	<body>
		<audio id="player"></audio>
		<style>
			::-webkit-scrollbar  
			{  
			    width: 0px;  
			}  
		</style>
		<div class="playerContext noOverflow"  style="background-image:url(img/mcBG/test07.png);background-size: cover;">
			<div class="singerDefaultCon noOverflow" style="width: 60%;">
				<div class="singerDefaultSmallCon noOverflow" style="width: 850px;
				  overflow-y:auto; ">
					<ul class="playerSingerButton">
					    <li onclick="window.location='index.html'">返回首页</li>
						<li id="singAdds"><img src="img/player-star01.png" height="22"/>&nbsp;收藏</li>
						<li id="singad"><img src="img/player-add.png" height="22"/>&nbsp;添加</li>
						<li id="singDel"><img src="img/player-delete.png" height="22"/>&nbsp;删除</li>
						<li id="singerDowns"><img src="img/player-down02.png" height="22"/>&nbsp;下载</li>
						<li id="singClear"><img src="img/player-clear.png" height="22"/>&nbsp;清空</li>
					</ul><br /><br />

					<table lay-skin="line" class="singerListTab">
					  <colgroup>
					  	<col width="35"><col width="30"><col width="400"><col width="250"><col width="60">
					  </colgroup>
					  <thead>
					    <tr>
					      <th>
					      	<label for="all" class="checkLabel"></label>
		  					<input type="checkbox" id="all"/>
					      </th>
					      <th></th>
					      <th>歌曲</th>
					      <th>歌手</th>
					      <th>时长</th>
					      <th>播放</th>
					    </tr> 
					  </thead>
					  <tbody>
					  	<c:forEach items="${songList}" var="mc"  varStatus="status">
					    <tr>
					      <input class="singerId" type="hidden" value="${mc.songPo.songid }"/>
					      <td class="urlTd">${mc.songPo.songurl }</td>
					      <td>
					      	<label for="tdInput${ status.index + 1}" class="checkLabel"></label>
		  					<input type="checkbox" value="${mc.songPo.songid }" id="tdInput${ status.index + 1}" />
					      </td>
					      <td class="stateTd">01</td>
					      <td class="tableSing">${mc.songPo.songname }</td>
					      <td class="tableSinger">${mc.singPo.singername }</td>
					      <td >${mc.songPo.songtime }</td>
					      <td>
					      	<button class="changePlayButton"><i class="layui-icon"style="font-size: 25px;color:#DDD">&#xe652;</i></button>
					      </td>
					      <input type="hidden" class="inputSongImg" value="${ mc.singPo.photourl}">
					      <input type="hidden" class="inputCyric" value="${ mc.songPo.cyricurl}">
					      
					    </tr>
					    </c:forEach>
					  </tbody>
					</table>  
					
				</div>
			</div>
			<div class="singerDefaultCon" style="width: 40%;">
				<div class="singerDefaultSmallCon noOverflow" style="width: 450px;text-align: center;">
					<img class="songImg" src="img/singList07.png" height="150px" />
					<div style="width: 470px;">
						<!--利用父容器来隐藏滚动条-->
						<ul id="songWords" style="overflow:auto;margin-top: 20px;height:380px;width: 470px;"></ul>
					</div>
				</div>
			</div>
			<div style="clear: both;"></div>	
		</div>
		<script>
			$(".checkLabel").click(function(){  //判定当个复选框
				var bool = $(this).next("input").is(':checked');
				if(bool){
					$(this).css("background-image","");
				}else{
					$(this).css("background-image","url(img/good.png)");
				}
			})
			
			//----------按钮js
			//清空
			$("#singClear").click(function(){
				$(".singerListTab tbody").html("");
			})
			//删除
			$("#singDel").click(function(){
				var $checkedInput = $(".singerListTab input[type='checkbox']:checked").not($("#all"));
				//alert($checkedInput)
				if($checkedInput.size()==0){
					layui.use(['layer'], function(){
						 var layer = layui.layer;
						 layer.msg('请选择歌曲删除');
					 });
					return;
				}
				$checkedInput.parent().parent().remove();
				//singPlayIndex 设置为删除后的索引位置
				singPlayIndex = 0;
			})
			
			
			//全选
			$("#all").change(function(){
				var bool = $(this).is(':checked');
				if(bool){
					$(".singerListTab input[type='checkbox']").attr("checked","");
					$(".checkLabel").css("background-image","url(img/good.png)");
				}else{
					$(".singerListTab input[type='checkbox']").removeAttr("checked");
					$(".checkLabel").css("background-image","");
				}
			})
			$(".playerContext").css("background-image","url(img/mcBG/test0"+(Math.ceil(Math.random()*10)%7+1)+".png)");
		</script>
		
		

		
		<div class="player">
			<div class="playButton">
				<img id="playPre" src="img/player-pre.png" />
				<img id="playOrStop" src="img/player-play.png"/>
				<img id="playNext" src="img/player-next.png" />
				<!--<img src="img/player-stop.png" />-->
			</div>
			
			<div class="playBar">
				<div class="singText">
					<p style="float: left;"></p>
					<p style="float: right;"><font class="changeTime">0.0</font>/<font class="allTime">0.0</font></p>
				</div>
				<div class="progress">
					<div class="bc"></div>
					<div class="bcmove"></div>
				</div>
			</div>
			
			<div class="otherButton">
				<img id="singPlayModel" src="img/player-order.png" title="顺序播放"/>
				<img id="playStar" src="img/player-star03.png" title="收藏"/>
				<img id="mcDown" src="img/player-down.png" />
			</div>
			<div class="soundButton">
				<img id="soundVolume" src="img/player-sound.png" style="float: left;" />
				<div class="soundProgress">
					<div class="soundBc"></div>
					<div class="soundBcMove"></div>
				</div>
			</div>
		</div>
		
		<script>
		$(function(){
			//player全局变量
			var playBarLength = 900; //playerbar长度
			var playBarLeft = 217;   //playerbar左位置
			//sound全局变量
			var soundBarLength = 85; //soundbar长度
			var soundBarLeft = 1416;   //sound左位置
			//歌词全局
			var songConHeight = 380; //歌词ul父容器的高度
			var songLiHeight = 25;   //歌词li一行的高度
			var songText ;//存放歌词
			var res ; //解析歌词得到的数组
			//歌曲
			var singPlayIndex = 0; //正在播放的歌曲索引
			var singPlayModel = 0; //0表示歌单循环播放，1表示单曲循环播放，2表示随机播放
			
			//---------------------------下一曲和上一曲
			$('#playPre').click(function(){
				var num = $(".urlTd").length;
				singPlayIndex = (singPlayIndex+num-1)%num;
				$(".changePlayButton").eq(singPlayIndex).click();
			});
			$('#playNext').click(function(){
				var num = $(".urlTd").length;
				singPlayIndex = (singPlayIndex+1)%num;
				$(".changePlayButton").eq(singPlayIndex).click();
			});
			
			//-------------------------------------------------------------
			//播放与停止
			var playFlag = false; //默认停止
			$("#playOrStop").click(function(){
				playFlag =!playFlag;
				if(playFlag){
					$('#player').get(0).play();
					$(this).attr("src","img/player-stop.png");
				}else{
					$('#player').get(0).pause();
					$(this).attr("src","img/player-play.png");
				}	
			});
			
			//直接开始播放当前音乐
			function playAutoThis(){
				$("#player").get(0).play();
				$("#playOrStop").attr("src","img/player-stop.png");
				playFlag = true;
			}
			
			//监听就绪状态设置
			$("#player").get(0).oncanplay = function(){
				//设置歌曲名称
				//
				//
				//设置总时间
				var alltime = Math.ceil($("#player").get(0).duration);
				$('.allTime').html(Math.floor(alltime/60) + '.'+ alltime%60);
				//设置声音进度条
				var percentSound = $("#player").get(0).volume;
				$(".soundBcMove").css("left",(percentSound*soundBarLength+soundBarLeft)+"px");
				$(".soundBc").css("width",(percentSound*soundBarLength)+"px");
			};
			
			//监听audio的进度条时间来影响显示的进度条
			$("#player").get(0).ontimeupdate=function(){
				//计算百分比
				var percent =parseFloat($("#player").get(0).currentTime)/parseFloat($("#player").get(0).duration);
				//设置到页面进度条
				$(".bc").css("width",parseInt(playBarLength*percent)+"px");
				$(".bcmove").css("left",(playBarLeft+parseInt(playBarLength*percent))+"px");
				//设置进度时间
				var changetime = Math.ceil($("#player").get(0).currentTime);
				$('.changeTime').html(Math.floor(changetime/60) + '.'+ changetime%60);
				if(res=='') return ;
				//显示歌词高亮滚动,该部分res歌词结果在下个js标签解析
				res.forEach(function(v1, i1, a1){
					if($("#player").get(0).currentTime>v1[0]){
						var thisWord = $("#songWords li").eq(i1);
						thisWord.css("color","lightgreen");
						$("#songWords li").not(thisWord).css("color","#BBB");
						
						//移动歌词
						var songIndex = i1;
						var centerHeight = songConHeight/2;
						var scrollLocation = songLiHeight*songIndex;
						if(scrollLocation>centerHeight){
							$("#songWords").get(0).scrollTop=songLiHeight*songIndex-centerHeight;
						}
						else $("#songWords").get(0).scrollTop=0;
							
					}
				});
				
			}
			
			//监听歌曲进度条结束跳转到下一曲
			$("#player").get(0).onended=function(){
				//根据歌曲播放模式来进行方法调用
				var num = $(".urlTd").length; //播放歌曲总数
				if(singPlayModel==0){//歌单循环播放
					singPlayIndex = (singPlayIndex+1)%num;
				}
				else if(singPlayModel==1){//单曲循环播放
					//歌曲索引不需要变化
				}
				else if(singPlayModel==2){//随机播放
					singPlayIndex = Math.floor(Math.random()*num);
				}
				//重新播放歌曲
				$(".changePlayButton").eq(singPlayIndex).click();
			}
			
			
			//点击灰色背景进行进度设置
			$(".progress").click(function(e){
				//得到相对的点击长度，计算百分比
				var percent = (e.pageX - $(this).position().left)/playBarLength;
				//设置audio的进度，由于监听了audio会移动白条，所以不需要设置白条
				$("#player").get(0).currentTime = $("#player").get(0).duration * percent;
				//设置移动块位置
				$(".bcmove").css("left",(playBarLeft+parseInt(playBarLength*percent))+"px");
			});
			
			//监听移动页面音乐进度条
			var mouseMoveFlag = true; //判断是否可以移动进度块
			$(".bcmove").mousedown(function(e){
				e.preventDefault();
				mouseMoveFlag = true;
				$("body").mousemove(function(e){ //点击后通过设置flag可在页面带动进度块
					e.preventDefault();
					if(mouseMoveFlag){
						if(e.pageX<playBarLeft){ //边界判断
							$(".bcmove").css("left",playBarLeft+"px");
						}
						else if(e.pageX>(playBarLeft+playBarLength)){
							$(".bcmove").css("left",(playBarLeft+playBarLength)+"px");
						}
						else $(".bcmove").css("left",e.pageX+"px");
						var percent = (e.pageX - playBarLeft)/playBarLength;
						$(".bc").css("width",parseInt(playBarLength*percent)+"px");
						//duration得到的总时间，currentTime可设置的当前时间
						$("#player").get(0).currentTime = $("#player").get(0).duration * percent;
					}
				})
				$("body").mouseup(function(e){
					e.preventDefault();
					mouseMoveFlag = false;
				})
			})
			
			
			//-----------------------------------------------------------------------
			//监听audio声音进度条
			$("#player").get(0).onvolumechange = function(){
				var percentSound = $("#player").get(0).volume;
				//判断修改静音图标
				if(percentSound<0.01){
					$("#soundVolume").attr("src","img/player-nosound.png");
				}
				else{
					$("#soundVolume").attr("src","img/player-sound.png");
				}
				//监听设置页面音量位置
				$(".soundBcMove").css("left",(percentSound*soundBarLength+soundBarLeft)+"px");
				$(".soundBc").css("width",(percentSound*soundBarLength)+"px");
			}
			
			//监听sound灰色进度条
			$(".soundProgress").click(function(e){
				//因为比例就是音量，可以直接设置
				$("#player").get(0).volume=(e.pageX-soundBarLeft)/soundBarLength;
			})
			
			//静音
			var soundVolumeFlag = true; //默认没有收藏，后台传入数据判断
			var soundVolumeHistory = 0; //用于记录一个历史音量
			$("#soundVolume").click(function(){
				soundVolumeFlag = !soundVolumeFlag;
				if(!soundVolumeFlag){ //表示声音被静音
					//设置图标并且记录历史音量
					$("#soundVolume").attr("src","img/player-nosound.png");
					soundVolumeHistory = $("#player").get(0).volume;
					$("#player").get(0).volume = 0; //设置音量为0
				}
				else{
					$("#soundVolume").attr("src","img/player-sound.png");
					//回复历史音量
					$("#player").get(0).volume = soundVolumeHistory;
				}
			})
			
			//页面sound进度块
			var soundMouseMoveFlag = true; //判断是否可以移动进度块
			$(".soundBcMove").mousedown(function(e){
				e.preventDefault();
				soundMouseMoveFlag = true;
				$("body").mousemove(function(e){ //点击后通过设置flag可在页面带动进度块
					e.preventDefault();
					if(soundMouseMoveFlag){
						//计算移动百分比，也可以直接当做音量的值
						var percent = (e.pageX - soundBarLeft)/soundBarLength; 
						if(e.pageX<soundBarLeft){ //判断鼠标位置是否在左边界左边
							$(".soundBcMove").css("left",soundBarLeft+"px");//设置进度块为左边界
							percent=0; //不能为负数，直接为0
						}
						else if(e.pageX>(soundBarLeft+soundBarLength)){//判断鼠标位置是否在右边界右边
							$(".soundBcMove").css("left",(soundBarLeft+soundBarLength)+"px");
							percent=1 //不能为大于1，直接为1
						}
						else $(".soundBcMove").css("left",e.pageX+"px");
						//设置白进度条
						$(".soundBc").css("width",parseInt(soundBarLength*percent)+"px");
						//设置音量
						$("#player").get(0).volume = percent;
					}
				})
				$("body").mouseup(function(e){ //监听鼠标松开事件
					e.preventDefault();
					soundMouseMoveFlag = false;
				})
			})
			
			
			
			
			//-------------------------------------------------------------
			//单个收藏收藏
			function isCollection(){
				songId1 = $(".singerId").eq(singPlayIndex).val();//记录id
				$.ajax({ 
				    url: 'isConllectionForSong', 
				    data: {songId:songId1}, 
				    dataType: "json", 
				    async: false,
				    type: "POST", 
				    success: function (flag) {
				    	//根据songid得到显示是否收藏
						if(flag){
							$("#playStar").attr("src","img/player-star02.png");
							$("#playStar").attr("title","已收藏");
						}else{
							$("#playStar").attr("src","img/player-star03.png");
							$("#playStar").attr("title","收藏");
						}
						playStarFlag = flag; //默认没有收藏，后台传入数据判断
						//点击进行收藏

				    }
				})
			}
			$("#playStar").click(function(){//收藏点击事件
				playStarFlag=!playStarFlag;
				if(playStarFlag){
					//执行ajax后台程序 进行收藏
					 $.post("addSongCollection",{songId:songId1},function(data){
						if(data){//已登录收藏
							$("#playStar").attr("src","img/player-star02.png");
							$("#playStar").attr("title","已收藏");
						}else{//未登录收藏
							 layui.use(['layer'], function(){
								 var layer = layui.layer;
								 layer.msg('请登录后再进行收藏');
								 setTimeout('window.open("index.html")', 1000);
							 });
						}
					}); 
				}else{
					$.post("removeSongCollection",{songId:songId1},function(data){
						$("#playStar").attr("src","img/player-star03.png");
						$("#playStar").attr("title","收藏");
					})
				}	
			});
			
			

			//批量收藏
			$("#singAdds").click(function(){
				//拼接参数
				var $checkedInput = $(".singerListTab input[type='checkbox']:checked").not($("#all"));
				var songs ={};
				songs.songId = new Array();
				$checkedInput.each(function(){
					songs.songId.push($(this).attr("value"));
				});
				//判断选择数量
				if(songs.songId.length==0){
					layui.use(['layer'], function(){
						 var layer = layui.layer;
						 layer.msg('请选择歌曲收藏');
					 });
				}else{
					//收藏操作
					$.ajax({ 
					    url: 'addsSongCollection', 
					    data: songs, 
					    dataType: "json", 
					    type: "POST", 
					    traditional: true,//这里设为true就可以了
					    success: function (flag) { 
					    	if(flag){
					    		window.location.reload();
					    	}else{
					    		 layui.use(['layer'], function(){
									 var layer = layui.layer;
									 layer.msg('请登录后再进行收藏');
									 setTimeout('window.open("index.html")', 1000);
								 });
					    	}
					    	
					    } 
					}); 
				}
			})
			
			
			
			
			
			
			//--------------------------------------------------------------
			//播放模式
			//图标和标记切换
			$("#singPlayModel").click(function(){
				if(singPlayModel==0){//歌单循环播放切换为单曲播放
					//修改图标和提示
					$("#singPlayModel").attr("src","img/player-alone.png");
					$("#singPlayModel").attr("title","单曲播放");
					singPlayModel=1;
				}
				else if(singPlayModel==1){//单曲循环播放切换为随机播放
					$("#singPlayModel").attr("src","img/player-random.png");
					$("#singPlayModel").attr("title","随机播放");
					singPlayModel=2;
				}
				else if(singPlayModel==2){
					$("#singPlayModel").attr("src","img/player-order.png");
					$("#singPlayModel").attr("title","顺序播放");
					singPlayModel=0;
				}
			})
			
			//批量下载
			$("#singerDowns").click(function(){
				var $checkedInput = $(".singerListTab input[type='checkbox']:checked").not($("#all"));
				var songs ={};
				songs.songId = new Array();
				$checkedInput.each(function(){
					songs.songId.push($(this).attr("value"));
				});
				if(songs.songId.length==0){
					layui.use(['layer'], function(){
						 var layer = layui.layer;
						 layer.msg('请选择歌曲下载');
					 });
				}else{
					$.StandardPost("musicDown",songs);
				}
			})
			
			//批量添加
			$("#singad").click(function(){
				var $checkedInput = $(".singerListTab input[type='checkbox']:checked").not($("#all"));
				var songs ={};
				songs.songId = new Array();
				$checkedInput.each(function(){
					songs.songId.push($(this).attr("value"));
				});
				if(songs.songId.length==0){
					layui.use(['layer'], function(){
						 var layer = layui.layer;
						 layer.msg('请选择歌曲添加');
					 });
				}else{
					layui.use(['layer'], function(){
						 var layer = layui.layer;
						 $.get("isLogin",function(flag){
							 if(flag){
								 $.post("getMysongList",function(data){
									//构建选择框
									var select = "<select class='select' style='margin:30px;width:70%;height:35px;'>";
									data.forEach(function(value,index){
										select =select +"<option value='"+value.songlistid+"'>"+value.songlistname+"</option>";
									})
									select = select + "</select>";
									select = select + "<button onclick='aAdd()' class='layui-btn'>确定</button>";
									
									aAdd=function(){
										songs.songListId = $(".select").val();
										//收藏操作
										$.ajax({ 
										    url: 'ADDSongList', 
										    data: songs, 
										    dataType: "json", 
										    type: "POST", 
										    traditional: true,//这里设为true就可以了
										}); 
										layer.close(lay);
										layer.msg('添加成功');
									}
									
									//显示弹框选择加入歌单
									var lay = layer.open({
									  type: 1,
									  title :'请选择你要添加的歌单',
									  skin: 'layui-layer-demo', //样式类名
									  closeBtn: 1, //不显示关闭按钮
									  area: ['500px', '150px'],
									  anim: 2,
									  shadeClose: true, //开启遮罩关闭
									  content: select
									})
								 })

							 }else{
								 layer.msg('请先登录');
							 }
						 }) 
					  });
				}
			})
			
			
			//下载
			$("#mcDown").click(function(){
				var id = $(".singerId").eq(singPlayIndex).val();
				$.StandardPost("musicDown",{"songId":id});
			})
			

			//根据歌词动态刷新
			function reflashSongWord(){
				if(songText==""){
					res="";
					$("#songWords").html("暂无歌词");
				}else{
					//解析text
					res = parseLyric(songText);
					//动态增加容器高度
					$("#songWords").parent().css("height",(res.length*songLiHeight)+"px");
					//清空标签和定位
					$("#songWords").html("");
					$("#songWords").get(0).scrollTop=0;
					
					//动态添加歌词li标签
					res.forEach(function(v1, i1, a1) {  
						var li = $("<li>"+v1[1]+"</li>");
						$("#songWords").append(li);
					});
				}
				
			}
			

			//歌词解析器
			function parseLyric(text) { 
				var lines = text.split('\n');//存入数组  
				pattern = /\[\d{2}:\d{2}.\d{2}\]/g;//匹配时间的正则表达式
				result = [];   //最终结果的数组   
				//去掉不含时间的行   
				while (!pattern.test(lines[0])) {   
					lines = lines.slice(1);//选取下标1带末尾的数组   
				};  
				//倒序去掉不含时间的行  
				while (!pattern.test(lines[lines.length - 1])) {   
					lines = lines.slice(0,lines.length - 2);
				};  
				lines[lines.length - 1].length === 0 && lines.pop(); // 去掉末尾空元素    
				lines.forEach(function(v /*数组元素值*/ , i /*元素索引*/ , a /*数组本身*/ ) {   
					var time = v.match(pattern);//提取出时间[xx:xx.xx]   
					var value = v.replace(pattern, '');  //提取歌词   
					
					//因为一行里面可能有多个时间，所以time有可能是[xx:xx.xx][xx:xx.xx][xx:xx.xx]的形式，需要进一步分隔   
					time.forEach(function(v1, i1, a1) {   		
						var t = v1.slice(1, -1).split(':'); //去掉每个时间里的[]
						//将结果压入最终数组 ,格式[xx.xx] 即将分钟合并到秒钟
						result.push([parseInt(t[0], 10) * 60 + parseFloat(t[1]), value]);   
					});   
				});   
				//排序
				result.sort(function(a, b) {   
					return a[0] - b[0];   
				});   
				return result;   
			}
			
			//-------------------------------------------切换播放
			$(".changePlayButton").click(function(){
				//得到对应的索引，即tr的索引
				var index = $(this).parent().parent().index();
				singPlayIndex = index ;
				//------切换音乐
				//修改图标
				$("#player").attr("src",$(".urlTd").eq(index).html());
				var thisTd = $(".stateTd").eq(index); //获取到需要修改的状态td
				thisTd.html('<i class="layui-icon layui-anim layui-anim-rotate layui-anim-loop">&#x1002;</i>');//修改
				$(".stateTd").not(thisTd).each(function(i,v){ //其他状态td为数字
					var numIndex = $(this).parent().index()+1; //得到其索引+1
					if((""+numIndex).length<2){ //例如1，则设置为01
						numIndex = "0"+numIndex;
					}
					$(this).html(numIndex);
					
				});
				
				//设置歌曲底部的信息
				var $tableSing = $(".tableSing").eq(index).html();
				var $tableSinger = $(".tableSinger").eq(index).html();
				$(".singText p").eq(0).html($tableSing+"-"+$tableSinger)
				
				//修改歌曲图片
				$(".songImg").attr("src",$(".inputSongImg").eq(index).val());
				
				if($(".inputCyric").eq(index).val()!=null&&$(".inputCyric").eq(index).val()!=""){
					//ajax获取歌词并且设置到text
					$.ajax({
					    type: "GET",
					    url: $(".inputCyric").eq(index).val(),
					    async: false,
					    success: function(data) {
					        songText = data;
					    }
					});
				}else{
					songText=""
				}
				
				//刷新歌词
				reflashSongWord();
				//直接开始播放当前音乐
				playAutoThis();
				//显示收藏
				isCollection();
				
			});
			
			
			
			
			//默认点击播放列表第一个音乐
			$(".changePlayButton").eq(0).click();
			
			
			
		});
		</script>
		
		
	</body>
</html>
