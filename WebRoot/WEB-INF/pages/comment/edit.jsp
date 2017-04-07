<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>潭州学院CMS系统-评论管理列表</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css">
<%@include file="/commons/public.jsp" %>
</head>
<body>
<form id="comment_form">
	<input type="hidden" name="comment.id" id="opid" value="${comment.id}"/>
	<input type="text" name="comment.name" value="${empty comment.name?'我叫keke':comment.name}"/>
	<input type="button" id="save"  value="提交" onclick="tz_save(this)">
</form>
<script type="text/javascript">
	function tz_save(obj){
		var opid = $("#opid").val();
		var method = "save";
		if(isNotEmpty(opid)){
			method = "update";
		}
		var params = $("#comment_form").serialize();
		$(obj).removeAttr("onclick").val("保存中...");
		$.ajax({
			type:"post",
			data:params,
			beforeSend:function(){loading("请稍后，执行中...");},
			url:basePath+"/json/comment/"+method,
			success:function(data){
				$(obj).attr("onclick","tz_save(this)").val("保存");
				loading("数据添加成功",4);
				window.location.href = basePath+"/admin/comment/list";
			}
		});	
	}
</script>
</body>
</html>