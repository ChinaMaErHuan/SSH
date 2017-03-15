/**
 * tzdesk系统平台
 * CMS
 * com.tz.core.filter
 * PermissionFilter.java
 * 创建人:maerhuan 
 * 时间：2017年2月28日-下午9:37:32 
 * 2017潭州教育公司-版权所有
 */
package com.tz.core.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.struts2.json.JSONException;
//import org.apache.struts2.json.JSONUtil;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tz.model.User;
import com.tz.util.TzConstanst;
/**
 * 
 * 
 * PermissionFilter 权限拦截器
 * 创建人:maerhuan 
 * 时间：2017年3月1日-下午5:31:26 
 * @version 1.0.0
 *
 */
public class PermissionFilter implements Filter {

	private ServletContext context;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		User user = (User) request.getSession().getAttribute(
				TzConstanst.SESSION_USERKEY);
		//获取spring管理对象的其他方式
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(this.context);
		context.getBean("permissionServiceImpl");
		if (user != null) {
			// 访问的链接地址
			String url = request.getRequestURI();
			// 获取session里面已经缓存的的权限
			@SuppressWarnings("unchecked")
			List<Object[]> permissionsList = (List<Object[]>) request.getSession().getAttribute(TzConstanst.PERMISSION_SESSION_USERKEY);
			
			// 权限比对 是否有权限访问链接地址
			// 如果有权限 就让步 否则跳转到页面 没有权限的页面(提示)
			if (isPermission(url, permissionsList)) {
				chain.doFilter(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error.jsp");
			}

		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	@Override
	public void destroy() {

	}

	// 定义一个方法来判断是否拥有权限
	private boolean isPermission(String url, List<Object[]> objects) {
		if ((objects == null) || (objects != null && objects.size() == 0)) {
			return false;
		}
		boolean flag = false;
		for (Object[] objects2 : objects) {
			// objects2[0] 就是从数据库中查询出来的权限的结果集的第一列 url
			String urlString = String.valueOf(objects2[0]);
			if (url.indexOf(urlString) != -1) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
