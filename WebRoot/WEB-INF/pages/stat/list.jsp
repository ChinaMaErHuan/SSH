<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Keywords" content="">
<meta name="Description" content="">
<title>统计管理</title>
<%@include file="/commons/public.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath}/css/sg.css">
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/ht_page.css">
<script type="text/javascript" src="${basePath}/js/sg/tz_page.js"></script>
<script type="text/javascript" src="${basePath}/js/ckeditor/ckeditor.js"></script>
</head>
<style type="text/css">
	.tz_searchbox{
	width:400px;
	border: 1px solid #e5e5e5;
	height: 30px;
	border-top-right-radius: 2px;
	border-bottom-right-radius: 2px;
	-webkit-border-top-right-radius: 2px;
	-webkit-border-bottom-right-radius: 2px;
	-moz-border-top-right-radius: 2px;
	-moz-border-bottom-right-radius: 2px;
}
.tz_searchbox{margin:20px auto;margin-bottom: 10px;}
.tz_sinp {border: 0px;width: 335px;float: left;height: 30px;text-indent: 0.5em;padding-right: 5px;overflow: hidden;}
.tz_sbtn {display: block;padding-left: 16px;padding-right: 16px;background: #45b3af;cursor: pointer;position: relative;top:-1px;z-index: 1;}
.tz_sbtn:hover {
	background: #34a5a1;
	transition: all 0.3s ease;
	-webkit-transition: all 0.3s ease;
	-moz-transition: all 0.3s ease;
}
.tmui-status{cursor: pointer;}
</style>
<body>
	<div class="container">
		<%@include file="/commons/header.jsp" %>
		<div id="channelbox" class="ht_con clearfix" style="min-height: 700px;">
			<!-- 搜索框开始 -->
			<div class="tz_searchbox">
				<input type="text" class="tz_sinp fl" id="keyword"
					placeholder="请输入关键字..."> <a class="tz_sbtn btn fr"
					id="tzui_search" onclick="tz_search(this)">搜索</a>
			</div>
			<table class="news_list">
				<thead>
					<tr>
						<th>序号</th>
						<th class="w180">类名</th>
						<th>执行方法</th>
 		          		<th>方法解释</th> 
 		          		<th>执行时间(单位:毫秒)</th> 
		          		<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tz_tbody" data-count="${page.itemCount}">
					<jsp:include page="listTemplate.jsp"></jsp:include>
				</tbody>
			</table>
			<div id="page" class="pagination" data-maxresults="${page.maxResults}"></div>
		</div>
	</div>
	<script type="text/javascript" src="${basePath}/js/admin/stat/tz_stat.js"></script>
</body>
</html>

