<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<tz:contentBean cid="${cid}"/>
<!DOCTYPE HTML>
<html>
  <head>
    <title>${content.title}</title>
	<meta http-equiv="keywords" content="${content.keyword}">
	<meta http-equiv="description" content="${content.description}">
  </head>
  <body>
  	<h1>${content.title}</h1>
  	<br>
  	${content.content}
  </body>
</html>
