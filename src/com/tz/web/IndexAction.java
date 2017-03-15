/**
 * tzdesk系统平台
 * CMS
 * com.tz.web.content
 * IndexAction.java
 * 创建人:maerhuan 
 * 时间：2017年2月24日-下午4:16:20 
 * 2017潭州教育公司-版权所有
 */
package com.tz.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tz.core.action.BaseAction;
@Controller("indexAction")
@Scope("prototype")
public class IndexAction extends BaseAction{
//	@Method(method=TzRequestMethod.POST)
	public String execute(){
		return SUCCESS;
	}
}
