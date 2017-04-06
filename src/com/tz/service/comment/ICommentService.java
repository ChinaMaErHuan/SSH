package com.tz.service.comment;

import java.util.List;

import com.tz.core.dao.TzParams;
import com.tz.core.service.IBaseService;
import com.tz.model.Comment;
import com.tz.util.TzPageInfo;

/**
 * 
 * 评论管理
 * ICommentService
 * 创建人:maerhuan
 * 时间：2017年04月06日 18:52:07 
 * @version 1.0.0
 *
 */
public interface ICommentService extends IBaseService<Comment,Integer> {
	/**
	 * 
	 * 查询并且分页</br>
	 * com.tz.service.comment </br>
	 * 方法名：findComments </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:26:10 </br>
	 * @param params
	 * @param pageInfo
	 * @return List<Comment>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Comment> findComments(TzParams params,TzPageInfo pageInfo);
	/**
	 * 
	 * 计算总数</br>
	 * com.tz.service.comment </br>
	 * 方法名：countComment </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:25:56 </br>
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int countComment(TzParams params);
	
}
