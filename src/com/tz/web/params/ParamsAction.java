/**
 * tzdesk系统平台
 * cms
 * com.tz.web.params
 * ParamsAction.java
 * 创建人:maerhuan 
 * 时间：2017年5月19日-下午6:46:56 
 * 2017潭州教育公司-版权所有
 */
package com.tz.web.params;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tz.core.action.BaseAction;
@Controller("paramsAction")
@Scope("prototype")
public class ParamsAction extends BaseAction {
	
	public String list(){
		return "list";
	}
}
