<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>统计管理列表</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css">
<%@include file="/commons/public.jsp" %>
</head>
<body>
<form id="stat_form">
	<input type="text" name="stat.id" id="opid" value="${stat.id}"/>
	<input type="text" name="stat.name" value="${empty stat.name?'我叫keke':stat.name}"/>
	<input type="button" id="save"  value="提交" onclick="tz_save(this)">
</form>
我是一个添加页面哦!!!小伙伴开始你的工作吧.....
<script type="text/javascript">
	$(function(){
		loading("我爱你们!!!!");
	});
	
	function tz_save(obj){
		var opid = $("#opid").val();
		var method = "save";
		if(isNotEmpty(opid)){
			method = "update";
		}
		var params = $("#stat_form").serialize();
		$(obj).removeAttr("onclick").val("保存中...");
		$.ajax({
			type:"post",
			data:params,
			beforeSend:function(){loading("请稍后，执行中...");},
			url:basePath+"/json/stat/"+method,
			success:function(data){
				$(obj).attr("onclick","tz_save(this)").val("保存");
				loading("数据添加成功",4);
				window.location.href = basePath+"/admin/stat/list";
			}
		});	
	}
</script>
</body>
</html>