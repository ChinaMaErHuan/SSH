/**
 * tzdesk系统平台
 * cms
 * com.tz.dao
 * TestChannelDao.java
 * 创建人:maerhuan 
 * 时间：2017年4月7日-下午1:37:00 
 * 2017潭州教育公司-版权所有
 */
package com.tz.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tz.core.dao.TzParams;
import com.tz.dao.channel.IChannelDao;
import com.tz.model.Channel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestChannelDao {
	@Autowired
	private IChannelDao channelDao;
	@Test
	public void handle(){
		TzParams params = new TzParams();
		int list = channelDao.countChannel(params);
		System.out.println(list);
		
	}
}
