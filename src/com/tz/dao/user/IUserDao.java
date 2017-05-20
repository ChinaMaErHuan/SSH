package com.tz.dao.user;

import com.tz.core.dao.IBaseDao;
import com.tz.model.User;

public interface IUserDao extends IBaseDao<User, Integer> {

	/**
	 * 
	 * 根据账号和密码查询用户是否存在
	 * com.tz.dao.user 
	 * 方法名：findExitUser
	 * 创建人：maerhuan 
	 * 时间：2017年2月24日-下午5:08:54 
	 * @param account
	 * @param password
	 * @return User
	 * @exception 
	 * @since  1.0.0
	 */
	 User findExitUser(String account,String password);
	 
	 int countUser();
	 
}
