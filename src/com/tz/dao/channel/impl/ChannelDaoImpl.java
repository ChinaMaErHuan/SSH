package com.tz.dao.channel.impl;

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
import com.tz.dao.channel.IChannelDao;
import com.tz.model.Channel;
import com.tz.util.TzPageInfo;
import com.tz.util.TzStringUtils;

/**
 * 
 * 栏目管理
 * ChannelDaoImpl
 * 创建人:maerhuan
 * 时间：2017年04月06日 19:18:12 
 * @version 1.0.0
 *
 */
@Repository
@Transactional
public class ChannelDaoImpl extends BaseDaoImpl<Channel,Integer> implements IChannelDao{

	/**
	 * 查询所有的栏目</br>
	 * com.tz.dao.channel.impl </br>
	 * 方法名：findChannels </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午9:54:58 </br>
	 * @param params
	 * @param pageInfo
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@Override
	public List<Channel> findChannels(TzParams params, TzPageInfo pageInfo) {
		DetachedCriteria detachedCriteria = getCurrentDetachedCriteria();
		if(params!=null){
			if (TzStringUtils.isNotEmpty(params.getKeyword())) {
				//添加查询条件
				detachedCriteria.add(Restrictions.like("name", params.getKeyword(), MatchMode.ANYWHERE));
			}
		}
		detachedCriteria.addOrder(Order.asc("createTime")).add(Restrictions.eq("isDelete", 0));
		return findByDetachedCriteria(detachedCriteria, pageInfo);
	}

	/**
	 * 计算所有的栏目</br>
	 * com.tz.dao.channel.impl </br>
	 * 方法名：countChannel </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午9:54:58 </br>
	 * @param params
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@Override
	public int countChannel(TzParams params) {
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
