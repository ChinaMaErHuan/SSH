<%@page import="java.io.OutputStreamWriter"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.File"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	//通过ajax渲染成功以后获取的网页的源代码
	String source = request.getParameter("source");
	//静态化页面放哪里去,静态化的目录最好是安装年月日去创建,
	//就按照年月。在备份的时候按天
	String ymd = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	String path = request.getRealPath("/pages/"+ymd);
	//创建一个目录
	File rootPath = new File(path);
	if(!rootPath.exists())rootPath.mkdirs();
	//静态的话的名称
	String fileName = "static.html";
	//获取写入的文件File
	//d://tomcat/tzcms/pages/2015/05/23/static.html
	File filePath = new File(rootPath,fileName);
	//讲获取的网页源代码通过io流写入到static.html中
	//不带编码格式
	String s = source.replaceAll("\\n*|\r*","");
// 	FileWriter writer = new FileWriter(filePath);
 	PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath),"GBK"));
//  PrintWriter writer2 = new PrintWriter(filePath,"GBK");
	writer.write(s);
	writer.close();
%>