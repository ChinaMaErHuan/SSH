<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <%
   System.out.println(request.getAsyncContext());
   System.out.println(request.getContentLength());
   System.out.println(request.getRequestURI());
   System.out.println(request.getRequestURL());
   System.out.println(request.getServletPath());
   System.out.println(request.getRemoteHost());
   System.out.println(request.getLocalName());
//    null
//    -1
//    /cms/test.jsp
//    http://localhost:8080/cms/test.jsp
//    /test.jsp
//    127.0.0.1
//    DESKTOP-L8RHVOC
   %>
  </body>
</html>
