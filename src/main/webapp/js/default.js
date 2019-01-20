//获取到浏览器屏幕的宽度，并且设置
$(function(){
	$.extend({
	    StandardPost: function(url,args){
	        var body = $(document.body),
	            form = $("<form method='post' target=\"mc\"></form>"),
	            input;
	        form.attr({"action":url});
	        $.each(args,function(key,value){
	            input = $("<input type='hidden'>");
	            input.attr({"name":key});
	            input.val(value);
	            form.append(input);
	        });
	
	        form.appendTo(document.body);
	        form.submit();
	        document.body.removeChild(form[0]);
	    }
	});
	
	
	function changeDefaultWidth(){
		var scrennWidth =$(window).width()+ 'px';
		if($(window).width() < 1400){
			scrennWidth = "1400px";
		}
		var scrennheight =  $(window).height();
		$(".player").css("width",scrennWidth);
		$(".playerContext").css("width",scrennWidth);
		$(".playerContext").css("height",scrennheight);
		$("#nav-main").css("width",scrennWidth);
		$(".focus").css("width",scrennWidth);
		$(".kindSing").css("width",scrennWidth);
		$(".newSings").css("width",scrennWidth);
		$(".hotSings").css("width",scrennWidth);
		$("#foot").css("width",scrennWidth);
		$(".foot").css("width",scrennWidth);
		$(".singerListCon").css("width",scrennWidth);
		$(".stationBg").css("width",scrennWidth);
		$("#userBg").css("width",scrennWidth);
		
		$(".frameNav").css("width",scrennWidth);
		
	}
	changeDefaultWidth();
	
	//屏幕修改宽度事件
	$(window).resize(function(){
	   changeDefaultWidth();
	});
})