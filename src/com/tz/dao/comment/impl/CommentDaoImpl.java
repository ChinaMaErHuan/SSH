package com.tz.dao.comment.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tz.core.dao.BaseDaoImpl;
import com.tz.core.dao.TzParams;
import com.tz.dao.comment.ICommentDao;
import com.tz.model.Comment;
import com.tz.util.TzPageInfo;
import com.tz.util.TzStringUtils;

/**
 * 
 * 评论管理
 * CommentDaoImpl
 * 创建人:maerhuan
 * 时间：2017年04月06日 18:52:07 
 * @version 1.0.0
 *
 */
@Repository
@Transactional
public class CommentDaoImpl extends BaseDaoImpl<Comment,Integer> implements ICommentDao{

	/**
	 * 查询所有的评论分页</br>
	 * com.tz.dao.comment.impl </br>
	 * 方法名：findComments </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:10:03 </br>
	 * @param params
	 * @param pageInfo
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@Override
	public List<Comment> findComments(TzParams params, TzPageInfo pageInfo) {
		DetachedCriteria detachedCriteria = getCurrentDetachedCriteria();
		if(params!=null){
			if (TzStringUtils.isNotEmpty(params.getKeyword())) {
				//添加查询条件
				detachedCriteria.add(Restrictions.like("name", params.getKeyword(), MatchMode.ANYWHERE));
			}
		}
		detachedCriteria.addOrder(Order.desc("createTime")).add(Restrictions.eq("isDelete", 0));
		return findByDetachedCriteria(detachedCriteria, pageInfo);
	}

	/**
	 * 计算所有的评论</br>
	 * com.tz.dao.comment.impl </br>
	 * 方法名：countComment </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:10:03 </br>
	 * @param params
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@Override
	public int countComment(TzParams params) {
		DetachedCriteria detachedCriteria = getCurrentDetachedCriteria();
		if (params!=null) {
			if (TzStringUtils.isNotEmpty(params.getKeyword())) {
				//添加查询条件
				detachedCriteria.add(Restrictions.like("name", params.getKeyword(), MatchMode.ANYWHERE));
			}
		}
		detachedCriteria.setProjection(Projections.count("id")).add(Restrictions.eq("isDelete", 0));
		Number number = (Number) detachedCriteria.getExecutableCriteria(getSession()).uniqueResult();
		return number==null?0:number.intValue();
	}
	
}
