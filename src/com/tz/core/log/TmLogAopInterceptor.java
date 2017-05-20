/**
 * tzdesk系统平台
 * cms
 * com.tz.core.log
 * TmLogAopInterceptor.java
 * 创建人:maerhuan 
 * 时间：2017年5月13日-下午11:01:26 
 * 2017潭州教育公司-版权所有
 */
package com.tz.core.log;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tz.core.TzLog;
import com.tz.model.Stat;
import com.tz.service.stat.IStatService;

@Aspect
@Component
public class TmLogAopInterceptor {
	@Autowired
	private IStatService statService;
	private String methodName = null;// 方法名
	private String className = null;// 类名
	private long etime = 0;// 耗时
	private StringBuffer buffer = new StringBuffer();
	private String log = null;// 执行结果
	private String model = null;// 模块

	@Around("execution(* com.tz.service.*.impl.*.*(..))")
	public void log(ProceedingJoinPoint point) throws Throwable {
		try {
			// 获取请求对应的类
			Class clz = point.getTarget().getClass();
			// 获取请求的参数
			Object[] params = point.getArgs();
			if (params != null) {
				for (Object object : params) {
					buffer.append(String.valueOf(object) + ",");
				}
			}
			// 类名
			className = clz.getName();
			// 方法名
			methodName = point.getSignature().getName();
			// clz获取对应注解信息
			TzLog tzLog = (TzLog) clz.getAnnotation(TzLog.class);
			if (tzLog != null) {
				model = tzLog.name();
				// 获取该类下面所有的方法
				Method[] methods = clz.getDeclaredMethods();
				for (Method method : methods) {
					// 如果请求的方法名和类里面的方法一直的话，并且加了注解那么就解析
					if (methodName.equalsIgnoreCase(method.getName())) {
						TzLog tzLog2 = (TzLog) method
								.getAnnotation(TzLog.class);
						if (tzLog2 != null) {
							log = tzLog2.desc();
						}
					}
				}
			}

			// 耗时
			long stime = System.currentTimeMillis();
			point.proceed();
			etime = System.currentTimeMillis() - stime;
		} catch (Exception e) {
			e.printStackTrace();
			log = "出现异常了!";
		}

		Stat stat = new Stat();
		stat.setMethod(methodName);
		stat.setClassName(className);
		stat.setParams(buffer.toString());
		stat.setIsDelete(0);
		stat.setLog(log);
		stat.setName(model);
		stat.setTimer(etime);
		statService.save(stat);
	}
}
