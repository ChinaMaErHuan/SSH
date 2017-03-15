package com.tz.core.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tz.util.TzStringUtils;

/**
 * 
 * 
 * TzMethodInterceptor
 * 创建人:maerhuan 
 * 时间：2017年2月27日-下午2:37:42 
 * @version 1.0.0
 *
 */
@Component("methodInterceptor")
public class TzMethodInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	public String intercept(ActionInvocation invocation) throws Exception {
		request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		boolean flag = isMethod(invocation);
		if(flag){
			return invocation.invoke();
		}else{
			return "error";
		}
	}
	
	
	public boolean isMethod(ActionInvocation invocation){
		boolean flag = true;
		try {
			//获取当前请求的Action对象
//			Object action = invocation.getProxy().getAction();
			//获取当前请求类的class对象
			@SuppressWarnings("rawtypes")
			Class clz = invocation.getProxy().getAction().getClass();
			//获取当前请求的方式
			String postType = request.getMethod();
			
			//获取当前请求的方法
			String methodName = invocation.getProxy().getMethod();
			//根据返回获取当前方法是否存在
			
			Method method = clz.getDeclaredMethod(methodName, null);
			if(method!=null){//如果method不为null
				//获取当前方法上是否加有自己定义的注解tzMethod
				com.tz.core.Method tzMethod = method.getAnnotation(com.tz.core.Method.class);
				//如果有自己定义的注解tzMethod
				if(tzMethod!=null){
					//获取你自定义的value的值
					String value = tzMethod.value();
					
					//如果不为null那么并且如果你写入的请求方式和当前请求的方式不一致，返回false
					if(TzStringUtils.isNotEmpty(value) && !value.equalsIgnoreCase(postType)){
						flag = false;
					}else{
						//如果为null,那么根据枚举方式在进行一次判断，
						TzRequestMethod[] requestMethod = tzMethod.method();
						if(!requestMethod[0].toString().equalsIgnoreCase(postType)){
							flag = false;
						}
					}
				}
			}
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			flag = false;
		}
		
		return flag;
	}

}
