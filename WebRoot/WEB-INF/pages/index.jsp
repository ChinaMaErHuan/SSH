<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Keywords" content="">
<meta name="Description" content="">
<title>网站首页</title>
<%@include file="/commons/public.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath}/css/sg.css">
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/ht_page.css">
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

img {
	border: 0;
}
/*tz_content start*/
.tz_content {
	width: 100%;
}
/*t_right start*/
.tz_content .t_right {
	height: 300px;
	background: #f3f4f5;
}

.tz_content .t_right .r_position {
	height: 51px;
	border-bottom: 1px solid #ccc;
	background: #e5e5e5;
	line-height: 51px;
}

.tz_content .t_right .r_position i {
	width: 14px;
	height: 18px;
	display: block;
	float: left;
	background: url("images/ht_icon.png") no-repeat -16px -51px;
	margin: 16px 15px 0 20px;
}

.tz_content .t_right .r_position p span:hover {
	cursor: pointer;
	text-decoration: underline;
}

.tz_content .t_right .r_message .m_list {
	height: 99px;
	margin-top: 20px;
	float: left;
	margin-left: 1%;
}

.tz_content .t_right .r_message .m_list .l_icon {
	width: 44px;
	height: 46px;
	display: block;
	float: left;
	background: url("images/ht_icon.png") no-repeat;
	margin: 26px 10px 0px 30px;
}

.tz_content .t_right .r_message .m_list span {
	font-size: 24px;
}

.tz_content .t_right .r_message .m_list .l_info {
	color: #fff;
	margin-top: 25px;
}

.tz_content .t_right .r_message .m_1 {
	width: 22%;
	background: #27a9e3;
}

.tz_content .t_right .r_message .m_1 .l_icon {
	background-position: 0 -76px;
}

.tz_content .t_right .r_message .m_2 {
	width: 16%;
	background: #28b779;
}

.tz_content .t_right .r_message .m_2 .l_icon {
	background-position: -46px -76px;
	width: 46px;
}

.tz_content .t_right .r_message .m_3 {
	width: 17%;
	background: #ffb848;
}

.tz_content .t_right .r_message .m_3 .l_icon {
	background-position: -96px -76px;
	width: 46px;
	margin-top: 33px;
}

.tz_content .t_right .r_message .m_4 {
	width: 21%;
	background: #cc6a6a;
}

.tz_content .t_right .r_message .m_4 .l_icon {
	background-position: -143px -76px;
}

.tz_content .t_right .r_message .m_5 {
	width: 18%;
	background: #2255a4;
}

.tz_content .t_right .r_message .m_5 .l_icon {
	background-position: -190px -76px;
	height: 55px;
}

.tz_content .t_right .r_message .m_6 {
	width: 17%;
	background: #da542e;
}

.tz_content .t_right .r_message .m_6 .l_icon {
	background-position: -239px -76px;
}

.tz_content .t_right .r_message .m_7 {
	width: 20%;
	background: #2255a4;
}

.tz_content .t_right .r_message .m_7 .l_icon {
	background-position: -288px -76px;
	width: 50px;
}

.tz_content .t_right .r_message .m_8 {
	width: 18%;
	background: #6297bc;
}

.tz_content .t_right .r_message .m_8 .l_icon {
	background-position: -342px -76px;
	width: 49px;
}

.tz_content .t_right .r_message .m_9 {
	width: 20%;
	background: #27a9e3;
}

.tz_content .t_right .r_message .m_9 .l_icon {
	background-position: -395px -76px;
	width: 50px;
}

.tz_content .t_right .r_message .m_10 {
	width: 19%;
	background: #28b779;
}

.tz_content .t_right .r_message .m_10 .l_icon {
	background-position: -449px -76px;
	width: 47px;
}

.tz_content .t_right .r_message .m_list:hover {
	background: #2f3437;
}

.tz_content .t_right .clear {
	clear: both;
}

.tz_content .t_right .r_table {
	border: 1px solid #ccc;
	background: #fff;
	margin: 20px 1% 0 1%;
	padding-bottom: 10px;
}

.tz_content .t_right .r_table .t_title {
	width: 100%;
	height: 40px;
	background: #eee;
	border-bottom: 1px solid #ddd;
}

.tz_content .t_right .r_table .t_title i {
	width: 16px;
	height: 14px;
	display: block;
	float: left;
	background: url("images/ht_icon.png") no-repeat -386px -15px;
	margin: 12px;
}

