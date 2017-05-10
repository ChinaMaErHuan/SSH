package com.tz.core.tag;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tz.model.Channel;
import com.tz.service.channel.IChannelService;
import com.tz.util.TzStringUtils;


public class TzChannelListTag extends BodyTagSupport{
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1L;
	private String var;//变量的对象名称
	private Integer pid;
	private Iterator<Channel> iterator = null;
	private Integer index;
	
	public int doStartTag() throws JspException {
		ServletContext context = this.pageContext.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		IChannelService channelService = (IChannelService) ctx.getBean("kekechannelservice");
		if (TzStringUtils.isEmpty(var))var = "channel";
		List<Channel> channels = channelService.find("from Channel where isDelete = 0 and parent.id is null order by sort asc");
		if (channels != null && channels.size() > 0) {
			index = 0;
			pageContext.setAttribute(var + "_size", channels.size());
			pageContext.setAttribute(var + "_index", index);
			iterator = channels.iterator();
			pageContext.setAttribute(var, iterator.next());
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}
	
	public int doAfterBody() throws JspException {
		if (iterator.hasNext()) {
			index++;
			pageContext.setAttribute(var, iterator.next());
			pageContext.setAttribute(var + "_index", index);
			return EVAL_BODY_AGAIN;
		}
		return SKIP_BODY;
	}
	

	public int doEndTag() throws JspException {
		iterator = null;
		index = 0;
		return EVAL_PAGE;
	}


	public void setVar(String var) {
		this.var = var;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
}
