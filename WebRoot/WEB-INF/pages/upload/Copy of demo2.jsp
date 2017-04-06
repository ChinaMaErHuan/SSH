<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>文件上传-swfupload</title>
	<%@include file="/commons/public.jsp" %>
	<script type="text/javascript" src="${basePath}/js/swfupload/copy/swfupload.js"></script>
	<script type="text/javascript" src="${basePath}/js/swfupload/copy/swfupload.queue.js"></script>
	<script type="text/javascript" src="${basePath}/js/swfupload/copy/swfupload.speed.js"></script>
	<script type="text/javascript" src="${basePath}/js/swfupload/copy/handlers.js"></script>
	<script type="text/javascript">
		var swfu;
		window.onload = function() {
			var settings = {
				flash_url : "${basePath}/js/swfupload/swfupload.swf",//指明文件上传的flash文件
				flash9_url : "${basePath}/js/swfupload/swfupload_fp9.swf",//新版的flash文件
				upload_url: "${basePath}/upload/upload",//上传的地址
				file_size_limit : "600 MB",//控制当前文件上传的大小
				//file_types : "*.jpg;*gif;*jpeg;*.png;*.bmp",//控制文件上传的类型*.zip;*.html，*.*
				file_types : "*.*",//控制文件上传的类型*.zip;*.html，*.*
				file_post_name:"file",//提交给后台File对象
				post_params:{"result":"为是一个文件上传携带的参数哦!!!"},//给后台传递的参数
				file_types_description : "请选择文件",
				file_upload_limit : 100,//限制文件上传的个数
				file_queue_limit : 100,//队列限制数量
				debug: false,//显示控制台
				// Button settings
				button_image_url: "${basePath}/js/swfupload/XPButtonUploadText_61x22.png",//上传文件的图片
				button_width: "61",//图片的宽度
				button_height: "22",//图片的高度
				button_action:SWFUpload.BUTTON_ACTION.SELECT_FILES,//控制文件是否多选SWFUpload.BUTTON_ACTION.SELECT_FILE(单文件上传去掉S)
				button_placeholder_id: "spanButtonPlaceHolder",
				moving_average_history_size: 40,
				// The event handler functions are defined in handlers.js
				//以下所有的事件的回调函数都定义在handler.js
				swfupload_preload_handler : preLoad,//swfupload加载成功的回调方法
				swfupload_load_failed_handler : loadFailed,//swfupload插件加载失败的回调方法
				file_queued_handler : fileQueued,//文件加载到队列里面的回调函数--handler.js定义
				file_queue_error_handler: fileQueueError,//文件加载失败的回调函数--handler.js定义
				file_dialog_complete_handler: fileDialogComplete,//点击文件上传弹出上传框触发的回调函数---handler.js定义
				upload_start_handler : uploadStart,//上传开始执行的回调函数--handler.js定义
				upload_progress_handler : uploadProgress,//上传过程中的回调函数--handler.js定义
// 				upload_success_handler : uploadSuccess,//上传成功执行的回调函数--handler.js定义
				upload_success_handler : function (file, serverData){
					$("#targetbox").append(serverData.trim());
					var data = eval("("+serverData+")");
					var jdata = $.extend({},file,data);
					$("#targetbox").append("<div><img src='"+basePath+"/"+jdata.url+"' width='200' height='200'/>文件名称是:"+jdata.oldName+"</div>");
				},
				upload_complete_handler : uploadComplete,//上传完毕的回调函数--handler.js定义
				upload_error_handler:uploadError,//上传出错的回调函数--handler.js定义
				custom_settings : {//设置目标显示对象让handler.js里面的回调函数使用
					uploadFileName:document.getElementById("uploadFileName"),
					tdSizeRemained:document.getElementById("tdSizeRemained"),
					tdCountSize:document.getElementById("tdSizeCount"),	
					tdFilesQueued : document.getElementById("tdFilesQueued"),
					tdFilesUploaded : document.getElementById("tdFilesUploaded"),
					tdErrors : document.getElementById("tdErrors"),
					tdCurrentSpeed : document.getElementById("tdCurrentSpeed"),
					tdAverageSpeed : document.getElementById("tdAverageSpeed"),
					tdMovingAverageSpeed : document.getElementById("tdMovingAverageSpeed"),
					tdTimeRemaining : document.getElementById("tdTimeRemaining"),
					tdTimeElapsed : document.getElementById("tdTimeElapsed"),
					tdPercentUploaded : document.getElementById("tdPercentUploaded"),
					tdSizeUploaded : document.getElementById("tdSizeUploaded"),
					percentMark:document.getElementById("percent"),
					tdProgressEventCount : document.getElementById("tdProgressEventCount")
				}
			};
			//初始化
			swfu = new SWFUpload(settings);
	     };
	     
	     function stop(){
 	    	 swfu.cancelQueue();
 	    	 swfu.settings.file_upload_limit = 100;
 	    	 swfu.settings.file_queue_limit = 100;
 	    	 swfu.customSettings.tdFilesQueued.innerHTML = 0;
 	    	 swfu.customSettings.tdFilesUploaded.innerHTML = 0;
 	    	 swfu.customSettings.tdErrors.innerHTML = 0;
	     }
	</script>
	<style type="text/css">
		.uploadbox{width:620px;height: 108px;background: #f9f9f9;border:2px solid #111;margin:100px; }
		.uploadbox .header{width: 100%;height:36px;background: #111}
		.uploadbox .fname,.fsize,.fnum{color:#fff;line-height: 36px;padding-left: 2px;}
		.uploadbox .uploadProgress{width: 100%;height: 40px;background:#999;position: relative;}
		.uploadbox .percentnum{color: #fff;position: absolute;top:8px;font-size: 16px;}
		.uploadbox .uploadProgress .uploadPercent{width: 0%;height:100%;/*background:linear-gradient(90deg,#111,#999);*/background:pink;}
	</style>
</head>
<body>
	
	<div class="uploadbox">
		<h1 class="header">
			<span class="fname">当前上传：<label class="rname" id="uploadFileName"></label></span>
			<span class="fsize">大小：<label class="rsize" id="tdSizeCount"></label></span>
			<span class="fnum">文件个数：<label class="rnum" id="tdFilesQueued"></label>个</span>
		</h1>
		<div>
			<div class="uploadProgress">
				<div class="uploadPercent" id="percent"></div>
				<span class="percentnum" id="tdPercentUploaded"></span>
			</div>
		</div>
	</div>
	
	
	<span id="spanButtonPlaceHolder"></span>
	<input type="button" onclick="stop()" value="停止上传">
	<div>
		<span>文件队列:</span>
		<span id="tdFilesQueued"></span>
	</div>
	<div>
		<span>正在上传的文件:</span>
		<span id="tdFilesUploaded"></span>
	</div>
	<div>
		<span>错误:</span>
		<span id="tdErrors"></span>
	</div>
	<div>
		<span>当前上传的速度:</span>
		<span id="tdCurrentSpeed"></span>
	</div>
	<div>
		<span>上传的平均速度:</span>
		<span id="tdAverageSpeed"></span>
	</div>
	<div>
		<span>复制文件的速度:</span>
		<span id="tdMovingAverageSpeed"></span>
	</div>
	<div>
		<span>剩余时间：</span>
		<span id="tdTimeRemaining"></span>
	</div>
	<div>
		<span>消耗的实际：</span>
		<span id="tdTimeElapsed"></span>
	</div>
	<div>
		<span>上传进度百分比</span>
		<span id="tdPercentUploaded"></span>
	</div>
	<div>
		<span>文件的总大小</span>
		<span id="tdSizeCount"></span>
	</div>
	<div>
		<span>上传的大小</span>
		<span id="tdSizeUploaded"></span>
	</div>
	<div>
		<span>剩余大小</span>
		<span id="tdSizeRemained"></span>
	</div>
	<div>
		<span>上传进度</span>
		<span id="tdProgressEventCount"></span>
	</div>
	
	<div id="targetbox"></div>
</body>
</html>