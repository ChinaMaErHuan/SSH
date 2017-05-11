<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp"%>
<input type="hidden" id="itemCount" value="${page.itemCount}">
<tz:if test="${tz:size(channels)==0}">
	<tr>
		<td colspan="7"><h1>抱歉！暂无数据^_^o^_^o^_^</h1></td>
	</tr>
	<tz:else />
	<c:forEach items="${channels}" var="channel">
   <tr id="tz-items-${channel.id}" data-itemcount="${page.itemCount}">
   		<td>
   			<tz:if test="${tz:size(channel.channels) > 0}">
   				<a href="javascript:void(0);" class="tzui-expand" onclick="tz_channelexpand(this);" data-opid="${channel.id}" style="font-size: 24px;">+</a>
   			</tz:if>
   		</td>
   		<td><img src="${channel.logo}" width="100" height="30"/></td>
   		<td class="tzui-key tzui-tips tmui-ellipsis">${channel.name}</td>
    	<td>${channel.keyword}</td>
    	<td><input type="text" value="${channel.sort}" style="width:40px;text-align: center;" data-opid="${channel.id}" onblur="tm_update_sort(this)"></td>
    	<td class="tzui-tips" tip="添加的时间是:${channel.createTime}">${tz:formateDate(channel.createTime,'yyyy年MM月dd日')}</td>
    	<td><span data-opid="${channel.id}" data-status="${channel.status}" class="tmui-status ${channel.status==1?'green':'red'}">${channel.status==1?"发布":"未发布"}</span></td>
   		<td>
   			<a href="javascript:void(0);" data-opid="${channel.id}" onclick="tz_edit(this)" class="edit_w">编辑</a>
   			<a href="javascript:void(0);" data-opid="${channel.id}" onclick="tz_delete(this)">删除</a>
   		</td>
   </tr>
   <c:forEach items="${channel.channels}" var="ch">
	   <tr id="tz-items-${ch.id}" class="tmui-channel-c tz-items-${channel.id}-c" style="display: none;">
	   		<td style="text-align: right;">
	   			<tz:if test="${tz:size(ch.channels) > 0}">
	   				<a href="javascript:void(0);" class="tzui-expand" onclick="tz_channelexpand(this);" data-opid="${ch.id}" style="font-size: 24px;">+</a>
	   			</tz:if>
	   		</td>
	   		<td><img src="${ch.logo}" width="100" height="30"/></td>
	   		<td class="tzui-key tzui-tips tmui-ellipsis">${ch.name}</td>
	    	<td>${ch.keyword}</td>
	    	<td><input type="text" value="${ch.sort}" style="width:40px;text-align: center;" data-opid="${ch.id}" onblur="tm_update_sort(this)"></td>
	    	<td class="tzui-tips" tip="添加的时间是:${ch.createTime}">${tz:formateDate(ch.createTime,'yyyy年MM月dd日')}</td>
	    	<td><span data-opid="${ch.id}" data-status="${ch.status}" class="tmui-status ${ch.status==1?'green':'red'}">${ch.status==1?"发布":"未发布"}</span></td>
	   		<td>
	   			<a href="javascript:void(0);" data-opid="${ch.id}" onclick="tz_edit(this)" class="edit_w">编辑</a>
	   			<a href="javascript:void(0);" data-opid="${ch.id}" onclick="tz_delete(this)">删除</a>
	   		</td>
	   </tr>
   </c:forEach>
</c:forEach>
</tz:if>