.tz_content .t_right .r_table .t_title p {
	float: left;
	line-height: 39px;
	border-left: 1px solid #ccc;
	padding-left: 10px;
	color: #000;
}

.tz_content .t_right .r_table .t_label {
	width: 80%;
	height: 100px;
	float: left;
}

.tz_content .t_right .r_table .t_label ul li {
	list-style: none;
	float: left;
	background: #999;
	margin: 10px 10px 0 10px;
}

.tz_content .t_right .r_table .t_label ul li a {
	display: block;
	text-decoration: none;
	color: #fff;
	padding: 8px 22px;
}

.tz_content .t_right .r_table .t_label ul li a i {
	width: 15px;
	height: 14px;
	display: inline-block;
	float: left;
	background: url("images/ht_icon.png") no-repeat;
	margin: 2px 10px 0px 0px;
}

.tz_content .t_right .r_table .t_label ul li a .l_icon_1 {
	background-position: -33px -52px;
}

.tz_content .t_right .r_table .t_label ul li a .l_icon_2 {
	background-position: -49px -52px;
}

.tz_content .t_right .r_table .t_label ul li a .l_icon_3 {
	background-position: -67px -52px;
	width: 16px;
	height: 15px;
}

.tz_content .t_right .r_table .t_label ul li a .l_icon_4 {
	background-position: -87px -52px;
}

.tz_content .t_right .r_table .t_label ul li a .l_icon_5 {
	background-position: -105px -52px;
}

.tz_content .t_right .r_table .t_label ul li a .l_icon_6 {
	background-position: -123px -52px;
}

.tz_content .t_right .r_table .t_label ul li a .l_icon_7 {
	background-position: -191px -52px;
	height: 15px;
}

.tz_content .t_right .r_table .t_label ul li a .l_icon_8 {
	background-position: -212px -52px;
}

.tz_content .t_right .r_table .t_label ul li a .l_icon_9 {
	background-position: -140px -52px;
}

.tz_content .t_right .r_table .t_label ul li a .l_icon_10 {
	background-position: -155px -52px;
}

.tz_content .t_right .r_table .t_label ul li a .l_icon_11 {
	background-position: -172px -52px;
}

.tz_content .t_right .r_table .t_label ul li:hover {
	background: #28b779;
}

.tz_content .t_right .r_table .t_search {
	width: 19%;
	height: 30px;
	float: left;
	margin-top: 10px;
}

.tz_content .t_right .r_table .t_search .t_text {
	width: 60%;
	height: 28px;
	float: right;
	border: 1px solid #ddd;
	line-height: 28px;
	outline: none;
	padding-left: 10px;
}

.tz_content .t_right .r_table .t_search .t_btn {
	width: 20%;
	height: 30px;
	float: right;
	background: #999;
	border: none;
	color: #fff;
	outline: none;
}

.tz_content .t_right .r_table .t_table_list {
	width: 100%;
}

.tz_content .t_right .r_table .t_table_list .t_tab {
	width: 98%;
	border: 1px solid #ddd;
	margin: 0 1% 0 1%;
	border-collapse: collapse;
}

.tz_content .t_right .r_table .t_table_list .t_tab thead tr {
	line-height: 40px;
	background: #eee;
}

.tz_content .t_right .r_table .t_table_list .t_tab tr td {
	border: 1px solid #ddd;
	text-align: center;
	line-height: 40px;
}

.tz_content .t_right .r_table .t_table_list .t_tab tbody tr:hover {
	background: #eee;
}

.tz_content .t_right .r_table .t_table_list .t_tab thead tr .action {
	width: 120px;
}

.tz_content .t_right .r_table .t_table_list .t_tab tbody .t_tools {
	width: 108px;
	height: 24px;
}

.tz_content .t_right .r_table .t_table_list .t_tab tbody .t_tools a {
	width: 24px;
	height: 24px;
	display: block;
	float: left;
	margin-left: 3px;
	background: url("images/ht_icon.png") no-repeat;
}

.tz_content .t_right .r_table .t_table_list .t_tab tbody .t_tools .t_see
	{
	background-position: -359px -45px;
	background-color: #6297bc;
}

.tz_content .t_right .r_table .t_table_list .t_tab tbody .t_tools .t_edit
	{
	background-position: -379px -47px;
	background-color: #27a9e3;
}

