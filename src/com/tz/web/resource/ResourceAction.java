/**
 * tzdesk系统平台
 * cms
 * com.tz.web.resource
 * ResourceAction.java
 * 创建人:maerhuan 
 * 时间：2017年5月19日-下午6:47:06 
 * 2017潭州教育公司-版权所有
 */
package com.tz.web.resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tz.core.action.BaseAction;

@Controller("resourceAction")
@Scope("prototype")
public class ResourceAction extends BaseAction{
	public String list(){
		return "list";
	}
}
