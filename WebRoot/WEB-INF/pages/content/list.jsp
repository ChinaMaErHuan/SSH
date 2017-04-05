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
.editor {width: 68%;}
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
</style>
</head>
<body>
	<div class="container clearfix">
		<%@include file="/commons/header.jsp"%>
		<!-- ht_con start  -->
		<div class="ht_con fl clearfix" style='width:30%;padding:0 10px'>
			<!-- 搜索框开始 -->
			<div class="tz_searchbox">
				<input type="text" class="tz_sinp fl" id="keyword"
					placeholder="请输入关键字..."> <a class="tz_sbtn btn fr"
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
					<tz:if test="${tz:size(contents)==0}">
						<tr>
							<td colspan="3"><h1>抱歉！暂无数据^_^o^_^o^_^</h1></td>
						</tr>
						<tz:else />
						<c:forEach items="${contents}" var="content">
							<tr id="tz-items-${content.id}">
								<td>${content.id}</td>
								<td class="tmui-ellipsis w180 title">${content.title}</td>
								<!-- 								<td class="tzui-tips" tip="添加的时间是:${content.createTime}">${tz:formateDate(content.createTime,"yyyy年MM月dd日")}</td> -->
								<td><a href="javascript:void(0);" class="edit_w"
									data-opid="${content.id}" onclick="tz_edit(this)">编辑</a> <a
									href="javascript:void(0);" data-opid="${content.id}"
									onclick="tz_delete(this)">删除</a></td>
							</tr>
						</c:forEach>
					</tz:if>
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
				<div class="tz_editbox fl">
					<input type="text" id="title" class="tz_sinp" placeholder="请输入标题..." /> 
					<a href="javascript:void(0);" id="tz_save" class="fr tz_sbtn btn" onclick="tz_updateSave(this)">保存</a>
				</div>
				<div class="toolbar_btn fl">
					<a href="javascript:void(0);" class="btn tz_sbtn" onclick="tz_localimg(this)">图片本地化</a>
				</div>
			</div>
			<!-- end toolbar -->
			<textarea id="p_desc" name="description"></textarea>
		</div>
		<!-- end editor -->
	</div>
	<div id="imgbox"><ul id="tzimgbox"></ul></div>
	<div class="tmui-overlay" style='display: none;'></div>
	<script type="text/javascript">
		
		function tz_localimg(){
			//第一步获取编辑器里面的图片
			var ctext = getEditorText("p_desc");
			if(isEmpty(ctext)){
				loading("请选择一个编辑的内容...",3);
				return false;
			}
			//获取编辑器的内容 源代码
			var content = getEditorHtml("p_desc");
			//讲编辑器的内容补齐，进行解析
			var $contentbox = $("<div>"+content+"</div>");
			//获取图片
			var imgs = $contentbox.find("img");
			var length  = imgs.length;
			if (length>0) {
				//将图片获取放入数组中，传递给后台。
				var html = "";
				imgs.each(function(){
					var src = $(this).attr("src");
					if(!isValidatorimg(src)){
						html +="<li><img src='"+src+"'><div class='img_con'><a href='javascript:void(0);' onclick='tm_downimg(this)'>下载此图片</a><span></span></div></li>";
					}
				});
				if(isEmpty(html)){
					loading("非常抱歉，没有找到你需要本地化的图片....",4);
					return false;
				}
				
				var $imgbox = $("#imgbox").removeClass().addClass(tz_animateIn()).show();
				tzcenter_pos($imgbox);
				//点击阴影层的时候触发的事件
				$imgbox.next().click(function(){
					//imgbox淡出
					$imgbox.addClass(tz_animateOut(14));
					//清空
					$("#tzimgbox").empty();
					//隐藏阴影层
 					$(this).hide();
				}).show();
				$("#tzimgbox").html(html);
			}else{
				loading("非常抱歉，没有找到你需要本地化的图片....",4);
			}
		};
		
		//下载图片到本地
		function tm_downimg(obj){
			//prev() 获得匹配元素集合中每个元素紧邻的前一个同胞元素
			//拿到图片的网址
			var src = $(obj).parent().prev().attr("src");
			//通过ajax进行异步传输，下载图片放入服务器
			$(obj).removeAttr("onclick").text("下载中...");
			$.ajax({
				type:"post",
				url:basePath+"/json/content/downimg",
				data:{"params.img":src},
				error:function(){
					loading("下载出现问题了...",4);
					$(obj).attr("onclick","tm_downimg(this)").text("下载此图片");
				},
				success:function(data){
					//获取编辑器的内容
					var content = getEditorHtml("p_desc");
					//内容补齐解析内容
					var $contentBox = $("<div>"+content+"</div>");
					//替换内容里面的图片
					$contentBox.find("img[src='"+src+"']").attr("src",basePath+"/"+data.result);
					//替换编辑器里的内容
					setEditorContent("p_desc",$contentBox.html());
					//下载完一张图片就从imgbox中去除
					$(obj).parent().parent().remove();
					//自动save
					$("#tz_save").trigger("click");
					tm_closeDiv();
				}
			});
			
			//window也是一个对象,你要模拟一个javascript的多线程
			window[src] = setInterval(function(){
				tz_getPercent(src);
			},300);
			
			
		};
		
		function tz_getPercent(src){
			$.ajax({
				type:"post",
				url:basePath+"/admin/content/imgPercent",
				data:{"params.img":src},
				success:function(data){
					if(data=="100"){
						clearInterval(window[src]);
					}
					$("#tzimgbox").find("img[src='"+src+"']").parent().find("span").html(data);
				}
			});
		};
		//当全部图片下载完毕以后，关闭弹出层
		function tm_closeDiv(){
			var length = $("#tzimgbox").find("li").length;
			if(length==0){
				 $("#imgbox").addClass(tz_animateOut()).hide().next().trigger("click");
			}
		};
	
		//是否是本地图片
		function isValidatorimg(src){
			if(src.indexOf(basePath)==-1){//方便扩展
				return false;
			}
			return true;
		};
		
		//图片居中
		function tzcenter_pos(obj){
			var top = ($(window).height() - obj.height())/2;
			var left = ($(window).width() - obj.width())/2;
			obj.css({top:top,left:left});
		};
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	</script>
	
	<script type="text/javascript">
		$(function() {
			$(".ht_con").height($(window).height() - 60);
			$(window).unbind("resize").resize(function() {
				$(".ht_con").height($(this).height() - 60);
			});
			//初始化文本编辑器
			CKEDITOR.replace("p_desc", {
				height : $(window).height() - 200
			});
			//第一次加载数据的时候拿到记录总条数
			var count = $("#tz_tbody").data("count");
			//拿到显示的记录数
			var maxResults = $("#page").data("maxresults");
			//初始化分页
			tz_initPage(count, maxResults);

			$(document).keydown(function(e) {
				if (e.keyCode == 13) {
					$("#tzui_search").trigger("click");
				}
			});
		});

		//每页显示X项
		var maxResults = $("#page").data("maxresults");
		function tz_initPage(count, maxResults) {
			$("#page").tzPage(count, {
				num_edge_entries : 1, //边缘页数
				num_display_entries : 3, //主体页数
				num_edge_entries : 2,
				current_page : 0,
				showGo : false,//控制是否显示go 页 ,默认是true
				showSelect : false,//控制是否现在下拉框 默认是true
				items_per_page : maxResults, //每页显示X项
				prev_text : "前一页",
				next_text : "后一页",
				callback : function(pageNo, pageSize) {
					tz_loadingTemplate(pageNo, pageSize);
				}
			});
		};
		//分页模板加载数据
		//截流
		var timer = null;
		//javascript中的函数是一种特殊的数据类型
		function tz_loadingTemplate(pno, psize, callback) {
			clearTimeout(timer);
			timer = setTimeout(function() {
				var keyword = $("#keyword").val().trimAll("g");
				$.ajax({
					type : "post",
					data : {
						"page.firstResult" : (pno * psize),
						"page.maxResults" : psize,
						"params.keyword" : keyword
					},
					url : basePath + "/admin/content/listTemplate",
					success : function(data) {
						$("#tz_tbody").html(data);
						tz_keywordHighlighter(keyword);//关键字高亮
						if (callback) {
							//listTemplate.jsp缓存起来的
							var count = $("#itemCount").val();
							callback(count);
						}
					}
				});
			}, 200);
		};
		//关键字高亮
		function tz_keywordHighlighter(keyword) {
			if (isEmpty(keyword)) return;
			$("#tz_tbody").find(".tmui-ellipsis").each(
					function() {
						var text = $(this).text();
						var regex = new RegExp(keyword, "ig");
						var rtext = text.replace(regex,
								"<span style='color:red'>" + keyword
										+ "</span>");
						$(this).html(rtext);
					});
		};
		//模糊查询
		function tz_search(obj) {
			var keyword = $("#keyword").val();
			if (isEmpty(keyword)) {
				loading("请输入关键字...", 2);
				$("#keyword").focus();
				return;
			}
			tz_loadingTemplate(0, maxResults, function(count) {
				tz_initPage(count, maxResults);//重新初始化分页
			});
		};

		//编辑
		function tz_edit(obj) {
			var opid = $(obj).data("opid");
 			$(obj).text("编辑").removeAttr("onclick");
			$.ajax({
				type:"post",
				url:basePath+"/admin/content/edit",
				data:{"id":opid},
				error : function() {//如果出错了，将事件重新绑定
					$(obj).text("编辑").attr("onclick", "tz_edit(this)");
				},
				success:function(data){
					//alert(jsonToString(data));
 					$(obj).text("编辑").attr("onclick", "tz_edit(this)");
					//将文章id缓存起来
 					$("#title").data("opid",opid).val(data.title);
					
					//把文章内容添加到文本编辑器中
 					setEditorContent("p_desc",data.content);
					}
			});

		};
		
		//更新保存
		function tz_updateSave(obj){
			var title = $("#title").val();
			var ctext = getEditorText("p_desc");
			var opid = $("#title").data("opid");
			var content = getEditorHtml("p_desc");
			if (isEmpty(title)) {
				loading("请输入标题...",3);
				$("#title").focus();
				return false;
			}
			
			if(isEmpty(ctext)){
				loading("请输入内容...",3);
				editorFocus("p_desc");
				return false;
			}
			var params = {"content.title":title,"content.content":content};
			var method = "save";
			
			if(isNotEmpty(opid)){
				method = "update";
				//动态添加
 				params["content.id"] = opid;
			}
			
			$(obj).removeAttr("onclick");
			$.ajax({
				type:"post",
				url:basePath+"/json/content/"+method,
				data:params,
				error : function() {//如果出错了，将事件重新绑定
					$(obj).attr("onclick", "tz_updateSave(this)");},
				success:function(data){
					$(obj).attr("onclick", "tz_updateSave(this)");
					if(data.result=="success"){
						//如果是save
						if(isEmpty(opid)){
							loading("保存成功！！！",4);
							$("#title").removeData("opid").val("");
							setEditorContent("p_desc","");
							$("#tz_tbody").prepend("<tr id='tz-items-"+data.content.id+"'>"+
							"	<td>"+data.content.id+"</td>"+
							"		<td class='tmui-ellipsis w180 title'>"+title+"</td>"+
							"		<td>"+
							"			<a href='javascript:void(0);' data-opid='"+data.content.id+"' onclick='tz_edit(this)' class='edit_w'>编辑</a>"+
							"			<a href='javascript:void(0);' data-opid='"+data.content.id+"' onclick='tz_delete(this)'>删除</a>"+
							"		</td>"+
							"	 </tr>");
						}else{//update
							loading("更新成功!!!",4);
							//清空
// 							$("#title").removeData("opid").val("");
// 							setEditorContent("p_desc","");
							
// 							$("#tz-items-"+opid).find(".title").text(title);
						}
					}
				}
			});
		};

		//删除content 做任何删除之前都要提示
		function tz_delete(obj) {
			$.tzConfirm({
				title : "删除提示",
				content : "您确定删除吗？",
				callback : function(ok) {
					if (ok) {
						var opid = $(obj).data("opid");
						// alert(opid);
						$.tzAjax.request({
							model : "json/content",
							method : "delete",
							callback : function(data) {
								if (data.result = "success") {
									loading("删除成功!", 4, true);
									$("#tz-items-" + opid).fadeOut("slow",
											function() {
												$(this).remove();
											});
									tz_loadingTemplate(0, maxResults, function(
											count) {
										tz_initPage(count, maxResults);//重新初始化分页
									});
								} else {
									loading("删除失败!", 4, true);
								}
							}
						}, {
							id : opid
						});
					}
				}
			});
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
		function setEditorContent(editorId, message) {
			var editor = CKEDITOR.instances[editorId];
			editor.setData(message);
		};

		//追加编辑器内容--追加
		function appendEditorContent(editorId, message) {
			var editor = CKEDITOR.instances[editorId];
			editor.insertHtml(message);
		};
	</script>
</body>
</html>