.tz_content .t_right .r_table .t_table_list .t_tab tbody .t_tools .t_save
	{
	background-position: -398px -47px;
	background-color: #28b779;
}

.tz_content .t_right .r_table .t_table_list .t_tab tbody .t_tools .t_del
	{
	background-position: -417px -48px;
	background-color: #ff912f;
}

.tz_content .t_right .r_table .t_table_list .t_tab tbody .t_tools a:hover
	{
	background-color: #000;
}

.tz_content .t_right .r_table .t_table_list .t_tab tbody .t_tools {
	display: none;
}

.tz_content .t_right .r_table .t_table_list .t_tab tbody tr:hover .t_tools
	{
	display: block;
}

.tz_content .t_right .r_table .clear {
	clear: both;
}
/*end t_right*/
/*头部导航和消息提示小于1360的时候 用下边的样式*/
@media screen and (max-width:1360px) {
	.tz_content .t_right .r_message .m_list .l_icon {
		display: none;
	}
	.tz_content .t_right .r_message .m_list .l_info {
		text-align: center;
	}
}
/*头部导航和左侧导航小于1024的时候 用下边的样式*/
@media screen and (max-width:1024px) {
	/*start t_right*/
	.tz_content .t_right {
		margin-left: 50px;
	}
	.tz_content .t_right .r_table .t_label ul li .l_a_9 {
		display: none;
	}
	.tz_content .t_right .r_table .t_label ul li .l_a_10 {
		display: none;
	}
	.tz_content .t_right .r_table .t_label ul li .l_a_11 {
		display: none;
	}
	/*end t_right*/
}
/*end content*/
/*tz_map start*/
.tz_map {
	width: 98%;
	height: 600px;
	margin-left:1%;
	margin-bottom:10px;
	border-left:1px solid #e5e5e5;
	border-right:1px solid #e5e5e5;
	position: relative;
}

.tz_map h1 {
	background: #3397e4;
	text-align: center;
	font-weight: 500;
	font-size: 24px;
	line-height: 50px;
	color: #fff;
}

.tz_map .t_con {
	width: 98%;
	height: 550px;
}

.tz_map .t_search {
	position: absolute;
	left: 370px;
	top: 80px;
	box-shadow: 1px 1px 65px 1px #000;
}

.tz_map .t_search .t_text {
	width: 500px;
	height: 36px;
	border: none;
	line-height: 36px;
	padding-left: 10px;
	font-size: 14px;
	font-family: "微软雅黑";
	color: #666;
	outline: none;
}

.tz_map .t_search .t_btn {
	width: 100px;
	height: 36px;
	background: #66F;
	border: none;
	font-size: 12px;
	font-family: "微软雅黑";
	color: #fff;
	font-size: 14px;
	outline: none;
	cursor: pointer;
}

.tz_map .t_search .t_btn:hover {
	background: #60F;
}
/*end tz_map*/

/*menu start*/
.menu {
	width: 100px;
	margin: 0 auto;
	box-shadow: 1px 2px 5px 0px #000;
	background: #fff;
}

.menu ul li {
	list-style: none;
	line-height: 34px;
	padding-left: 10px;
}

.menu ul li:hover {
	background: #EEE;
}
/*end menu*/
</style>
</head>

