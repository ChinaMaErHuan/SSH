/**
 * tzdesk系统平台
 * CMS
 * com.tz.core
 * Method.java
 * 创建人:maerhuan 
 * 时间：2017年2月25日-下午6:53:15 
 * 2017潭州教育公司-版权所有
 */
package com.tz.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.tz.core.interceptor.TzRequestMethod;
/**
 * 
 * 注解的自定义
 * Method
 * 创建人:maerhuan 
 * 时间：2017年5月13日-下午7:28:31 
 * @version 1.0.0
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface Method {
	public String value() default "";
	public abstract TzRequestMethod[] method() default {};
}
