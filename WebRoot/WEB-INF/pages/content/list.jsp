<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Keywords" content="">
<meta name="Description" content="">
<title>内容管理</title>
<%@include file="/commons/public.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath}/css/sg.css">
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/ht_page.css">
<script type="text/javascript" src="${basePath}/js/sg/tz_page.js"></script>
<script type="text/javascript" src="${basePath}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${basePath}/js/swfupload/tz_upload.js"></script>

<style type="text/css">
.tz_searchbox,.tz_editbox{
	border: 1px solid #e5e5e5;
	height: 30px;
	border-top-right-radius: 2px;
	border-bottom-right-radius: 2px;
	-webkit-border-top-right-radius: 2px;
	-webkit-border-bottom-right-radius: 2px;
	-moz-border-top-right-radius: 2px;
	-moz-border-bottom-right-radius: 2px;
}
.tz_searchbox{margin:20px auto;margin-bottom: 10px;}
.toolbar{margin:20px auto;margin-bottom: 10px;}
.toolbar_btn{height:31px;padding-top:1px;padding-left:20px;}
.tz_editbox{width:400px;}
.tz_sinp {border: 0px;width: 335px;float: left;height: 30px;text-indent: 0.5em;padding-right: 5px;overflow: hidden;}
.tz_sbtn {display: block;padding-left: 16px;padding-right: 16px;background: #45b3af;cursor: pointer;position: relative;top:-1px;z-index: 1;}
.tz_sbtn:hover {
	background: #34a5a1;
	transition: all 0.3s ease;
	-webkit-transition: all 0.3s ease;
	-moz-transition: all 0.3s ease;
}
.editor {width: 60.5%;padding-right: 3px;}
#imgbox{background: #f5f5f5;width: 665px;height:360px;position: absolute;top: 0px;left: 0px;display: none;z-index: 100}
#imgbox li{transition:all ease 0.8s;float: left;padding: 9px;width: 140px;background:#fff;margin:4px;overflow: hidden;}
#imgbox li:nth-child(4n){margin-right:0px;}
#imgbox li:hover{-webkit-box-shadow:1px 1px 2px 2px #888;-moz-box-shadow:1px 1px 2px 2px #888;box-shadow:1px 1px 2px 2px #888;}
#imgbox li img{width: 140px;height: 120px;overflow: hidden;}
#imgbox .img_con{width:140px;height:22px;margin-top:10px;}
#imgbox .img_con a{color: #fff;
    display:inline-block;
    color:#45b3af;
    text-align: center;
    line-height: 20px;
    width:100px;
    border: 1px solid #45b3af;
    -webkit-transition:all ease 0.4s;
    -moz-transition:all ease 0.4s;
    transition:all ease 0.4s;
    }
    
#imgbox li a:hover{color:#fff;background:#45b3af;}
#imgbox .img_con span{display:inline-block;float:right;width:36px;height:20px;text-align:center;color:#45b3af}

