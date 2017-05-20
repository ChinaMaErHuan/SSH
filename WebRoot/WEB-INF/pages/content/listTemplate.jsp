<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<tz:if test="${tz:size(contents)==0}">
	<tr>
		<td colspan="3"><h1>抱歉！暂无数据^_^o^_^o^_^</h1></td>
	</tr>
	<tz:else/>
	<c:forEach items="${contents}" var="content" >
		<tr id="tz-items-${content.id}" data-itemcount="${page.itemCount}">
			<td>${content.id}</td>
			<td class="title tmui-ellipsis w180">
				<tz:if test="${empty content.staticUrl}">
	   			<a href="javascript:void(0);" class="tmui-tips" tip="还未静态化">${content.title}</a>
	   			<tz:else/>
	   			<a href="${basePath}/${content.staticUrl}" target="_blank">${content.title}</a>
   				</tz:if>
			</td>
			<td>
				<a href="javascript:void(0);" class="edit_w" data-opid="${content.id}" onclick="tz_edit(this)">编辑</a> 
 				<a href="javascript:void(0);" data-opid="${content.id}" onclick="tz_delete(this)">删除</a>
				<a href="javascript:void(0);" data-opid="${content.id}" onclick="tzAdmin.staticContent(this)">静态</a>
			</td>
			
		</tr>
	</c:forEach>
</tz:if>
