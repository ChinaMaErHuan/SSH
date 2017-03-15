/**
 * tzdesk系统平台
 * TzSSH
 * com.tz.web
 * TestApplicationContext.java
 * 创建人:maerhuan 
 * 时间：2017年2月21日-下午5:54:51 
 * 2017潭州教育公司-版权所有
 */
package com.tz.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tz.dao.content.IContentDao;
import com.tz.model.Content;
import com.tz.web.content.ContentAction;

public class TestApplicationContext {
	@Test
	public void handle() throws SQLException{
//		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
//    	WebApplicationContext context2 =(WebApplicationContext) application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		//测试spring是否对接成功
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IContentDao contentDao = (IContentDao) context.getBean("contentDaoImpl");
		ContentAction contentAction = (ContentAction) context.getBean("contentAction");
		System.out.println(contentAction);
		
		//测试数据源配置是否成功
		ComboPooledDataSource dataSource = (ComboPooledDataSource)context.getBean("dataSource");
		System.out.println(dataSource);
		//测试数据库
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from tz_content");
		ResultSet rSet = statement.executeQuery();
		while(rSet.next()){
			System.out.println(rSet.getInt("id"));
			System.out.println(rSet.getString("title"));
			System.out.println(rSet.getString("content"));
		}
		//测试能否保存到数据库中
		Content content =new Content();
		content.setTitle("dddddddddddd");
		contentDao.save(content);
		
		
		
	}
	
}
