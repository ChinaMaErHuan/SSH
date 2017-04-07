$(function(){
var timer = null;
$("#tz_tbody").on("click",".tmui-status",function(){
	var $this = $(this);
	var opid = $this.data("opid");
	var status = $this.data("status");
	var mark = (status==0 ? 1 : 0 );
	clearTimeout(timer);
	timer = setTimeout(function(){
		loading("数据执行中，请稍后...");
		$.ajax({
			type:"post",
			url:basePath+"/json/channel/update",
			data:{"channel.id":opid,"channel.status":mark},
			success:function(data){
				 $this.data("status",mark).toggleClass((mark==0?"red green":"green red"))
				 .text((mark==0?"未发布":"发布"));
				 loading("操作成功...",4);
			}
		});
	},200);
});
//第一次加载数据的时候拿到记录总条数
var count = $("#tz_tbody").data("count");
//拿到显示的记录数
var maxResults = $("#page").data("maxresults");
//初始化分页
tz_initPage(count, maxResults);
//第一步：把我们数据块，定义成一个模板
//第二部：渲染模板，把数据返回给tbody
//tz_initPage($("#tz_tbody").data("itemcount"));
$(document).keydown(function(e){
	if(e.keyCode == 13){
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
		showGo : true,//控制是否显示go 页 ,默认是true
		showSelect : true,//控制是否现在下拉框 默认是true
		items_per_page : maxResults, //每页显示X项
		prev_text : "前一页",
		next_text : "后一页",
		callback : function(pageNo, pageSize) {
			tz_loadingTemplate(pageNo, pageSize);
		}
	});
};
//点击分页加载回调函数,
//如果一个ajax,访问一个同步url，最终就返回页面的源代码.静态化+ajax(节流)+模板
var timer = null;
function tz_loadingTemplate(pno,psize,callback){
	clearTimeout(timer);
	timer = setTimeout(function(){
		var keyword = $("#keyword").val().trimAll("g");
		$.ajax({
			type:"post",
			data:{"page.firstResult":(pno*psize),"page.maxResults":psize,"params.keyword":keyword},
			url:basePath+"/admin/channel/listTemplate",
			success:function(data){
				$("#tz_tbody").html(data);//等元素加载到页面里面,已经真是存在才能够绑定事件.一定是要你的元素在你的dom节点上存在了才能绑定事件
				$(".tzui-tips").tzTip();
				tz_keywordHighlighter(keyword);//关键字高亮
				if(callback){
					//listTemplate.jsp缓存起来的
					var count = $("#itemCount").val();
					callback(count);
				}
			}
		});
	},200);
};


//搜索,要重新分页,搜索以后总数是发送变化了,总数怎么获取到呢?
//模糊查询
function tz_search(obj) {
	var keyword = $("#keyword").val().trimAll("g");
	if (isEmpty(keyword)) {
		loading("请输入关键字...", 2);
		$("#keyword").focus();
		return;
	}
	tz_loadingTemplate(0, maxResults, function(count) {
		tz_initPage(count, maxResults);//重新初始化分页
	});
};

//登陆，注册
function tz_keywordHighlighter(keyword){
	$("#tz_tbody").find(".tzui-key").each(function(){
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
	url:basePath+"/admin/channel/edit",
	data:{"id":opid},
	success:function(data){
		$("#title").data("opid",opid).val(data.title);
		setEditorContent("p_desc",data.channel);
		}
	});
};

//更新
function tz_updateSave(){
	var title = $("#title").val();
var ctext = getEditorText("p_desc");
var opid = $("#title").data("opid");
var channel = getEditorHtml("p_desc");
if(isEmpty(title)){
	loading("请输入标题...",3);
	$("#title").focus();
	return false;
}

if(isEmpty(ctext)){
	loading("请输入内容...",3);
	editorFocus("p_desc");
	return false;
}
var params = {"channel.title":title,"channel.channel":channel};
var method = "save";
if(isNotEmpty(opid)){
	method = "update";
	params["channel.id"] = opid;
}
$.ajax({
	type:"post",
	url:basePath+"/json/channel/"+method,
	data:params,
	success:function(data){
		if(data.result=="success"){
			if(isEmpty(opid)){
				loading("保存成功！！！");
				$("#title").removeData("opid").val("");
				setEditorContent("p_desc","");
				$("#tz_tbody").prepend("<tr id='tz-items-"+data.channel.id+"'>"+
				"	<td>55</td>"+
				"		<td class='tmui-ellipsis w180 title'>"+title+"</td>"+
				"		<td>"+
				"			<a href='javascript:void(0);' data-opid='"+data.channel.id+"' onclick='tz_edit(this)' class='edit_w'>编辑</a>"+
				"			<a href='javascript:void(0);' data-opid='"+data.channel.id+"' onclick='tz_delete(this)'>删除</a>"+
				"		</td>"+
				"	 </tr>");
			}else{
				loading("更新更新成功!!!");
				$("#title").removeData("opid").val("");
				setEditorContent("p_desc","");
				$("#tz-items-"+opid).find(".title").text(title);
				}
			}
		}
	});
};

//删除
function tz_delete(obj){
	$.tzConfirm({title:"删除提示",channel:"您确定删除吗?",callback:function(ok){
	if(ok){
		var opid = $(obj).data("opid");
		$.tzAjax.request({model:"json/channel",method:"delete",callback:function(data){
			if(data.result=="success"){
				loading("删除成功!",4);
				$("#tz-items-"+opid).remove();
			}else{
				loading("删除失败!",4);
			}
		}},{"channel.id":opid});
		}
	}});
};