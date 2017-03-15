/**
 * tzdesk系统平台
 * cms
 * com.tz.service
 * TestPermissionServiceImpl.java
 * 创建人:maerhuan 
 * 时间：2017年3月12日-下午10:17:35 
 * 2017潭州教育公司-版权所有
 */
package com.tz.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tz.core.dao.TzParams;
import com.tz.service.content.IContentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestContentServiceImpl {
	@Autowired
	private IContentService contentService;
	@Test
	public void handle(){
		Integer numInteger = contentService.countCotent(new TzParams("中国", "2015", 2));
		System.out.println(numInteger);
	}
	
	
	
}
