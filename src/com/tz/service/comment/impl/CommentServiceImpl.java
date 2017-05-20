package com.tz.service.comment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tz.core.TzLog;
import com.tz.core.dao.TzParams;
import com.tz.core.service.BaseServiceImpl;
import com.tz.dao.comment.ICommentDao;
import com.tz.model.Comment;
import com.tz.service.comment.ICommentService;
import com.tz.util.TzPageInfo;

/**
 * 
 * 评论管理
 * CommentServiceImpl 
 * 创建人:maerhuan 
 * 时间：2017年04月06日 18:52:07
 * 
 * @version 1.0.0
 * 
 */
@Service
@TzLog(author="maerhuan",model="comment",desc="评论管理",name="评论模块")
public class CommentServiceImpl extends BaseServiceImpl<Comment,Integer>  implements ICommentService {
	
	public ICommentDao getCommentDao() {
		return (ICommentDao) super.getBaseDao();
	}
	
	@Autowired
	public void setCommentDao(ICommentDao commentDao) {
		super.setBaseDao(commentDao);
	}

	/**
	 * 查询并分页</br>
	 * com.tz.service.comment.impl </br>
	 * 方法名：findComments </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:26:36 </br>
	 * @param params
	 * @param pageInfo
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@TzLog(author="maerhuan",model="comment",desc="查询评论",name="评论模块")
	@Override
	public List<Comment> findComments(TzParams params, TzPageInfo pageInfo) {
		return getCommentDao().findComments(params, pageInfo);
	}

	/**
	 * 计算总数</br>
	 * com.tz.service.comment.impl </br>
	 * 方法名：countComment </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:26:36 </br>
	 * @param params
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@TzLog(author="maerhuan",model="comment",desc="计算评论",name="评论模块")
	@Override
	public int countComment(TzParams params) {
		return getCommentDao().countComment(params);
	}
	
}
