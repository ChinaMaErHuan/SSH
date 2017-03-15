/**
 * tzdesk系统平台
 * CMS
 * com.tz.service.user.impl
 * IUserServiceImpl.java
 * 创建人:maerhuan 
 * 时间：2017年2月24日-下午7:38:36 
 * 2017潭州教育公司-版权所有
 */
package com.tz.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tz.dao.user.IUserDao;
import com.tz.model.User;
import com.tz.service.user.IUserService;

@Service
public class IUserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public User findExitUser(String account, String password) {
		return userDao.findExitUser(account, password);
	}

}
