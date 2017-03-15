/**
 * tzdesk系统平台
 * cms
 * com.tz.service
 * TestPermissionImpl.java
 * 创建人:maerhuan 
 * 时间：2017年3月12日-下午8:38:20 
 * 2017潭州教育公司-版权所有
 */
package com.tz.service;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tz.service.permission.IPermissionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestPermissionServiceImpl {
	@Autowired
	private IPermissionService permissionService;
	
	@Test
	public void handle(){
		//查询所有的跟权限
		List<HashMap<String, Object>> list = permissionService.findRoot();
		for (HashMap<String, Object> hashMap : list) {
			System.out.println(hashMap);
		}
	}
	@Test
	public void handle2(){
		List<Object[]> list = permissionService.findPermissionByUserId(1);
		for (Object[] objects : list) {
			System.out.println(objects[0]);
		}
	}
	
	@Test
	public void handle3(){
		List<HashMap<String, Object>> list =permissionService.findChildrens(1);
		for (HashMap<String, Object> hashMap : list) {
			System.out.println(hashMap);
		}
	}
	
}
