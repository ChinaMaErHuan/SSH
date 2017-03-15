<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<input type="hidden" id="itemCount" value="${page.itemCount}">
<tz:if test="${tz:size(contents)==0}">
	<tr>
		<td colspan="3"><h1>抱歉！暂无数据^_^o^_^o^_^</h1></td>
	</tr>
	<tz:else/>
	<c:forEach items="${contents}" var="content">
		<tr id="tz-items-${content.id}">
			<td>${content.id}</td>
			<td class="title tmui-ellipsis w180">${content.title}</td>
<!-- 			<td class="tzui-tips" tip="添加的时间是:${content.createTime}">${tz:formateDate(content.createTime,"yyyy年MM月dd日")}</td> -->
			<td><a href="javascript:void(0);" class="edit_w" data-opid="${content.id}" onclick="tz_edit(this)">编辑</a> <a
			href="javascript:void(0);" data-opid="${content.id}" onclick="tz_delete(this)">删除</a></td>
		</tr>
	</c:forEach>
</tz:if>
