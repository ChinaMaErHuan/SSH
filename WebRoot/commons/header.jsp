<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="dh clearfix">
			<div class="dh_left fl">
				<img src="${basePath}/images/logo.png" alt="" />
			</div>
			<div class="dh_list fl">
				<ul>
					<tz:if test="${tz:indexOf(pageContext.request.requestURI,'index')!=-1}">
						<li class="select"><a href="javascript:void(0)">网站首页</a></li>
						<tz:else/>
						 <li><a href="${basePath}/index">网站首页</a></li>
					</tz:if>
					
				 	<tz:if test="${tz:indexOf(pageContext.request.requestURI,'content/list')!=-1}">
			            <tz:permission method="list" model="content"><li class="select"><a href="javascript:void(0);">内容管理</a></li></tz:permission>
	           			<tz:else/>
	           			<tz:permission method="list" model="content"><li><a href="${basePath}/admin/content/list">内容管理</a></li></tz:permission>
           			</tz:if>
           		
	           		<tz:if test="${tz:indexOf(pageContext.request.requestURI,'channel/list')!=-1}">
			            <tz:permission method="list" model="channel"><li class="select"><a href="javascript:void(0);">栏目管理</a></li></tz:permission>
	           		<tz:else/>
	           			<tz:permission method="list" model="channel"><li><a href="${basePath}/admin/channel/list">栏目管理</a></li></tz:permission>
	           		</tz:if>
	           		
	           		<tz:if test="${tz:indexOf(pageContext.request.requestURI,'comment/list')!=-1}">
			            <tz:permission method="list" model="comment"><li class="select"><a href="javascript:void(0);">互动管理</a></li></tz:permission>
	           		<tz:else/>
	           			<tz:permission method="list" model="comment"><li><a href="${basePath}/admin/comment/list">互动管理</a></li></tz:permission>
	           		</tz:if>
	           		
	           		<tz:if test="${tz:indexOf(pageContext.request.requestURI,'resource/list')!=-1}">
			            <tz:permission method="list" model="resource"><li class="select"><a href="javascript:void(0);">资源管理</a></li></tz:permission>
	           		<tz:else/>
	           			<tz:permission method="list" model="resource"><li><a href="${basePath}/admin/resource/list">资源管理</a></li></tz:permission>
	           		</tz:if>
	          
	           		<tz:if test="${tz:indexOf(pageContext.request.requestURI,'stat/list')!=-1}">
			            <tz:permission method="list" model="stat"><li class="select"><a href="javascript:void(0);">统计管理</a></li></tz:permission>
	           		<tz:else/>
	           			<tz:permission method="list" model="stat"><li><a href="${basePath}/admin/stat/list">统计管理</a></li></tz:permission>
	           		</tz:if>
	           		
	           		<tz:if test="${tz:indexOf(pageContext.request.requestURI,'params/list')!=-1}">
			            <tz:permission method="list" model="params"><li class="select"><a href="javascript:void(0);">参数管理</a></li></tz:permission>
	           		<tz:else/>
	           			<tz:permission method="list" model="params"><li><a href="${basePath}/admin/params/list">参数管理</a></li></tz:permission>
	           		</tz:if>
				</ul>
			</div>
			<a href="${basePath}/logout" class="fr">退出</a>
		</div>