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

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface Method {
	public String value() default "";
	public abstract TzRequestMethod[] method() default {};
}
