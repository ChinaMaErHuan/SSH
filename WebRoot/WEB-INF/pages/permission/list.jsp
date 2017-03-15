<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>权限管理</title>
<%@include file="/commons/public.jsp" %>
<link rel="stylesheet" type="text/css" href="${basePath}/css/sg.css">
<link rel="stylesheet" type="text/css" href="${basePath}/css/ht_page.css">
<link rel="stylesheet" type="text/css" href="${basePath}/js/sg/tree/tm_tree.css">
<script type="text/javascript" src="${basePath}/js/sg/tree/tm_tree.js"></script>

</head>
<body style='background:#fff;'>
	
	<h1>权限列表页面</h1>
	<div id="tree"></div>
	<script type="text/javascript">
		$(function(){
// 			loading("我是一个权限管理哦!");
			//在JS中将JSON的字符串解析成JSON数据格式
			
			var root = eval('${root}');
			
			//tree--要有一个静态的树
			var children = eval(${children});
			$("#tree").tmTree({
				root:root,//根目录数据的设定
				children:children,
				type:"checkbox",//展现的形式checkbox的方式展示,如果等radio那么需要设置isRadio为true
				isRadio:false,
				exclusion:true,
				expand:true,//控制全局的展开和显示
				expandCount:1,//控制第几个节点展开
				onclick:function($this,data){
					
				}		
			});
		});
	</script>
</body>	
</html>
