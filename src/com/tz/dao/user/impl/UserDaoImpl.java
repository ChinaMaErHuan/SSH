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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tz.core.dao.BaseDaoImpl;
import com.tz.dao.user.IUserDao;
import com.tz.model.User;
import com.tz.util.TzStringUtils;

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

	
	@Override
	public int countUser() {
		DetachedCriteria detachedCriteria = getCurrentDetachedCriteria();
		detachedCriteria.setProjection(Projections.count("id"));
		Number number = (Number)detachedCriteria.getExecutableCriteria(getSession()).uniqueResult();
		return number==null?0:number.intValue();
	}
}

