/**
 * tzdesk系统平台
 * TzSSH
 * com.tz.web.content
 * ContentAction.java
 * 创建人:maerhuan 
 * 时间：2017年2月21日-下午3:56:55 
 * 2017潭州教育公司-版权所有
 */
package com.tz.web.content;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tz.core.action.BaseAction;
import com.tz.model.Content;
import com.tz.model.User;
import com.tz.service.content.IContentService;

/**
 * 
 * 
 * ContentAction 创建人:maerhuan 时间：2017年2月23日-下午3:42:23
 * 
 * @version 1.0.0
 * 
 */
@Controller("contentAction")
@Scope("prototype")
// prototype:每次action创建的对象都是一个全新的对象
// singleton:是单例
public class ContentAction extends BaseAction {
	@Autowired
	private IContentService contentService;

	private List<Content> contents;

	private Content content;

	/**
	 * 
	 * 查询展示</br> com.tz.web.content </br> 方法名：list </br> 创建人：maerhuan </br>
	 * 时间：2017年3月12日-下午11:14:54 </br>
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public String list() {
		contents = contentService.findContents(params, page);
		int itemCount = contentService.countCotent(params);
		page.setItemCount(String.valueOf(itemCount));
		return LIST;
	}

	/**
	 * 
	 * 模板加载</br> com.tz.web.content </br> 方法名：listTemplate </br> 创建人：maerhuan
	 * </br> 时间：2017年3月12日-下午11:15:06 </br>
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public String listTemplate() {
		contents = contentService.findContents(params, page);
		int itemCount = contentService.countCotent(params);
		page.setItemCount(String.valueOf(itemCount));
		return "listTemplate";
	}

	/**
	 * 
	 * 删除方法</br> com.tz.web.content </br> 方法名：delete </br> 创建人：maerhuan </br>
	 * 时间：2017年3月12日-下午11:14:30 </br>
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public String delete() {
		try {
			contentService.deleteById(id);
			result = SUCCESS;
		} catch (Exception e) {
			result = FAIL;
		}
		return AJAX_SUCCESS;
	}
	
	/**
	 * 
	 * 更新</br>
	 * com.tz.web.content </br>
	 * 方法名：update </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月12日-下午11:16:13 </br>
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String update(){
		content.setUpdateTime(new Date());
		contentService.updateDefault(content);
		//对象太大 要清空不然会报错
		content = null;
		result = SUCCESS;
		return AJAX_SUCCESS;
	}
	/**
	 * 
	 * 保存</br>
	 * com.tz.web.content </br>
	 * 方法名：save </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月14日-下午10:09:35 </br>
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String save(){
		content.setUser(new User(1));
		content.setIsComment(1);
		content.setStatus(1);
		content.setSort(0);
		content.setHit(0);
		content.setIsDelete(1);
		content.setIsTop(0);
		content = contentService.save(content);
		content.setUser(null);
		result = SUCCESS;
		return AJAX_SUCCESS;
	}

	
//	public String edit(){
//		
//		contentService.get(id);
//		System.out.println(contentService.get(id).getTitle());
//			
//		return AJAX_SUCCESS;
//	}
	/**
	 * 
	 * 编辑方法</br>
	 * com.tz.web.content </br>
	 * 方法名：edit </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月14日-下午10:06:56 </br> void
	 * @exception 
	 * @since  1.0.0
	 */
	public void edit(){//和struts2的ajax无关了
		Content content2 = contentService.get(id);
		outObject(content2);
		
	}
	
	

	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

}
