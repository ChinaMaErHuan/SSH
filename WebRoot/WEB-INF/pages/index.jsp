<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Keywords" content="">
<meta name="Description" content="">
<title>新闻管理</title>
<%@include file="/commons/public.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath}/css/sg.css">
<link rel="stylesheet" type="text/css" href="${basePath}/css/ht_page.css">
</head>

<body>
	<div class="container">
		<%@include file="/commons/header.jsp" %>
		<div class="ht_con clearfix">
			<a href="javascript:void(0);" class="add_btn btn">增加新闻</a>
			<div class="add_nr">
				<div class="row_sty clearfix">
					<p>新闻标题：</p>
					<input type="text" class="text_box" placeholder="请在此输入标题--">
				</div>
				<div class="row_sty clearfix">
					<p>作者：</p>
					<input type="text" class="text_box" placeholder="请在此输入标题--">
				</div>
				<div class="row_sty clearfix">
					<p>新闻内容：</p>
					<textarea class="textarea_nr"></textarea>
				</div>
				<div class="row_sty clearfix">
					<p>&nbsp;</p>
					<a href="javascript:void(0);" class="submit btn">立即提交</a>
				</div>
			</div>
			<table class="news_list">
				<tr>
					<th>序号</th>
					<th>标题</th>
					<th>时间</th>
					<th>操作</th>
				</tr>
				<tr>
					<td>1</td>
					<td>潭州学院官网已更新至v2.1.0 版</td>
					<td>2015-04-28</td>
					<td><a href="javascript:void(0);" class="edit_w">编辑</a><a
						href="javascript:void(0);">删除</a></td>
				</tr>
				<tr>
					<td>2</td>
					<td>《宴会厅装修知识，注意事项》</td>
					<td>2015-04-28</td>
					<td><a href="javascript:void(0);" class="edit_w">编辑</a><a
						href="javascript:void(0);">删除</a></td>
				</tr>
				<tr>
					<td>3</td>
					<td>第二节课新文档的建立和基本工具的介绍和运用</td>
					<td>2015-04-28</td>
					<td><a href="javascript:void(0);" class="edit_w">编辑</a><a
						href="javascript:void(0);">删除</a></td>
				</tr>
				<tr>
					<td>4</td>
					<td>4月24日淘宝运营VIP试听课程：快速提升流量的渠道,你学么？</td>
					<td>2015-04-28</td>
					<td><a href="javascript:void(0);" class="edit_w">编辑</a><a
						href="javascript:void(0);">删除</a></td>
				</tr>
				<tr>
					<td>5</td>
					<td>现在才开淘宝店会太晚吗？</td>
					<td>2015-04-28</td>
					<td><a href="javascript:void(0);" class="edit_w">编辑</a><a
						href="javascript:void(0);">删除</a></td>
				</tr>
				<tr>
					<td>6</td>
					<td>网络交易如何识别真假客户</td>
					<td>2015-04-28</td>
					<td><a href="javascript:void(0);" class="edit_w">编辑</a><a
						href="javascript:void(0);">删除</a></td>
				</tr>
				<tr>
					<td>7</td>
					<td>专业模特给准新娘的10个更上镜秘技</td>
					<td>2015-04-28</td>
					<td><a href="javascript:void(0);" class="edit_w">编辑</a><a
						href="javascript:void(0);">删除</a></td>
				</tr>
				<tr>
					<td>8</td>
					<td>西式婚礼的流程以及拍摄指南</td>
					<td>2015-04-28</td>
					<td><a href="javascript:void(0);" class="edit_w">编辑</a><a
						href="javascript:void(0);">删除</a></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="bottom">Copyright © 2013-2015 www.tanzhouedu.com All
		Rights Reserved 版权所有：湖南潭州教育咨询有限公司 备案号：备13016338号</div>
</body>
</html>

