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

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tz.core.dao.TzParams;
import com.tz.dao.stat.IStatDao;
import com.tz.model.Stat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestStatDaoImpl {
	@Autowired
	private IStatDao statDao;
	TzParams params = new TzParams();
	@Test
	public void handle(){
		int list = statDao.countStat(params);
		System.out.println(list);
		
	}
	
	@Test
	public void handle2(){
		Stat stat= null;
		for (int i = 0; i < 20; i++) {
			stat = new Stat();
			stat.setIsDelete(1);
			stat.setName("啊师傅过来的开发");
			stat.setCreateTime(new Date());
			statDao.save(stat);
		}
	}
}
