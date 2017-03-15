/**
 * tzdesk系统平台
 * CMS
 * com.tz.core.tag
 * TzPermissionTag.java
 * 创建人:maerhuan 
 * 时间：2017年3月2日-下午5:03:18 
 * 2017潭州教育公司-版权所有
 */
package com.tz.core.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.tz.util.TzConstanst;
/**
 * 
 * 权限标签
 * TzPermissionTag
 * 创建人:maerhuan 
 * 时间：2017年3月2日-下午6:17:54 
 * @version 1.0.0
 *
 */
public class TzPermissionTag extends BodyTagSupport{

	
	private static final long serialVersionUID = 1L;
	
	private String method;
	private String model;
	private List<Object[]> permissionList = null;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doInitBody() throws JspException {
		permissionList = (List<Object[]>) this.pageContext.getSession().getAttribute(TzConstanst.PERMISSION_SESSION_USERKEY);
	}
	
	
	@Override
	public int doAfterBody() throws JspException {
		//标签体内容
		String bodyContent = this.getBodyContent().getString();
		if(bodyContent!=null){
			JspWriter out = this.getPreviousOut();
			if (isPersmission()) {
				try {
					out.print(bodyContent);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				try {
					out.print("");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		//return SKIP_PAGE;//忽略标签下面的内容
		return EVAL_PAGE; //继续执行标签下面的内容
	}
	
	
	public void setMethod(String method) {
		this.method = method;
	}

	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * 
	 * 判断用户是否拥有权限
	 * com.tz.core.tag 
	 * 方法名：isPersmission
	 * 创建人：maerhuan 
	 * 时间：2017年3月2日-下午5:20:49 
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	private boolean isPersmission(){
		try {
			if((permissionList==null) || (permissionList!=null && permissionList.size()==0))return false;
			boolean flag = false;
			for (Object[] objects2 : permissionList) {
				String cmodel = String.valueOf(objects2[1]);// /index
				String cmethod = String.valueOf(objects2[2]);// /index
				if(cmodel.equals(model) && cmethod.equals(method)){
					flag= true;
					break;
				}
			}
			return flag;
		} catch (Exception e) {
			return false;
		}
	}
	

	
	
}
