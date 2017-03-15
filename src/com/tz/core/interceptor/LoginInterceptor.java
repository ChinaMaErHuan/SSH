/**
 * tzdesk系统平台
 * CMS
 * com.tz.core.interceptor
 * LoginInterceptor.java
 * 创建人:maerhuan 
 * 时间：2017年2月25日-下午6:31:25 
 * 2017潭州教育公司-版权所有
 */
package com.tz.core.interceptor;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tz.model.User;
import com.tz.util.TzConstanst;

@Component("loginInterceptor")
public class LoginInterceptor extends AbstractInterceptor {

	
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		System.out.println("当前执行action是:"+invocation.getAction().getClass().getName());
		System.out.println("当前执行的方法是:"+invocation.getProxy().getMethod());
		//获取上下文
		ActionContext context = invocation.getInvocationContext();
		//获取session里面的user
		User user = (User) context.getSession().get(TzConstanst.SESSION_USERKEY);
		if(user!=null){
			return invocation.invoke();
		}else{
			
			return "toLogin";
		}
	}

}
