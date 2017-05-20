package com.tz.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * 
 * TzLog
 * 创建人:maerhuan 
 * 时间：2017年5月13日-下午7:28:26 
 * @version 1.0.0
 *
 */
@Documented
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD,ElementType.TYPE})
public @interface TzLog {
	public String model() default "";//模块
	public String name() default "";//模块的名称
	public String desc() default "";//模块的描述
	public String author() default "";//作者
	public String time() default "";//创建时间
}
