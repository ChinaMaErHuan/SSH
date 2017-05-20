<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Keywords" content="">
<meta name="Description" content="">
<title>登录页面</title>
<%@include file="/commons/public.jsp"%>
<style type="text/css">
body {
	background:url(images/bg.jpg) top center no-repeat;
	color: #2f3437;
	font-size: 12px;
}

#login_div {
	width: 100%;
	height: 100%;
}

#login_div .login_bt {
	width: 100%;
	height: 30%;
	position: relative;
}

#login_div .login_logo {
	width: 450px;
	height: 65px;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-left: -225px;
	margin-top: -32.5px;
}

#login_div .login_nr {
	width: 100%;
	height: 300px;
	background: rgba(47, 52, 55, 0.8);
	filter: progid:DXImageTransform.Microsoft.gradient(startcolorstr=#7F2f3437,
		endcolorstr=#7F2f3437);
}

#login_div .login_zi {
	width: 316px;
	margin: 0 auto;
}

#login_div .tishi {
	width: 100%;
	height: 55px;
	line-height: 55px;
	color: #ffffff;
}

#login_div .tishi span {
	display: block;
	width: 16px;
	height: 16px;
	margin-top: 20px;
	float: left;
}

#login_div .tishi p {
	float: left;
	padding-left: 5px;
}

#login_div .icon_close {
	background: url(images/icon_close.png) no-repeat;
}

#login_div .icon_ok {
	background: url(images/icon_ok.png) no-repeat;
}

#login_div .box {
	width: 100%;
	height: 44px;
	margin-bottom: 8px;
	background: #d3d5dc;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
}

#login_div .user_box {
	width: 252px;
	height: 44px;
	line-height: 44px;
	padding-left: 12px;
	border: 0px;
	background: none;
	float: left;
}

#login_div .user_pic {
	width: 52px;
	height: 44px;
	background: url(images/icon_username.png) no-repeat center;
	float: left;
}

#login_div .password_pic {
	width: 52px;
	height: 44px;
	background: url(images/icon_key.png) no-repeat center;
	float: left;
}

#login_div .yanzheng,#login_div .yz_box {
	width: 62%;
	line-height: 44px;
	background: none;
	border: 0px;
	float: left;
}

#login_div .yz_change,#login_div .yz_pic {
	width: 38%;
	line-height: 44px;
	text-align: center;
	float: right;
}

#login_div .yz_change a {
	color: #ffffff;
}

#login_div .yz_change span {
	color: #28b779;
}

#login_div .submit_btn {
	width: 100%;
	height: 44px;
	text-align: center;
	color: #ffffff;
	font-size: 18px;
	border: 0px;
	background: #28b779;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	cursor: pointer;
}
</style>
</head>

<body>
	<div id="login_div">
		<div class="login_bt">
			<div class="login_logo">
				<img src="${basePath}/images/login_logo.png" width="450" height="65" />
			</div>
		</div>
		<div class="login_nr animated bounceIn">
			<div class="login_zi">
				<div class="tishi" style="display:none;">
					<span class="icon_close"></span>
					<p>您输入的用户名或密码不正确，请重新输入！</p>
				</div>
				<div class="tishi">
					<span class="icon_ok"></span>
					<p>输入正确，恭喜您登录成功！</p>
				</div>
				
				<div class="box animated bounceInLeft">
					<input name="" type="text" id="account" placeholder="请输入账号"
						maxlength="50" class="user_box" />
					<div class="user_pic" ></div>
				</div>
				<div class="box animated bounceInRight">
					<input name="" type="password" id="password" placeholder="请输入密码"
						maxlength="50" class="user_box" />
					<div class="password_pic"></div>
				</div>
				<div class="yanzheng ">
					<div class="box animated bounceIn">
						<input name="" type="text" id="checkcode" class="yz_box" placeholder="请输入验证码" style="padding-left:12px;text-transform:lowercase"/>
						<div class="yz_pic" style = 'width:59px;height:30px;margin-top:7px;margin-right:3px;text-align:center;'>
							<img alt="验证码" id ="checkImg" src="${basePath}/checkImg" width = '59' height = '30' style = 'display:block;vertical-align: middle;'>
						</div>
					</div>
				</div>
				<div class="yz_change">
					<a href="#" onclick="changeImage()">看不清，<span>换一张！</span></a>
				</div>
				<div class="clearfix"></div>
				<input name="" onclick="tz_login(this)" type="button"
					class="submit_btn animated bounceIn" value="登 录" />
			</div>
		</div>
	</div>
	<script type="text/javascript">
		//enter登录
		$(function() {
			$(document).keydown(function(e) {
				if (e.keyCode === 13) {
					$(".submit_btn").trigger("click");
				}
			});
		});
		function changeImage(){
			var imgDom=document.getElementById("checkImg");  
		    imgDom.src="${pageContext.request.contextPath}/checkImg?"+new Date().getTime();
		};
		/*登陆*/
		function tz_login(obj) {
			//获取用户和密码
			var account = $("#account").val();
			var password = $("#password").val();//等价于document.getElementById("password").value;
			var checkcode = $("#checkcode").val();
			if(isEmpty(account)){//isEmpty()函数在工具类中 sgutil中
				loading("请输入用户账号...",3);
				$("#account").focus();
				return ;
			}
			if(isEmpty(password)){
				loading("请输入密码...",3);
				$("#password").focus();
				return ;
			}
			if(isEmpty(checkcode)){
				loading("请输入验证码...",3);
				$("#checkcode").focus();
				return ;
			}
			//去事件
			$(obj).val("登陆中...").removeAttr("onclick");
			var json = {
				type : "post",//请求方式
				url : basePath + "/json/logined",//请求地址
				data : {"account": account,"password":password,"checkcode":checkcode,
				},//传递给服务器的参数
				error : function() {//如果出错了，将事件重新绑定
					$(obj).val("登陆").attr("onclick", "tz_login(this)");
				},
				success : function(data) {//返回成功执行回调函数.因为out.print是输入带有空格的。一定去空格
					$(obj).val("登陆").attr("onclick", "tz_login(this)");
					//data = data.trim();//去掉前后空格
					alert(data.result);
					if (data.result == "success") {
						//浏览器地址的追踪
						window.location.href = basePath + "/index";
					} else if (data.result == "fail") {
						loading("登陆失败，用户或者密码错误!!!",4);
						$("#password").focus().val("");//清空密码    
					}else if (data.result == "checkcodeFail") {
						loading("验证码错误!!!",4);
						$("#checkcode").focus().val("");//清空   
					}else if (data.result == "codeNull") {
						loading("请输入验证码!!!",4);
						$("#checkcode").focus();//清空   
					}
				}
			};
			$.ajax(json);
		};
	</script>
</body>
</html>