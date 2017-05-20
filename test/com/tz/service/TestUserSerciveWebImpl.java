/**
 * tzdesk系统平台
 * cms
 * com.tz.service
 * TestUserSerciveWebImpl.java
 * 创建人:maerhuan 
 * 时间：2017年5月14日-下午8:55:44 
 * 2017潭州教育公司-版权所有
 */
package com.tz.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tz.model.User;
import com.tz.service.ws.IUserServiceWeb;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestUserSerciveWebImpl {
	@Autowired
	private IUserServiceWeb userServiceWeb;

	@Test
	public void handle() {
		System.out.println(userServiceWeb);
		try {
			List<User> users = userServiceWeb.findUsers();
			for (User user : users) {
				System.out.println("========" + user.getUsername());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		User user = new User();
//		user.setAccount("keke");
//		user.setUsername("kekekekeke");
//		user.setPassword("12324646");
//		userServiceWeb.saveUser(user);
	}
}