<body>
	<div class="container">
		<%@include file="/commons/header.jsp"%>
		<!--tz_content start -->
		<div class="tz_content">
			<!--t_right start-->
			<div class="t_right">
				<div class="r_position">
					<i></i>
					<p>
						您当前所在位置：<span>首页</span> > <span>后台中心</span>
					</p>
				</div>
				<div class="r_message">
					<div class="m_list m_1">
						<i class="l_icon"></i>
						<div class="l_info">
							<span>${totalUser}</span>
							<p>所有用户数</p>
						</div>
					</div>
					<div class="m_list m_2">
						<i class="l_icon"></i>
						<div class="l_info">
							<span>32411</span>
							<p>新增用户数</p>
						</div>
					</div>
					<div class="m_list m_3">
						<i class="l_icon"></i>
						<div class="l_info">
							<span>${totalChannel}</span>
							<p>所有栏目数</p>
						</div>
					</div>
					<div class="m_list m_4">
						<i class="l_icon"></i>
						<div class="l_info">
							<span>${totalContent}</span>
							<p>所有文章数</p>
						</div>
					</div>
					<div class="m_list m_5">
						<i class="l_icon"></i>
						<div class="l_info">
							<span>46</span>
							<p>新上传文章数</p>
						</div>
					</div>
					<div class="m_list m_6">
						<i class="l_icon"></i>
						<div class="l_info">
							<span>12304</span>
							<p>所有应用</p>
						</div>
					</div>
					<div class="m_list m_7">
						<i class="l_icon"></i>
						<div class="l_info">
							<span>${totalComment}</span>
							<p>所有评论数</p>
						</div>
					</div>
					<div class="m_list m_8">
						<i class="l_icon"></i>
						<div class="l_info">
							<span>46</span>
							<p>新增评论数</p>
						</div>
					</div>
					<div class="m_list m_9">
						<i class="l_icon"></i>
						<div class="l_info">
							<span>657</span>
							<p>图片数</p>
						</div>
					</div>
					<div class="m_list m_10">
						<i class="l_icon"></i>
						<div class="l_info">
							<span>332</span>
							<p>新上传图片数</p>
						</div>
					</div>
				</div>
			</div>
			<!--tz_map start-->
		<div class="tz_map">
		<h1>后台系统移动定位</h1>
		<div class="t_con" id="t_map"></div>
		<div class="t_search">
			<input type="text" class="t_text" placeholder="请输入你的搜索地址（学校，写字楼或街道）"/><input type="button" value="搜  索" class="t_btn"/>
		</div>
		</div>
		<!--end tz_map-->
		</div>
		<!--end t_right-->

		<!--end tz_content-->
	</div>
	<div class="bottom">Copyright © 2013-2015 www.tanzhouedu.com All
		Rights Reserved 版权所有：湖南潭州教育咨询有限公司 备案号：备13016338号</div>
	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=b245ae8390f2ecdc0e5706241c28fc7b"></script>
	<script type="text/javascript">
		var map, toolBar, mouseTool, contextMenu;
		//初始化地图对象，加载地图
		map = new AMap.Map("t_map", {
			resizeEnable : true,
			width:1400,
		});
		//地图中添加地图操作ToolBar插件、鼠标工具MouseTool插件
		map.plugin([ "AMap.ToolBar", "AMap.MouseTool" ], function() {
			toolBar = new AMap.ToolBar();
			map.addControl(toolBar);
			mouseTool = new AMap.MouseTool(map);
		});

		//自定义右键菜单内容
		var menuContent = document.createElement("div");
		menuContent.innerHTML = "<div class='menu'>" + "		<ul>"
				+ "			<li onclick='zoomMenu(0)'>缩小</li>"
				+ "			<li onclick='zoomMenu(1)'>放大</li>"
				+ "			<li onclick='distanceMeasureMenu()'>测量尺寸</li>"
				+ "			<li onclick='addMarkerMenu()'>添加标记位置</li>" + "		</ul>"
				+ "	</div>";

		//创建右键菜单
		contextMenu = new AMap.ContextMenu({
			isCustom : true,
			content : menuContent
		});//通过content自定义右键菜单内容

		//地图绑定鼠标右击事件——弹出右键菜单
		AMap.event.addListener(map, 'rightclick', function(e) {
			contextMenu.open(map, e.lnglat);
			contextMenuPositon = e.lnglat; //右键菜单位置
		});
		contextMenu.close();
		//右键菜单缩放地图
		function zoomMenu(tag) {
			if (tag === 0) {
				map.zoomOut();
			}
			if (tag === 1) {
				map.zoomIn();
			}
			contextMenu.close();
		}

		//右键菜单距离量测
		function distanceMeasureMenu() {
			mouseTool.rule();
			contextMenu.close();
		}

		//右键菜单添加Marker标记
		function addMarkerMenu() {
			mouseTool.close();
			var marker = new AMap.Marker({
				map : map,
				position : contextMenuPositon, //基点位置
				icon : "http://webapi.amap.com/images/marker_sprite.png", //marker图标，直接传递地址url
				offset : {
					x : -8,
					y : -34
				}
			//相对于基点的位置
			});
			contextMenu.close();
		}

		$(function() {
			$(".t_btn").click(function() {
				var text = $(".t_text").val();
				map.setCity(text);
			});
		});
	</script>
</body>
</html>

