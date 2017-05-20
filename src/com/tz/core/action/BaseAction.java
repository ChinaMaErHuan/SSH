/**
 * tzdesk系统平台
 * CMS
 * com.tz.core.action
 * BaseAction.java
 * 创建人:maerhuan 
 * 时间：2017年2月24日-下午3:25:19 
 * 2017潭州教育公司-版权所有
 */
package com.tz.core.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONUtil;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionContext;
import com.tz.core.dao.TzParams;
import com.tz.model.User;
import com.tz.util.TzConstanst;
import com.tz.util.TzPageInfo;

public class BaseAction {

	public static final String AJAX_SUCCESS = "ajaxSuccess";
	public static final String LIST = "list";
	public static final String ADD = "add";
	public static final String EDIT = "edit";
	public static final String DELETE = "delete";
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	public static final String ERROR = "error";
	public static final String CHECKCODEFAILE = "checkcodeFail";
	
	public Integer id;
	
	//分页
	public TzPageInfo page =  new TzPageInfo();
	//参数
	public TzParams params = new TzParams();
	
	public String result;
	
	@JSON(serialize=false)
	public User getUser(){
		User user = (User)ActionContext.getContext().getSession().get(TzConstanst.SESSION_USERKEY);
		return user;
	}
	
	public void outObject(Object obj){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(JSONUtil.serialize(obj));
			pw.flush();
			pw.close();
		} catch (Exception e) {
			System.out.println("出现隐藏了" + e);
		}
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TzPageInfo getPage() {
		return page;
	}

	public void setPage(TzPageInfo page) {
		this.page = page;
	}

	public TzParams getParams() {
		return params;
	}

	public void setParams(TzParams params) {
		this.params = params;
	}
	
	
}
