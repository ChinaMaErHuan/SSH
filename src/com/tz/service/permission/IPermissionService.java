/**
 * tzdesk系统平台
 * CMS
 * com.tz.service.permission
 * IPermissionService.java
 * 创建人:maerhuan 
 * 时间：2017年2月28日-下午9:18:50 
 * 2017潭州教育公司-版权所有
 */
package com.tz.service.permission;

import java.util.HashMap;
import java.util.List;

import com.tz.core.service.IBaseService;
import com.tz.model.Permission;

public interface IPermissionService extends IBaseService<Permission, Integer> {
	/**
	 * 
	 * 根据用户找到对应权限信息</br> com.tz.service.permission </br>
	 * 方法名：findPermissionByUserId </br> 创建人：maerhuan </br>
	 * 时间：2017年3月7日-上午12:40:17 </br>
	 * 
	 * @param userId
	 * @return List<Object[]>
	 * @exception
	 * @since 1.0.0
	 */
	public List<Object[]> findPermissionByUserId(Integer userId);
	/**
	 * 
	 * 查询所有的根权限</br>
	 * com.tz.service.permission </br>
	 * 方法名：findRoot </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月7日-下午12:33:52 </br>
	 * @return List<HashMap<String,Object>>
	 * @exception 
	 * @since  1.0.0
	 */
	public  List<HashMap<String, Object>> findRoot();
	/**
	 * 
	 * 根据父ID查询所有的可以访问的目录</br>
	 * com.tz.service.permission </br>
	 * 方法名：findChildrens </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月7日-下午12:40:09 </br>
	 * @param parentId
	 * @return List<HashMap<String,Object>>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<HashMap<String, Object>> findChildrens(Integer parentId);
}
