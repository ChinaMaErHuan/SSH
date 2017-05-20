<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>添加页面</title>
<%@include file="/commons/public.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath}/css/sg.css">
<link rel="stylesheet" type="text/css" href="${basePath}/css/ht_page.css">
<style type="text/css">
	input[readonly]{background:#ccc}
</style>
<script type="text/javascript" src="${basePath}/js/swfupload/tz_upload.js"></script>
</head>
<body>
<div class="container" style="min-height: 900px;">
    <%@include file="/commons/header.jsp" %>
     <div class="ht_con clearfix">
     	 <form id="channelForm">
          <div class="add_nr">
          		<div class="row_sty clearfix fl">
                   <p>父栏目：</p>
                   <select style="padding:10px 60px;" name="channel.parent.id">
                   		<option>--请选择栏目--</option>
                   		<tz:channelList var="c" pid="1">
                   			<option ${channel.parent.id == c.id?"selected='selected'":""}  value="${c.id}">${c.name}</option>
                   		</tz:channelList>
                   </select>
               </div>
               <div class="row_sty clearfix fl">
                   <p>栏目名称：</p>
                   <input type="hidden" id="channelId" value="${channel.id}"/>
                   <input type="text" name="channel.name" value="${channel.name}"  class="text_box" placeholder="请在此输入标题--">
               </div>
               <div class="row_sty clearfix fl">
                   <p>栏目链接：</p>
                   <input type="text" name="channel.link" value="${channel.link}"  class="text_box" placeholder="请输入栏目链接...">
               </div> 
               <div class="row_sty clearfix fl">
                   <p>栏目LOGO：</p>
                   <input type="text" readonly="readonly" name="channel.logo" value="${channel.logo}"  class="text_box" placeholder="请输入栏目LOGO">
                   <img src="${basePath}/${channel.logo}" width="40" height="40" id="preview">
                   <span id="uploadBtn"></span>
               </div> 
               <div class="row_sty clearfix fl">
                   <p>栏目seo关键字：</p>
                   <input type="text" name="channel.keyword" value="${channel.keyword}"  class="text_box" placeholder="请输入栏目seo关键字">
               </div> 
               <div class="row_sty clearfix fl">
                   <p>栏目seo描述：</p>
                   <input type="text" name="channel.description" value="${channel.description}"  class="text_box" placeholder="请输入栏目seo描述">
               </div>    
               <div class="row_sty clearfix">
                   <p>&nbsp;</p>
                   <a href="javascript:void(0);" onclick="tm_savechannel(this)" class="submit btn">立即提交</a>
                   <a href="${basePath}/admin/channel/list" class="submit btn" style="margin-left: 8px;">返回列表</a>
               </div>          
          </div>
          </form>
     </div>
</div>
<script type="text/javascript">
	$(function(){
		$.tzUpload({
			targetId:"uploadBtn",
			size:"10 MB",
			type:"*.jpg;*png",
			single:true,//选中一个
			callback:function(data){
				var jdata = eval("("+data+")");
				$("#preview").attr("src",basePath+"/"+jdata.url+"?d="+new Date().getTime());
				$("input[name='channel.logo']").val(jdata.url);
			}
		});
	});
	
	//第一步：定义方法
	//第二步：获取参数
	//第三步：讲参数通过ajax进行异步传输
	//第四步：定义action的保存方法(dao)
	//第五步：保存栏目
	//第六步：验证()
	//查漏补缺
	//我总是迫不及待的要把一个功能做的非常的好，而往往忽略了最本质的东西.
	function tm_savechannel(obj){
		//在开发如果还没有页面清空你改做什么。后台需要验证--90%以上的
		var id = $("#channelId").val();
		var params = $("#channelForm").serializeArray();
		var json = tm_parseParams(params);
		if(isEmpty(json["channel.name"])){
			loading("请输入栏目名称!",4);
			$("input[name='channel.name']").focus();
			return;
		}
		
		if(isEmpty(json["channel.link"])){
			loading("请输入栏目URL!",4);
			$("input[name='channel.link']").focus();
			return;
		}
		
		if(isEmpty(json["channel.logo"])){
			loading("请上传LOGO!",4);
			$("input[name='channel.logo']").focus();
			return;
		}
		var method = "save";
		if(isNotEmpty(id)){
			var json = {"name":"channel.id","value":id};
			params.push(json);
			method = "update";
		}
		$(obj).removeAttr("onclick").text("提交中...");
		$.ajax({
			type:"post",
			url:basePath+"/json/channel/"+method,
			data:params,
			success:function(data){
				$(obj).attr("onclick","tm_savechannel(this)").text("保存");
				if(data.result=="success"){
					loading("操作成功!!!",4);
					window.location.href = basePath+"/admin/channel/list";
				}else{
					loading("出现错误了....");
				}
			}
		});
	};
	
		
	function tm_parseParams(params){
		var json = {};
		if(params){
			var length = params.length;
			for(var i=0;i<length;i++){
				var data = params[i];
				json[data.name] = data.value;
			}
		}
		return json;
	}
</script>

</body>
</html>