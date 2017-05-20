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

import com.tz.core.service.IBaseService;
import com.tz.model.User;

public interface IUserService extends IBaseService<User, Integer>{
	/**
	 * 
	 * 根据账号密码查询用户</br>
	 * com.tz.service.user </br>
	 * 方法名：findExitUser </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月23日-下午5:41:24 </br>
	 * @param account
	 * @param password
	 * @return User
	 * @exception 
	 * @since  1.0.0
	 */
	 User findExitUser(String account, String password);
	 int countUser();
}