.box{width:100px;height: 100%;background:#fff;color:#fff;margin-top:20px;margin-left:3px;border:1px solid #e5e5e5;}
.itemsbox{cursor:pointer;background:#45b3af;transition:all 0.3s linear;}
.itemsbox:hover{background:#00929d;;transition:all 0.3s linear;}
.items{height:40px;border-bottom:1px solid #fff;line-height:40px;color:#fff;text-align: center;line-height:40px;}
.cnt{display:none;background:#f9f9f9;color:#111}
.channel-items{text-align: center;border-bottom:1px solid #e5e5e5;}
.channel-items:hover{background:#dcdedc;}
.channel-items a:hover{color:;transition: all 0.3s linear;}
.channel-items:nth-last-child(1){border:none;}
.selected{background:#dcdedc;}
</style>
</head>
<body>
	<div class="container clearfix">
		<%@include file="/commons/header.jsp"%>
		<div class="box fl" id="boxitems">
	     		<tz:channelList var="ch">
					<div class="itemsbox">	
						<div class="items">${ch.name}</div>
						<div class="cnt">
							<c:forEach items="${ch.channels}" var="c">
								<div id="channel-items-${c.id}" class="channel-items"><a href="javascript:void(0);" data-opid="${c.id}">${c.name}</a></div>
							</c:forEach>
						</div>
					</div>
	     		</tz:channelList>
			</div> 
		<!-- ht_con start  -->
		<div class="ht_con fl clearfix" style='width:30%;padding:0 10px'>
			<!-- 搜索框开始 -->
			<div class="tz_searchbox">
				<input type="text" class="tz_sinp fl" id="keyword"
					placeholder="请输入关键字..." > <a class="tz_sbtn btn fr"
					id="tzui_search" onclick="tz_search(this)">搜索</a>
			</div>
			<table class="news_list">
				<thead>
					<tr>
						<th>序号</th>
						<th>标题</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tz_tbody" data-count="${page.itemCount}">
					<jsp:include page="listTemplate.jsp"></jsp:include>
				</tbody>
			</table>
			<div id="page" class="pagination"
				style='position:relative;left:12%;top:4%;'
				data-maxresults="${page.maxResults}"></div>
		</div>
		<!-- end ht_con -->
		<!-- editor start -->
		<div class="editor fr clearfix">
			<!-- toolbar start -->
			<div class="toolbar clearfix">
				<div style='width:300px;margin-bottom:11px;'><span style = 'width:130px;height:30px;line-height:30px;display:inline-block;text-align:center;background:#45b3af;color:#fff;border-radius:3px;'>当前编辑的栏目是:</span><span  style='display:inline-block;margin-left:10px;'><input id="channelname" style='height:30px;padding-left:5px;' type ="text" readonly="readonly"/></span></div>
				<div class="tz_editbox fl">
					<input type="text" id="title" class="tz_sinp" placeholder="请输入标题..." /> 
					<a href="javascript:void(0);" id="tz_save" class="fr tz_sbtn btn" onclick="tz_updateSave(this)">保存</a>
				</div>
				<div class="toolbar_btn fl">
					<a href="javascript:void(0);" class="btn tz_sbtn" onclick="tz_localimg(this)">图片本地化</a>
				</div>
				<div><span id="uploadBtn"></span></div>
			</div>
			<!-- end toolbar -->
			<textarea id="p_desc" name="description"></textarea>
		</div>
		<!-- end editor -->
	</div>
	<div id="imgbox"><ul id="tzimgbox"></ul></div>
	<div class="tmui-overlay" style='display: none;'></div>
	<script type="text/javascript">

	$(function(){
		$.tzUpload({
			targetId:"uploadBtn",
			size:"2000 MB",
			url:basePath+"/upload/upload",
			//type:"*.jpg;*png",
 			//single:true,
			callback:function(data){
				//alert(data);
				var jdata = eval("("+data+")");
				appendEditorContent("p_desc","<div style='text-align:center;'><img class='tzup_imgitems' src='"+basePath+"/"+jdata.url+"'></div>");
			}
		});
		
		//栏目导航
		$("#boxitems").find(".itemsbox").on("click",function(){
			$(this).find(".cnt").show().end().siblings().find(".cnt").hide();
		}).end().find(".channel-items").click(function(){
			var opid = $(this).find("a").data("opid");
			$(".channel-items").removeClass("selected");
			$(this).addClass("selected");
			$(this).parent().trigger("click");
			$("#channelname").val($(this).text());
			$("#channelname").data("opid",opid);
			//alert($("#channelname").data("opid"));
			tz_loadingTemplate(0,10,function(itemCount){
				tz_initPage(itemCount);
			});
			var length = $("#tz_tbody").find("tr").length;
			//清除数据
			if(length <=1){
				$("#title").val("");
				$("#title").removeData("opid");
				setEditorContent("p_desc","");//删除以后把已经添加的东西删除
			}
		});
		//触发执行第一个
		$("#boxitems").find(".channel-items").eq(0).trigger("click");
		//注册监听器
		CKEDITOR.on('instanceReady', function (e) { 
	          if(e.editor.document.$.addEventListener) 
	               e.editor.document.$.addEventListener('keydown',keydown,false);
	          else if(e.editor.document.$.attachEvent)
	               e.editor.document.$.attachEvent('onkeyup',function(e){keydown(e);});  
	    });
		//监听以后 缓存内容 用户体验
		function keydown(){
			var html = getEditorHtml("p_desc");
			localStorage.setItem("editorCache", html);
			localStorage.setItem("title", $("#title").val());
			localStorage.setItem("cname", $("#channelname").val());
			localStorage.setItem("cid", $("#channelname").data("opid"));	
		};
		
		
	});	

	function tz_localimg(){
		//第一步获取编辑器里面的图片
		var ctext = getEditorText("p_desc");
		if(isEmpty(ctext)){
			loading("请选择一个编辑的内容...",3);
			return false;
		}
		//获取编辑器的内容
		var content = getEditorHtml("p_desc");
		//讲编辑器的内容补齐，进行解析
		var $contentBox = $("<div>"+content+"</div>");
		//获取内容中图片
		var imgs = $contentBox.find("img");
		var length = imgs.length;
		if(length>0){//代表内容里面有网络图片
			//将图片获取放入数组中，传递给后台。
			var html = "";
			imgs.each(function(){
				var src = $(this).attr("src");
				if(isValidatorimg(src)){
					html +="<li><img src='"+src+"'><a href='javascript:void(0);' onclick='tm_downimg(this)'>下载</a><span></span></li>";
				}
			});
			if(isEmpty(html)){
				loading("非常抱歉，没有找到你需要本地化的图片....",4);
				return ;
			}
			
			var $imgbox = $("#imgbox").removeClass().addClass(tz_animateIn()).show();
			tzcenter_pos($imgbox);
			$imgbox.next().click(function(){
				$imgbox.fadeOut("slow");
				$("#tzimgbox").empty();
				$(this).hide();
			}).show();
			$("#tzimgbox").html(html);
		}else{
			loading("没有找到需要本地化的图片!!",4);
		}
	};
	
	function isValidatorimg(src){
		if(src.indexOf(basePath)==-1){//方便扩展
			return true;
		}
		return false;
	};
	
	function tzcenter_pos(obj){
		var top = ($(window).height() - obj.height())/2;
		var left = ($(window).width() - obj.width())/2;
		obj.css({top:top,left:left});
	};
	
	//当前服务器的图片不要参与的下载。
	//上传是否可以带进度条呢?
	
	///json/模块名称/方法名 == ajax
	///admin/模块名称/方法名===非ajax
	//带有进度条，文件下载.
	//下载网络图片
	function tm_downimg(obj){
		var src = $(obj).prev().attr("src");
		//通过ajax进行异步传输，下载图片放入服务器
		$(obj).removeAttr("onclick").text("下载中...");
		$.ajax({
			type:"post",
			url:basePath+"/json/content/downimg",
			data:{"params.img":src},
			error:function(){
				loading("下载出现问题了...",4);
				$(obj).attr("onclick","tm_downimg(this)").text("下载");
			},
			success:function(data){
				//获取编辑器的内容
				var content = getEditorHtml("p_desc");
				//讲编辑器的内容补齐，进行解析
				var $contentBox = $("<div>"+content+"</div>");
				//替换编辑器里面的图片
				$contentBox.find("img[src='"+src+"']").attr("src",basePath+"/"+data.result);
				//替换编辑器里的内容
				setEditorContent("p_desc",$contentBox.html());
				$(obj).parent().remove();
				//自动更新
				$("#tz_save").trigger("click");
				tm_closeDiv();
			}
		});
		
		//window也是一个对象,你要模拟一个javascript的多线程
		window[src] = setInterval(function(){
			tm_getPercent(src);
		},300);
	};
	
	
	function tm_getPercent(src){
		$.ajax({
			type:"post",
			url:basePath+"/admin/content/imgPercent",
			data:{"params.img":src},
			success:function(data){
				if(data==100){
					clearInterval(window[src]);
				}
				$("#tzimgbox").find("img[src='"+src+"']").parent().find("span").html(data);
			}
		});
	}
	
	//当全部图片下载完毕以后，关闭弹出层
	function tm_closeDiv(){
		var length = $("#tzimgbox").find("li").length;
		if(length==0){
			 $("#imgbox").addClass(tz_animateOut()).next().trigger("click");
		}
	}
</script>
<script type="text/javascript">
	$(function(){
		//第一步：把我们数据块，定义成一个模板
		//第二部：渲染模板，把数据返回给tbody
		tz_initPage("${page.itemCount}");
		$(document).keydown(function(e){
			if(e.keyCode == 13){
				$("#tzui_search").trigger("click");
			}
		});
		
		$("#contentbox").height($(window).height()-60);
		$(window).unbind("resize").resize(function(){
			$("#contentbox").height($(this).height()-60);
		});
		
		CKEDITOR.replace("p_desc",{height:$(window).height()-200});
		//如果浏览器突然关闭  取缓存
		var html = localStorage.getItem("editorCache");
		if(html){
			setEditorContent("p_desc", html);
			$("#title").val(localStorage.getItem("title"));
			//alert($("#title").val());
			$("#channelname").val(localStorage.getItem("cname"));
			$("#channelname").data("opid",localStorage.getItem("cid"));
		}
	});
	
	var pageNo = 0;
	var pageSize = 10; 
	function tz_initPage(itemCount){
		$("#page").tzPage(itemCount, {
			num_edge_entries : 1, //边缘页数
			num_display_entries :2, //主体页数
			num_edge_entries:2,
			current_page:0,
			showGo:false,
			showSelect:false,
			items_per_page : pageSize, //每页显示X项
			prev_text : "前一页",
			next_text : "后一页",
			callback : function(pageNo,psize){
				tz_loadingTemplate(pageNo,psize);
			}
		});
	};
	
	
	//搜索,要重新分页,搜索以后总数是发送变化了,总数怎么获取到呢?
	function tz_search(obj){
		var keyword = $("#keyword").val();
		if(isEmpty(keyword)){
			loading("请输入关键字...",4);
			$("#keyword").focus();
			return ;
		}
		tz_loadingTemplate(pageNo,pageSize,function(itemcount){
			tz_initPage(itemcount);
		});
	};
	
	//点击分页加载回调函数,
	//如果一个ajax,访问一个同步url，最终就返回页面的源代码.静态化+ajax(节流)+模板
	var timer = null;
	function tz_loadingTemplate(pno,psize,callback){
		clearTimeout(timer);
		timer = setTimeout(function(){
			var keyword = $("#keyword").val();
			var cid = $("#channelname").data("opid");
			//alert(cid);
			$.ajax({
				type:"post",
				data:{"page.firstResult":(pno*psize),"page.maxResults":psize,"params.keyword":keyword,"params.channelId":cid},
				url:basePath+"/admin/content/listTemplate",
				success:function(data){
					if(isEmpty(data.trim())){
						$("#tz_tbody").html("<tr id='nomessage'><td colspan='3' style='text-align:center;'>该栏目下没有内容!!!</td></tr>");
					}else{
						$("#tz_tbody").html(data);
						tz_keywordHighlighter(keyword);//关键字高亮
						$(".tmui-tips").tzTip();
						if(callback){
							var itemcount = $("#tz_tbody").find("tr").eq(0).data("itemcount");
							callback(itemcount);
						}
					}
				}
			});
		},200);
	};
	
	//登陆，注册
	function tz_keywordHighlighter(keyword){
		if(isEmpty(keyword)) return ;
		$("#tz_tbody").find(".tmui-ellipsis").each(function(){
			var text = $(this).text();
			var regex = new RegExp(keyword,"ig");
			var rtext = text.replace(regex,"<span style='color:red'>"+keyword+"</span>");
			 $(this).html(rtext);
		});
	};
	
	
	//点击编辑的时候触发的时间
	function tz_edit(obj){
		var opid = $(obj).data("opid");
		$.ajax({
			type:"post",
			url:basePath+"/admin/content/edit",
			data:{"id":opid},
			success:function(data){
				$("#title").data("opid",opid).val(data.title);
				$("#channelname").data("opid",data.channelId).val(data.channelName);
				setEditorContent("p_desc",data.content);
				$(".channel-items").removeClass("selected");
				$("#channel-items-"+data.channelId).addClass("selected").parent().trigger("click");
			}
		});
	};
	
	//更新
	function tz_updateSave(){
		var title = $("#title").val();
		var ctext = getEditorText("p_desc");
		var opid = $("#title").data("opid");
		var content = getEditorHtml("p_desc");
		//讲编辑器的内容补齐，进行解析
		var $contentBox = $("<div>"+content+"</div>");
		//获取内容中图片
		var imgs = $contentBox.find("img");
		var length = imgs.length;
		
		var channelId =$("#channelname").data("opid");
		if(isEmpty(channelId)){
			loading("请选择一个栏目进行此操作",4);
			return false;
		}
		
		if(isEmpty(title)){
			loading("请输入标题...",3);
			$("#title").focus();
			return false;
		}
		
		if(isEmpty(ctext) && length==0){
			loading("请输入内容...",3);
			editorFocus("p_desc");
			return false;
		}
		
		var params = {"content.title":title,"content.content":content,"content.channel.id":channelId};
		var method = "save";
		if(isNotEmpty(opid)){
			method = "update";
			params["content.id"] = opid;
		}
		$.ajax({
			type:"post",
			url:basePath+"/json/content/"+method,
			data:params,
			success:function(data){
				if(data.result=="success"){
					$("#nomessage").remove();
					localStorage.clear();//清除缓存
					if(isEmpty(opid)){
						loading("保存成功！！！",4);
						$("#title").data("opid",data.content.id);
						$("#tz_tbody").prepend("<tr id='tz-items-"+data.content.id+"'>"+
						"	<td>55</td>"+
						"		<td class='tmui-ellipsis w180 title'>"+title+"</td>"+
						"		<td>"+
						"			<a href='javascript:void(0);' data-opid='"+data.content.id+"' onclick='tz_edit(this)' class='edit_w'>编辑</a>"+
						"			<a href='javascript:void(0);' data-opid='"+data.content.id+"' onclick='tz_delete(this)'>删除</a>"+
						"		</td>"+
						"	 </tr>");
						//setEditorContent("p_desc","");
					}else{
						loading("更新成功!!!",4);
						//$("#title").removeData("opid").val("");
						//setEditorContent("p_desc","");
						//$("#tz-items-"+opid).find(".title").text(title);
					}
				}
			}
		});
	}
	
	//删除
	function tz_delete(obj){
		$.tzConfirm({title:"删除提示",content:"您确定删除吗?",callback:function(ok){
			if(ok){
				var opid = $(obj).data("opid");
				$.tzAjax.request({model:"json/content",method:"delete",callback:function(data){
					if(data.result=="success"){
						loading("删除成功!",4);
						$("#tz-items-"+opid).remove();
						var length = $("#tz_tbody").find("tr").length;  
						$("#title").val("");
						$("#title").removeData("opid");
						setEditorContent("p_desc","");//删除以后把已经添加的东西删除
						if(length<=1){
							$("#tz_tbody").html("<tr id='nomessage'><td colspan='3' style='text-align:center;'>该栏目下没有内容!!!</td></tr>");
						}
					}else{
						loading("删除失败!",4);
					}
				}},{id:opid});
			}
		}});
	};
	
	
	//得到焦点 
	function editorFocus(id) {
		CKEDITOR.instances[id].focus();
	}

	//获取带有格式的内容--html
	function getEditorHtml(editorId) {
		var editor = CKEDITOR.instances[editorId];
	    return editor.getData();
	};

	//获取编辑器内容,纯文本 text
	function getEditorText(editor) {
		var stemTxt = CKEDITOR.instances[editor].document.getBody().getText();
		return stemTxt;
	};

	//插入编辑器内容--覆盖
	function setEditorContent(editorId,message) {
		var editor = CKEDITOR.instances[editorId];
		editor.setData(message);
	};

	//追加编辑器内容--追加
	function  appendEditorContent(editorId,message){
		var editor = CKEDITOR.instances[editorId];
		editor.insertHtml(message);
	};
	
</script>
</body>
</html>
