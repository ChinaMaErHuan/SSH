/**
 * tzdesk系统平台
 * cms
 * com.tz.web.comment
 * CommentAction.java
 * 创建人:maerhuan 
 * 时间：2017年4月6日-下午11:08:41 
 * 2017潭州教育公司-版权所有
 */
package com.tz.web.comment;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tz.core.action.BaseAction;

@Controller("commentAction")
@Scope("prototype")
public class CommentAction extends BaseAction {
	public String list(){
		return LIST;
	}
}
