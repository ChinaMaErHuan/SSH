<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>文件上传-swfupload</title>
	<%@include file="/commons/public.jsp" %>
	<script type="text/javascript" src="${basePath}/js/swfupload/tz_upload.js"></script>
	<style type="text/css">
		
				
	</style>
</head>
<body>
	<span id="uploadBtn"></span>	
	<div id="console"></div>
	<script type="text/javascript">
		 $.tzUpload({
			targetId:"uploadBtn",
			size:"2000 MB",
			//type:"*.png"
			single:false,//允许多个文件上传
			callback:function(data){
				$("#console").append("<div>"+jsonToString(data)+"</div>");
			}
		});
	</script>
</body>
</html>