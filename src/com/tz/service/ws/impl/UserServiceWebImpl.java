/**
 * Tm系统平台
 * TzCms
 * com.tz.service.ws.impl
 * UserServiceWebImpl.java
 * 创建人:xuchengfei 
 * 时间：2015年5月28日-下午9:42:41 
 * 2015Tm公司-版权所有
 */
package com.tz.service.ws.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tz.model.User;
import com.tz.service.user.IUserService;
import com.tz.service.ws.IUserServiceWeb;

/**
 * 
 * UserServiceWebImpl
 *  创建人:xuchengfei
 *   时间：2015年5月28日-下午9:42:41
 * 
 * @version 1.0.0
 * 
 */
@Service
public class UserServiceWebImpl implements IUserServiceWeb {

	@Autowired
	private IUserService userService;

	@Override
	public List<User> findUsers() {
		List<User> users = userService.find("from User");
		return users;
	}

	@Override
	public boolean saveUser(User user) {
		user = userService.save(user);
		return user == null ? false : true;
	}

}
