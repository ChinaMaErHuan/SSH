<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Keywords" content="">
<meta name="Description" content="">
<title>栏目管理</title>
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
						<th class="w180">标题</th>
						<th>关键字</th>
		          		<th>创建时间</th>
		          		<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tz_tbody" data-count="${page.itemCount}">
					<tz:if test="${tz:size(channels)==0}">
						<tr>
							<td colspan="6"><h1>抱歉！暂无数据^_^o^_^o^_^</h1></td>
						</tr>
						<tz:else />
						<c:forEach items="${channels}" var="channel">
					   		<tr id="tz-items-${channel.id}">
						   		<td>${channel.id}</td>
						   		<td class="tmui-ellipsis w180 tzui-tips title"> ${channel.name}</td>
						   		<td> ${channel.keyword}</td>
						    	<td class="tzui-tips" tip="添加的时间是:${channel.createTime}">${tz:formateDate(channel.createTime,'yyyy年MM月dd日')}</td>
						   		<td><span data-opid="${channel.id}" data-status="${channel.status}" class="tmui-status ${channel.status==1?'green':'red'}">${channel.status==1?"发布":"未发布"}</span></td>
						   		<td>
						   			<a href="javascript:void(0);" data-opid="${channel.id}" onclick="tz_edit(this)" class="edit_w">编辑</a>
						   			<a href="javascript:void(0);" data-opid="${channel.id}" onclick="tz_delete(this)">删除</a>
						   		</td>
			  				 </tr>
						</c:forEach>
					</tz:if>
				</tbody>
			</table>
			<div id="page" class="pagination" data-maxresults="${page.maxResults}"></div>
		</div>
	</div>
	<script type="text/javascript" src="${basePath}/js/admin/tz_channel.js"></script>
</body>
</html>

