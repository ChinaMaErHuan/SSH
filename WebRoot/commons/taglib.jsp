<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tld/tz.tld"  prefix="tz"%>
<%
String path = request.getContextPath();
int port = request.getServerPort();
String basePath = "";
if(port==80){
	 basePath = request.getScheme()+"://"+request.getServerName()+path;
}else{
	 basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
}
request.setAttribute("basePath", basePath);
%>
