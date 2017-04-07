/**
 * tzdesk系统平台
 * cms
 * com.tz.web.stat
 * StatAction.java
 * 创建人:maerhuan 
 * 时间：2017年4月6日-下午11:08:53 
 * 2017潭州教育公司-版权所有
 */
package com.tz.web.stat;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tz.core.action.BaseAction;

@Controller("statAction")
@Scope("prototype")
public class StatAction extends BaseAction {
	public String list(){
		return LIST;
	}
}
