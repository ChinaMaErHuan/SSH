/**
 * tzdesk系统平台
 * cms
 * com.tz.web.comment
 * CommentAction.java
 * 创建人:maerhuan 
 * 时间：2017年4月6日-下午11:08:41 
 * 2017潭州教育公司-版权所有
 */
package com.tz.web.comment;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tz.core.Method;
import com.tz.core.action.BaseAction;
import com.tz.core.interceptor.TzRequestMethod;
import com.tz.model.Comment;
import com.tz.model.User;
import com.tz.service.comment.ICommentService;

@Controller("commentAction")
@Scope("prototype")
public class CommentAction extends BaseAction {
	@Autowired
	private ICommentService commentService;

	private List<Comment> comments;
	private Comment comment;

	/**
	 * 
	 * 查询分页</br> com.tz.web.comment </br> 方法名：list </br> 创建人：maerhuan </br>
	 * 时间：2017年4月7日-下午4:49:12 </br>
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */

	public String list() {
		comments = commentService.findComments(params, page);
		int itemCount = commentService.countComment(params);
		page.setItemCount(String.valueOf(itemCount));
		return LIST;
	}
	/**
	 *加载模板
	 * (这里用一句话描述这个方法的作用)</br>
	 * com.tz.web.comment </br>
	 * 方法名：listTemplate </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月7日-下午4:51:43 </br>
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String listTemplate() {
		comments = commentService.findComments(params, page);
		int itemCount = commentService.countComment(params);
		page.setItemCount(String.valueOf(itemCount));
		return "listTemplate";
	}
	/**
	 * 
	 * 编辑</br>
	 * com.tz.web.comment </br>
	 * 方法名：edit </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月7日-下午4:51:53 </br>
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String edit() {
		if (id != null) {
			comment = commentService.get(id);
		}
		return "edit";
	}
	
	/**
	 * 
	 * 保存</br>
	 * com.tz.web.comment </br>
	 * 方法名：save </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月7日-下午4:52:04 </br>
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@Method(method = TzRequestMethod.POST)
	public String save() {
		comment.setUser(new User(1));
		comment.setStatus(1);
		comment.setIsDelete(0);
		comment = commentService.save(comment);
		comment.setUser(null);
		result = "success";
		return AJAX_SUCCESS;
	}
	/**
	 * 
	 * 更新</br>
	 * com.tz.web.comment </br>
	 * 方法名：update </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月7日-下午4:52:43 </br>
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@Method(method=TzRequestMethod.POST)
	public String update(){
		comment.setUpdateTime(new Date());
		comment = commentService.updateDefault(comment);
		comment=null;
		result ="success";
		return AJAX_SUCCESS;
	}
	
	/**
	 * 
	 * (这里用一句话描述这个方法的作用)</br>
	 * com.tz.web.comment </br>
	 * 方法名：delete </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月7日-下午4:52:25 </br>
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String delete(){
		try {
			comment.setUpdateTime(new Date());
			comment.setIsDelete(1);
			commentService.updateDefault(comment);
			result = "success";
		} catch (Exception e) {
			result = "fail";
		}
		return AJAX_SUCCESS;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}
