<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp"%>
<input type="hidden" id="itemCount" value="${page.itemCount}">
<tz:if test="${tz:size(channels)==0}">
	<tr>
		<td colspan="6"><h1>抱歉！暂无数据^_^o^_^o^_^</h1></td>
	</tr>
	<tz:else />
	<c:forEach items="${channels}" var="channel">
		<tr id="tz-items-${channel.id}">
			<td>${channel.id}</td>
			<td class="tmui-ellipsis w180 tzui-tips title">${channel.name}</td>
			<td>${channel.keyword}</td>
			<td class="tzui-tips" tip="添加的时间是:${channel.createTime}">${tz:formateDate(channel.createTime,'yyyy年MM月dd日')}</td>
			<td><span data-opid="${channel.id}"
				data-status="${channel.status}"
				class="tmui-status ${channel.status==1?'green':'red'}">${channel.status==1?"发布":"未发布"}</span></td>
			<td><a href="javascript:void(0);" data-opid="${channel.id}"
				onclick="tz_edit(this)" class="edit_w">编辑</a> <a
				href="javascript:void(0);" data-opid="${channel.id}"
				onclick="tz_delete(this)">删除</a></td>
		</tr>
	</c:forEach>
</tz:if>
