<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp"%>
<!DOCTYPE HTML >
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Keywords" content="">
	<meta name="Description" content="">
	<title>文件上传通用组件</title>
	<%@include file="/commons/public.jsp"%>
  </head>
  
  <body>
    <form action="http://localhost:8080/cms/upload/upload" method="post" enctype="multipart/form-data">
  		<input type="file" name="file">
  		<input type="submit" value="提交"/>
  	</form>
  </body>
</html>
