/**
 * tzdesk系统平台
 * CMS
 * com.tz.dao.permission.impl
 * PermissionDaoImpl.java
 * 创建人:maerhuan 
 * 时间：2017年2月28日-下午8:11:04 
 * 2017潭州教育公司-版权所有
 */
package com.tz.dao.permission.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tz.core.dao.BaseDaoImpl;
import com.tz.dao.permission.IPermissionDao;
import com.tz.model.Permission;
/**
 * 
 * 
 * PermissionDaoImpl
 * 创建人:maerhuan 
 * 时间：2017年3月6日-下午11:04:55 
 * @version 1.0.0
 *
 */
@Repository
@Transactional
public class PermissionDaoImpl extends BaseDaoImpl<Permission,Integer> implements IPermissionDao{
	/**
	 * 
	 * 根据用户找到对应权限信息
	 * com.tz.dao.permission 
	 * 方法名：findPermissionByUserId
	 * 创建人：maerhuan 
	 * 时间：2017年2月28日-下午8:11:54 
	 * @param userId
	 * @return List<Object[]>
	 * @exception 
	 * @since  1.0.0
	 */
	@Override
	public List<Object[]> findPermissionByUserId(Integer userId) {
		String sql =" SELECT DISTINCT t1.url,t1.model,t1.method "+
			" FROM tz_permission t1 "+
			" LEFT JOIN tz_role_permission t2 ON t1.id = t2.permission_id "+
			" LEFT JOIN tz_role t3 ON t3.id = t2.role_id "+
			" LEFT JOIN tz_role_user t4 ON t4.role_id = t3.id "+
			" LEFT JOIN tz_user t5 ON t5.id = t4.user_id "+
			" WHERE t5.id = ?"; 
		@SuppressWarnings("unchecked")
		List<Object[]> list = getSession().createSQLQuery(sql).setInteger(0, userId).list();
		return list;
	}
	/**
	 * 1:查询所有的根目录
	 * 2:查询所根目录下面的子目录(递归) (如果是ajax那么久不需要递归)
	 * 3:把查询根目录和递归的子目录和页面属性插件进行结合
	 * 4:数据结构你弄清楚
	 * 查询所有的根目录</br>
	 * com.tz.dao.permission </br>
	 * 方法名：findRoot </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月6日-下午10:41:06 </br>
	 * @return List<Permission>
	 * @exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> findRoot() {
		String hql = "from Permission where isDelete = 0 and parent.id=1";
		return getSession().createQuery(hql).list();
	}
	/**
	 * 
	 * 根据父ID查询所有的子目录</br>
	 * com.tz.dao.permission </br>
	 * 方法名：findChildrens </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月6日-下午10:42:12 </br>
	 * @param parentId
	 * @return List<Permission>
	 * @exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> findChildrens(Integer parentId) {
		return  getSession().createQuery("from Permission where isDelete =0 and parent.id = "+parentId).list();
	}
	
	
}
