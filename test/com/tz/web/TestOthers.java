package com.tz.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tz.core.dao.TzParams;
import com.tz.dao.content.IContentDao;
import com.tz.dao.permission.IPermissionDao;
import com.tz.model.Content;
import com.tz.service.permission.IPermissionService;
import com.tz.service.user.IUserService;
import com.tz.util.TzPageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestOthers {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IPermissionService permissionService;
	
	@Autowired
	private IPermissionDao permissionDao;
	
	@Autowired
	private IContentDao contentDao;
	
//	@Test
//	public void handler() throws JSONException{
//		String password = TzStringUtils.md5Base64("123456");
//		User user = userService.findExitUser("keke", null);
//		System.out.println(user.getUsername());
//	}
	
	@Test
	public void handler3(){
		TzParams params = new TzParams();
		params.setKeyword("中");
		TzPageInfo pageInfo = new TzPageInfo();
		pageInfo.setFirstResult("0");
		pageInfo.setMaxResults("10");
		List<Content> contents = contentDao.findContents(params, pageInfo);
		int itemCount = contentDao.countCotent(params);
		System.out.println("表中一共有："+itemCount+"条数据");
		for (Content content : contents) {
			System.out.println(content.getId()+"==="+content.getTitle());
		}
	}
	
	@Test
	public void handler2(){
//		String url = "http://news.qq.com/";
//		String filterUrl  = "http://news.qq.com/a/";
//		//http://www.xinhuanet.com/
//		//第一步：导入解析包jsoup.jar java中的javascript+jquery
//		//第二步：根据URL获取网页源代码。
//		Document document = Jsoup.parse(TmGatherContentUtil.getHtmlResourceByURL(url,"GBK"));//java.net下面api
//		//第三步：抓取网页中所有需要的URL
//		Elements links = document.getElementsByTag("a");
//		Set<String> urls = new HashSet<String>();
//		for (Element element : links) {
//			String href = element.attr("href");
//			if(TzStringUtils.isNotEmpty(href) && href.startsWith(filterUrl)){
//				urls.add(href);
//			}
//		}
//		
//		//第四步：解析匹配出来的URL，讲需要的匹配出来
//		for (String string : urls) {
//			try {
//				Document document2 = Jsoup.parse(TmGatherContentUtil.getHtmlResourceByURL(string,"GBK"));
//				String title = document2.getElementsByTag("h1").get(0).text();
//				String keyword = document2.getElementsByTag("meta").get(2).attr("content");
//				String desc = document2.getElementsByTag("meta").get(3).attr("content");
//				String content = document2.getElementById("Cnt-Main-Article-QQ").html();
//				System.out.println(title+"==="+keyword+"==="+desc);
//				Content c = new Content();
//				c.setTitle(title);
//				c.setContent(content);
//				c.setPublishDate(new Date());
//				c.setAuthor("keke");
//				c.setHit(0);
//				c.setIsTop(0);
//				c.setStatus(0);
//				c.setIsDelete(0);
//				c.setUser(new User(1));
//				c.setSort(1);
//				c.setKeyword(keyword);
//				c.setDescription(desc);
//				contentDao.save(c);
//				System.out.println("采集成功--------"+string);
//			} catch (Exception e) {
//				continue;
//			}
//		}
		//第五步：保存数据库
	}
	
//	@Test
//	public void handler2() throws JSONException{
//		List<Object[]> objects = permissionService.findPermissionByUserId(2);
//		System.out.println(JSONUtil.serialize(objects));
//	}
//	
//	@Test
//	public void handler03(){
//		List<Permission> permissions = permissionDao.findRoot();
//		for (Permission permission : permissions) {
//			System.out.println(permission.getName());
//		}
//	}
	
//	[
//	 {name=后台首页, opid=2, pid=2, url=javascript:void(0);}, 
//	 {name=集群管理, opid=3, pid=3, url=javascript:void(0);}, 
//	 {name=部门管理, opid=4, pid=4, url=javascript:void(0);}, 
//	 {name=员工管理, opid=5, pid=5, url=javascript:void(0);}, 
//	 {name=中间服务部门管理, opid=6, pid=6, url=javascript:void(0);}, 
//	 {name=部门业绩比例管理, opid=7, pid=7, url=javascript:void(0);}, 
//	 {name=vip学员, opid=8, pid=8, url=javascript:void(0);}, 
//	 {name=虹之玉统计管理, opid=9, pid=9, url=javascript:void(0);}, 
//	 {name=导出个人虹之玉, opid=110, pid=110, url=javascript:void(0);},
//	 {name=权限, opid=111, pid=111, url=javascript:void(0);}, 
//	 {name=角色管理, opid=114, pid=114, url=javascript:void(0);}, 
//	 {name=访谈管理, opid=144, pid=144, url=javascript:void(0);}
//	]
			
			
	

	
//	@Test
//	public void handler04() throws JSONException{
//		List<HashMap<String, Object>> permissions = permissionService.findRoot();
//		System.out.println(JSONUtil.serialize(permissions));
//	}
//	
	//hibernate 批量新增和批量修改 
	//springjdbctemplate批量新增和修改
	@Test
	public void handler5(){
		Date sdate = new Date();
		Content content = null;
		for (int i = 0; i < 1000;i++) {
			content = new Content();
			content.setTitle(i+"");
			content.setContent(i+"i");
			contentDao.save(content);
			System.out.println("==========="+i+"已保存成功==========");
		}
		long time = new Date().getTime() - sdate.getTime();
		System.out.println("一共耗时:"+time+"ms");
	}
	
	
	@Test
	public void handler6(){
		Date sdate = new Date();
		List<Content> contents = new ArrayList<Content>();
		for (int i = 0; i < 10000;i++) {
			Content content = new Content();
			content.setTitle(i+"");
			content.setContent(i+"i");
			contents.add(content);
		}
		contentDao.saveBatch(contents);
		long time = new Date().getTime() - sdate.getTime();
		System.out.println("一共耗时:"+(time/1000)+"s");
	}
	
	
}
