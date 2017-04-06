package com.tz.dao.comment;

import java.util.List;

import com.tz.core.dao.IBaseDao;
import com.tz.core.dao.TzParams;
import com.tz.model.Comment;
import com.tz.util.TzPageInfo;

/**
 * 
 * 评论管理
 * ICommentDao
 * 创建人:maerhuan
 * 时间：2017年04月06日 18:52:07
 * @version 1.0.0
 *
 */
public interface ICommentDao extends IBaseDao<Comment,Integer> {
	/**
	 * 
	 * 查询所有的评论总数</br>
	 * com.tz.dao.comment </br>
	 * 方法名：findComments </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:09:13 </br>
	 * @param params
	 * @param pageInfo
	 * @return List<Comment>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Comment> findComments(TzParams params,TzPageInfo pageInfo);
	/**
	 * 
	 * 计算所有的评论总数</br>
	 * com.tz.dao.comment </br>
	 * 方法名：countComment </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:09:44 </br>
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int countComment(TzParams params);
}
