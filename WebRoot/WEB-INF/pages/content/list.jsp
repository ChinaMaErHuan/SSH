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
								<!--<td class="tzui-tips" tip="添加的时间是:${content.createTime}">${tz:formateDate(content.createTime,"yyyy年MM月dd日")}</td> -->
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
	<script type="text/javascript" src="${basePath}/js/admin/tz_content.js"></script>
</body>
</html>
