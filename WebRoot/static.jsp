<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'static.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  
  	模板+数据=页面
    This is my JSP page. <br>
    <%
    	pageContext.setAttribute("message", "sdlkfjsdhfkasd是对方说的发送到发送到发送大法师的");
    %>
    
    <tz:channelList>
    	<p>==============${channel.name}<p>
    </tz:channelList>
    
    ${message}
  </body>
</html>
