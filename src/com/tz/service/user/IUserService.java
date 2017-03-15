/**
 * tzdesk系统平台
 * CMS
 * com.tz.service.user
 * IUserService.java
 * 创建人:maerhuan 
 * 时间：2017年2月24日-下午7:37:54 
 * 2017潭州教育公司-版权所有
 */
package com.tz.service.user;

import com.tz.model.User;

public interface IUserService {
	public User findExitUser(String account, String password);
}
