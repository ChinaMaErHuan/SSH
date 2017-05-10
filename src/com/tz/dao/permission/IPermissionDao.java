/**
 * tzdesk系统平台
 * CMS
 * com.tz.dao.permission
 * IPermissionDao.java
 * 创建人:maerhuan 
 * 时间：2017年2月28日-下午8:10:27 
 * 2017潭州教育公司-版权所有
 */
package com.tz.dao.permission;

import java.util.List;

import com.tz.core.dao.IBaseDao;
import com.tz.model.Permission;

public interface IPermissionDao extends IBaseDao<Permission, Integer> {
	/**
	 * 
	 * 根据用户找到角色以及对应的所有权限信息
	 * com.tz.dao.permission 
	 * 方法名：findPermissionByUserId
	 * 创建人：maerhuan 
	 * 时间：2017年2月28日-下午8:11:54 
	 * @param userId
	 * @return List<Object[]>
	 * @exception 
	 * @since  1.0.0
	 */
	 List<Object[]> findPermissionByUserId(Integer userId);
	/**
	 * 
	 * 查询所有的根目录</br>
	 * com.tz.dao.permission </br>
	 * 方法名：findRoot </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月6日-下午10:41:06 </br>
	 * @return List<Permission>
	 * @exception 
	 * @since  1.0.0
	 */
	 List<Permission> findRoot();
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
	 List<Permission> findChildrens(Integer parentId);
}
