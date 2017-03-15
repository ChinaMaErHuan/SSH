/**
 * tzdesk系统平台
 * CMS
 * com.tz.dao
 * TestUserDaoImpl.java
 * 创建人:maerhuan 
 * 时间：2017年2月24日-下午5:31:42 
 * 2017潭州教育公司-版权所有
 */
package com.tz.dao;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tz.dao.user.IUserDao;
import com.tz.model.User;
import com.tz.util.TzStringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestUserDaoImpl {
	@Autowired
	private IUserDao userDao;

	@Test
	public void handle() throws JSONException {
		String password = TzStringUtils.md5Base64("123456");
		User user = userDao.findExitUser("keke", password);
		System.out.println(JSONUtil.serialize(user));
	}
}
