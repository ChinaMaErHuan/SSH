<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<tz:if test="${tz:size(stats)==0}">
<tr>
	<td colspan="5"><h1>抱歉！暂无数据^_^o^_^o^_^</h1></td>
</tr>
<tz:else />
<c:forEach items="${stats}" var="stat">
   <tr id="tz-items-${stat.id}" data-itemcount="${page.itemCount}">
   		<td>${stat.id}</td>
   		<td class="tzui-key tzui-tips tmui-ellipsis">${stat.name}</td>
    	<td class="tzui-tips" tip="添加的时间是:${stat.createTime}">${tz:formateDate(stat.createTime,'yyyy年MM月dd日')}</td>
    	<td><span data-opid="${stat.id}" data-status="${stat.status}" class="tmui-status ${stat.status==1?'green':'red'}">${stat.status==1?"发布":"未发布"}</span></td>
   		<td>
   			<a href="${basePath}/admin/stat/edit?id=${stat.id}" data-opid="${stat.id}" class="edit_w">编辑</a>
   			<a href="javascript:void(0);" data-opid="${stat.id}" onclick="tz_delete(this)">删除</a>
   		</td>
   </tr>
</c:forEach>
</tz:if>