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
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tz.core.dao.TzParams;
import com.tz.dao.comment.ICommentDao;
import com.tz.model.Comment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestCommentDaoImpl {
	@Autowired
	private ICommentDao commentDao;
	TzParams params = new TzParams();
	@Test
	public void handle(){
		int list = commentDao.countComment(params);
		System.out.println(list);
		
	}
	@Test
	public void handle2(){
		Comment comment= null;
		for (int i = 0; i < 50; i++) {
			comment = new Comment();
			comment.setContent("写的是写什么玩意啊 真烂 真差");
			comment.setCreateTime(new Date());
			comment.setHits(new Random().nextInt(10000));
			comment.setName("maerhuan");
			comment.setStatus(0);
			comment.setIp("192.168.0.1");
			commentDao.save(comment);
		}
	}
}
