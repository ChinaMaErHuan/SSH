<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<tz:if test="${tz:size(channels)==0}">
<tr>
	<td colspan="6"><h1>抱歉！暂无数据^_^o^_^o^_^</h1></td>
</tr>
<tz:else />
<c:forEach items="${comments}" var="comment">
   <tr id="tz-items-${comment.id}" data-itemcount="${page.itemCount}">
   		<td>${comment.id}</td>
   		<td class="tzui-key tzui-tips tmui-ellipsis">${comment.name}</td>
    	<td>${comment.hits}</td>
    	<td class="tzui-tips" tip="添加的时间是:${comment.createTime}">${tz:formateDate(comment.createTime,'yyyy年MM月dd日')}</td>
    	<td><span data-opid="${comment.id}" data-status="${comment.status}" class="tmui-status ${comment.status==1?'green':'red'}">${comment.status==1?"发布":"未发布"}</span></td>
   		<td>
   			<a href="${basePath}/admin/comment/edit?id=${comment.id}" data-opid="${comment.id}" class="edit_w">编辑</a>
   			<a href="javascript:void(0);" data-opid="${comment.id}" onclick="tz_delete(this)">删除</a>
   		</td>
   </tr>
</c:forEach>
</tz:if>