/**
 * tzdesk系统平台
 * TzSSH
 * com.tz.dao.content.impl
 * ContentDaoImpl.java
 * 创建人:maerhuan 
 * 时间：2017年2月21日-下午3:43:23 
 * 2017潭州教育公司-版权所有
 */
package com.tz.dao.content.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tz.core.dao.BaseDaoImpl;
import com.tz.core.dao.TzParams;
import com.tz.dao.content.IContentDao;
import com.tz.model.Content;
import com.tz.util.TzPageInfo;
import com.tz.util.TzStringUtils;
/**
 * 
 * 
 * ContentDaoImpl
 * 创建人:maerhuan 
 * 时间：2017年2月21日-下午3:43:59 
 * @version 1.0.0
 *
 */
@Repository
@Transactional
public class ContentDaoImpl extends BaseDaoImpl<Content,Integer> implements IContentDao {

	/**
	 * 根据关键字查询content</br>
	 * com.tz.dao.content.impl </br>
	 * 方法名：findContents </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月11日-上午12:56:22 </br>
	 * @param params
	 * @param pageInfo
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@Override
	public List<Content> findContents(TzParams params, TzPageInfo pageInfo) {
		DetachedCriteria detachedCriteria = getCurrentDetachedCriteria();
		if (params!=null) {
			if (TzStringUtils.isNotEmpty(params.getKeyword())) {
				//添加查询条件
				detachedCriteria.add(Restrictions.like("title", params.getKeyword(), MatchMode.ANYWHERE));
			}
		}
		detachedCriteria.addOrder(Order.desc("createTime")).add(Restrictions.eq("isDelete", 0));
		return findByDetachedCriteria(detachedCriteria, pageInfo);
	}

	/**
	 * 根据关键字统计content</br>
	 * com.tz.dao.content.impl </br>
	 * 方法名：countCotent </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月11日-上午12:56:22 </br>
	 * @param params
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@Override
	public int countCotent(TzParams params) {
		DetachedCriteria detachedCriteria = getCurrentDetachedCriteria();
		if (params!=null) {
			if (TzStringUtils.isNotEmpty(params.getKeyword())) {
				//添加查询条件
				detachedCriteria.add(Restrictions.like("title", params.getKeyword(), MatchMode.ANYWHERE));
			}
		}
		detachedCriteria.setProjection(Projections.count("id")).add(Restrictions.eq("isDelete", 0));
		Number number = (Number) detachedCriteria.getExecutableCriteria(getSession()).uniqueResult();
		return number==null?0:number.intValue();
	}

	@Override
	public void saveBatch(List<Content> contents) {//数据的导入和导出,excel表的数据导入到数据库中
		Session session = getSession();
		for (int i = 0; i < contents.size(); i++) {
			session.save(contents.get(i));
			if(i%50==0){
				session.flush();
				session.clear();
			}
		}
	}
	
	@Override
	public void updateBatch(List<Content> contents) {
		Session session = getSession();
		for (int i = 0; i < contents.size(); i++) {
//			必须根据id去更新
			session.update(contents.get(i));
			if(i%50==0){
				session.flush();
				session.clear();
			}
		}
	}
	
	
}
