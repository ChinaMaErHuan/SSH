package com.tz.core.tag;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tz.model.Content;
import com.tz.service.content.IContentService;
import com.tz.util.TzStringUtils;

public class ContentBeanTag extends BodyTagSupport {
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1L;
	private String var;// 变量的对象名称
	private Integer cid;

	public int doStartTag() throws JspException {
		ServletContext context = this.pageContext.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		IContentService contentService = (IContentService) ctx.getBean("contentServiceImpl");
		Content content = contentService.get(cid);
		if (TzStringUtils.isEmpty(var))var = "content";
		pageContext.setAttribute(var, content);
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public void setVar(String var) {
		this.var = var;
	}
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}
}
