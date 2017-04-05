/**
 * tzdesk系统平台
 * CMS
 * com.tz.web.content
 * LoginAction.java
 * 创建人:maerhuan 
 * 时间：2017年2月24日-上午11:40:40 
 * 2017潭州教育公司-版权所有
 */
package com.tz.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.tz.core.Method;
import com.tz.core.action.BaseAction;
import com.tz.core.interceptor.TzRequestMethod;
import com.tz.model.User;
import com.tz.service.permission.IPermissionService;
import com.tz.service.user.IUserService;
import com.tz.util.TzConstanst;
import com.tz.util.TzStringUtils;

/**
 * 
 * 
 * LoginAction 创建人:maerhuan 时间：2017年2月24日-上午11:40:44
 * 
 * @version 1.0.0
 * 
 */
@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction {
	@Autowired
	private IUserService userService;
	@Autowired
	private IPermissionService permissionService;
	private String account;
	private String password;

	/**
	 * 
	 * 跳转到登陆页面 com.tz.web.content 方法名：login 创建人：maerhuan 时间：2017年2月24日-下午8:36:42
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public String login() {
		User user = (User) ActionContext.getContext().getSession()
				.get(TzConstanst.SESSION_USERKEY);
		if (user == null) {
			return "login";
		} else {
			return "toIndex";
		}
	}

	/**
	 * 
	 * 登陆方法 com.tz.web.content 方法名：login 创建人：maerhuan 时间：2017年2月24日-下午3:47:18
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	@Method(method=TzRequestMethod.POST)
	public String logined() {
		if (TzStringUtils.isNotEmpty(account)
				&& TzStringUtils.isNotEmpty(password)) {
			password = TzStringUtils.md5Base64(password);
			User user = userService.findExitUser(account, password);
			if (user != null) {
				ActionContext.getContext().getSession()
						.put(TzConstanst.SESSION_USERKEY, user);
				result = SUCCESS;
				// 获取用户所有的权限
				List<Object[]> permissionsList = permissionService
						.findPermissionByUserId(user.getId());
				// 将权限缓存到session中
				ActionContext
						.getContext()
						.getSession()
						.put(TzConstanst.PERMISSION_SESSION_USERKEY,
								permissionsList);
			} else {
				result = FAIL;
			}
		} else {
			result = "empty";
		}
		account = null;
		password = null;// 密码没有必要返回给浏览器
		return AJAX_SUCCESS;
	}

	/**
	 * 
	 * 退出登录的方法 com.tz.web.content 方法名：logout 创建人：maerhuan 时间：2017年3月1日-下午7:05:19
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public String logout() {
		ActionContext.getContext().getSession().clear();
		return "toLogin";
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
