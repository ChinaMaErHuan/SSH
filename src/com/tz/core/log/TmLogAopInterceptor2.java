package com.tz.core.log;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tz.core.TzLog;
import com.tz.model.Stat;
import com.tz.service.stat.IStatService;


@Component
public class TmLogAopInterceptor2 {
	
	@Autowired
	private IStatService statService;
	private String methodName = null;
	private String className = null;
	private long etime = 0;
	private StringBuffer buffer = new StringBuffer();
	private String log = null;
	private String model = null;
	
//	@Before
//	@After
//	@AfterReturning
//	@AfterThrowing 
//	@Around
	//execution([访问修饰符]<返回类型><方法名>(<参数列表>)[异常])
	public Object log(ProceedingJoinPoint point) throws Throwable{
		Object obj =null;
		try {
			//获取请求对应的类
			Class clz = point.getTarget().getClass();
			//获取请求的参数
			Object[] params = point.getArgs();
			if(params!=null){
				for (Object object : params) {
					buffer.append(String.valueOf(object)+",");
				}
			}
			//类名
			className = clz.getName();
			//方法名
			methodName = point.getSignature().getName();
			//clz获取对应注解信息
			TzLog tzLog = (TzLog) clz.getAnnotation(TzLog.class);
			if(tzLog!=null){
				model = tzLog.name();
				//获取该类下面所有的方法
				Method[] methods = clz.getDeclaredMethods();
				for (Method method : methods) {
					//如果请求的方法名和类里面的方法一直的话，并且加了注解那么就解析
					if(methodName.equalsIgnoreCase(method.getName())){
						TzLog tzLog2 = (TzLog) method.getAnnotation(TzLog.class);
						if(tzLog2!=null){
							log = tzLog2.desc();
						}
					}
				}
			}
			
			//耗时
			long stime = System.currentTimeMillis();
			obj= point.proceed();
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
		methodName = null;
		className = null;
		etime = 0;
		log = null;
		model = null;
		return obj;
	}
}
