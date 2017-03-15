/**
 * tzdesk系统平台
 * CMS
 * com.tz.dao.user.impl
 * UserDaoImpl.java
 * 创建人:maerhuan 
 * 时间：2017年2月24日-下午5:09:53 
 * 2017潭州教育公司-版权所有
 */
package com.tz.dao.user.impl;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.tz.core.dao.BaseDaoImpl;
import com.tz.dao.user.IUserDao;
import com.tz.model.User;

/**
 * 
 * 
 * UserDaoImpl 创建人:maerhuan 时间：2017年2月24日-下午5:30:55
 * 
 * @version 1.0.0
 * 
 */
@Repository
@Transactional
public class UserDaoImpl extends BaseDaoImpl<User,Integer> implements IUserDao {

	@Override
	public User findExitUser(String account, String password) {
		String hql = "from User where account = ? and password = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, account);
		query.setString(1, password);
		User user = (User) query.uniqueResult();
		return user;
	}

}
