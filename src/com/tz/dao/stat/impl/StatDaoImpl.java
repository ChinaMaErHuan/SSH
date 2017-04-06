package com.tz.dao.stat.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tz.core.dao.BaseDaoImpl;
import com.tz.core.dao.TzParams;
import com.tz.dao.stat.IStatDao;
import com.tz.model.Stat;
import com.tz.util.TzPageInfo;

/**
 * 
 * 统计管理
 * StatDaoImpl
 * 创建人:maerhuan
 * 时间：2017年04月06日 19:18:56 
 * @version 1.0.0
 *
 */
@Repository
@Transactional
public class StatDaoImpl extends BaseDaoImpl<Stat,Integer> implements IStatDao{

	/**
	 * (这里用一句话描述这个方法的作用)</br>
	 * com.tz.dao.stat.impl </br>
	 * 方法名：findStats </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:21:32 </br>
	 * @param params
	 * @param pageInfo
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@Override
	public List<Stat> findStats(TzParams params, TzPageInfo pageInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 计算总数</br>
	 * com.tz.dao.stat.impl </br>
	 * 方法名：countStat </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:21:32 </br>
	 * @param params
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@Override
	public int countStat(TzParams params) {
		// TODO Auto-generated method stub
		return 0;
	}

}
