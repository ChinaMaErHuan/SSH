/**
 * tzdesk系统平台
 * CMS
 * com.tz.dao
 * TestPermissionDaoImpl.java
 * 创建人:maerhuan 
 * 时间：2017年2月28日-下午8:30:25 
 */
package com.tz.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tz.dao.permission.IPermissionDao;
import com.tz.model.Permission;
import com.tz.service.permission.IPermissionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestPermissionDaoImpl {
	
	@Autowired
	private IPermissionDao permissionDao;
	@Autowired
	private IPermissionService permissionService;
	@Test
	public void findPermissionByUserIdTest(){
		List<Object[]> list = permissionDao.findPermissionByUserId(1);
		for (Object[] objects : list) {
			for (Object object : objects) {
				System.out.println(object.toString());
			}
		}
	}
	@Test
	public void findRootTest() throws JSONException {
		List<Permission> list = permissionDao.findRoot();
		for (Permission permission : list) {
			System.out.println(JSONUtil.serialize(permission));
		}
	}
	
	@Test
	public void findChildrensTest() throws JSONException{
		List<Permission> list = permissionDao.findChildrens(3);
		for (Permission permission : list) {
			System.out.println(JSONUtil.serialize(permission));
		}
	}
	
	@Test
	public void findRootTestService() {
		List<HashMap<String, Object>> list = permissionService.findRoot();
		for (HashMap<String, Object> hashMap : list) {
			System.out.println(hashMap);
		}
	}
	
	@Test
	public void findChildrensTestService() {
		List<HashMap<String, Object>> list = permissionService.findChildrens(3);
		for (HashMap<String, Object> hashMap : list) {
			System.out.println(hashMap);
		}
	}
	
	
}
