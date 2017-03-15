/**
 * tzdesk系统平台
 * cms
 * com.tz.dao
 * TestContentDaoImpl.java
 * 创建人:maerhuan 
 * 时间：2017年3月10日-下午3:55:43 
 * 2017潭州教育公司-版权所有
 */
package com.tz.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tz.core.dao.TzParams;
import com.tz.dao.content.IContentDao;
import com.tz.model.Content;
import com.tz.model.User;
import com.tz.util.TzGatherContentUtil;
import com.tz.util.TzPageInfo;
import com.tz.util.TzStringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestContentDaoImpl {
	@Autowired
	private IContentDao contentDao;
	//将抓取下来的数据插入到表中
	@Test
	public void insertDataToContent(){
		String url = "http://news.qq.com/";
		String filterUrl  = "http://news.qq.com/a/";
		//http://www.xinhuanet.com/
		//第一步：导入解析包jsoup.jar java中的javascript+jquery
		//第二步：根据URL获取网页源代码。
		Document document = Jsoup.parse(TzGatherContentUtil.getHtmlResourceByURL(url,"GBK"));//java.net下面api
		//第三步：抓取网页中所有需要的URL
		Elements links = document.getElementsByTag("a");
		Set<String> urls = new HashSet<String>();
		for (Element element : links) {
			String href = element.attr("href");
			if(TzStringUtils.isNotEmpty(href) && href.startsWith(filterUrl)){
				urls.add(href);
			}
		}
		
		//第四步：解析匹配出来的URL，讲需要的匹配出来
		for (String string : urls) {
			try {
				Document document2 = Jsoup.parse(TzGatherContentUtil.getHtmlResourceByURL(string,"GBK"));
				String title = document2.getElementsByTag("h1").get(0).text();
				String keyword = document2.getElementsByTag("meta").get(2).attr("content");
				String desc = document2.getElementsByTag("meta").get(3).attr("content");
				String content = document2.getElementById("Cnt-Main-Article-QQ").html();
//				System.out.println(title+"==="+keyword+"==="+desc);
				Content c = new Content();
				c.setTitle(title);
				c.setContent(content);
				c.setPublishDate(new Date());
				c.setAuthor("keke");
				c.setHit(0);
				c.setIsTop(0);
				c.setStatus(0);
				c.setIsDelete(0);
				c.setUser(new User(1));
				c.setSort(1);
				c.setKeyword(keyword);
				c.setDescription(desc);
				contentDao.save(c);
				System.out.println("采集成功--------"+string);
			} catch (Exception e) {
				continue;
			}
		}
	}
	
	@Test
	public void handle2(){
		TzParams params = new TzParams("美国","2017-3-11",5);
		TzPageInfo pageInfo = new TzPageInfo();
		pageInfo.setFirstResult("0");
		pageInfo.setMaxResults("12");
		List<Content> list = contentDao.findContents(params, pageInfo);
		int count = contentDao.countCotent(params);
		System.out.println(count);
		for (Content content : list) {
			System.out.println(content.getTitle());
		}
	}
	
	@Test
	public void handle3(){
		Content content = contentDao.get(170);
		content.setAuthor("maerhuan");
		content.setHit(500);
		contentDao.updateDefault(content);
	}
	
}
