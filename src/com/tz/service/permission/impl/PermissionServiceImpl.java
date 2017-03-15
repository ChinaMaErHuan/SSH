/**
 * tzdesk系统平台
 * CMS
 * com.tz.service.permission.impl
 * PermissionServiceImpl.java
 * 创建人:maerhuan 
 * 时间：2017年2月28日-下午9:19:24 
 * 2017潭州教育公司-版权所有
 */
package com.tz.service.permission.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tz.core.service.BaseServiceImpl;
import com.tz.dao.permission.IPermissionDao;
import com.tz.model.Permission;
import com.tz.service.permission.IPermissionService;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Integer> implements IPermissionService {

	public IPermissionDao getPermissionDao() {
		return (IPermissionDao) super.getBaseDao();
	}

	@Autowired
	public void setPermissionDao(IPermissionDao permissionDao) {
		super.setBaseDao(permissionDao);
	}
	
	@Override
	public List<Object[]> findPermissionByUserId(Integer userId) {
		
		return getPermissionDao().findPermissionByUserId(userId);
		
	}
	/**
	 *  {name : "主题框架",url : "",opid : 1,pid : "root1"},=map.put("name":"主题框架"); 
		{name : "主题框架2",url : "",opid : 2,pid : "root2"}, =map.put("name":"主题框架");
		{name : "主题框架3",url : "",opid : 3,pid : "root3"}, map.put("name":"主题框架");
		{name : "主题框架4",url : "",opid : 4,pid : "root4"}, map.put("name":"主题框架");
		{name : "主题框架5",url : "",opid : 5,pid : "root5"}, map.put("name":"主题框架");
		{name : "主题框架6",url : "",opid : 6,pid : "root6"} map.put("name":"主题框架");
	 * 找到所有的根目录返回Map 为什么返回Map? 数形菜单格式的接收方式就是这样的</br>
	 * com.tz.service.permission.impl </br>
	 * 方法名：findRoot </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月7日-下午4:32:30 </br>
	 * @return
	 * @exception 
	 * @since  1.0.0
	 */
	@Override
	public List<HashMap<String, Object>> findRoot() {
		List<Permission> permissions = getPermissionDao().findRoot();
		List<HashMap<String, Object>> maps = null;
		if(permissions!=null && permissions.size()>0){
			maps = new ArrayList<HashMap<String,Object>>();
			HashMap<String, Object> map = null;
			for(Permission permission:permissions){
				map = new HashMap<String,Object>();
				map.put("name", permission.getName());
				map.put("url", permission.getUrl());
				map.put("opid", permission.getId());
				map.put("pid", permission.getId());
				maps.add(map);
			}
		}
		return maps;
	}
	/**
	 * 
	 * 根据父ID查询所有的可以访问路径</br>
	 * com.tz.service.permission.impl </br>
	 * 方法名：findChildrens </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月7日-下午4:42:40 </br>
	 * @param parentId
	 * @return
	 * @exception 
	 * @since  1.0.0
	 */
	@Override
	public List<HashMap<String, Object>> findChildrens(Integer parentId) {
		List<Permission> permissions = getPermissionDao().findChildrens(parentId);
		List<HashMap<String, Object>> maps = null;
		if(permissions!=null && permissions.size()>0){
			maps = new ArrayList<HashMap<String,Object>>();
			HashMap<String, Object> map = null;
			for(Permission permission:permissions){
				map = new HashMap<String,Object>();
				map.put("name", permission.getName());
				map.put("url", permission.getUrl());
				map.put("opid", permission.getId());
				map.put("pid", permission.getId());
				maps.add(map);
			}
		}
		return maps;
	}

}
