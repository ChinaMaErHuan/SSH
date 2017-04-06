<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>文件上传-swfupload</title>
	<%@include file="/commons/public.jsp" %>
	<script type="text/javascript" src="${basePath}/js/swfupload/tz_upload.js"></script>
	<style type="text/css">
		.uploadbox{width:640px;background: #f9f9f9;border:2px solid #111;margin:100px; }
		.uploadbox .header{width: 100%;height:36px;background: #111}
		.uploadbox .fname,.fsize,.fnum{color:#fff;line-height: 36px;padding-left: 2px;}
		.uploadbox .uploadProgress{width: 100%;height: 40px;background:#999;position: relative;}
		.uploadbox .percentnum{color: #fff;position: absolute;top:8px;font-size: 16px;}
		.uploadbox .uploadProgress .uploadPercent{width: 0%;height:100%;/*background:linear-gradient(90deg,#111,#999);*/background:pink;}
		.uploadbox .rname{position: absolute;top: 5px;font-size: 12px;left: 5px;color:#fff;}
		.uploadbox p{width: 100%;height: 35px;line-height: 35px;background: #f9f9f9;border-bottom: 1px solid #ccc;}
	</style>
</head>
<body>
	<div id="uploadBtn"></div>
	<script type="text/javascript">
		 $.tzUpload({
// 			url:"springmvc/struts2/jsp/servlet",
			uploadBox:"uploadBox",
			targetId:"uploadBtn",
			size:"600 MB",
			callback:function(data){
				//alert(data);
			}
		});
	</script>
</body>
</html>