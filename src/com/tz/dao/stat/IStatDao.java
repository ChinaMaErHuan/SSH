package com.tz.dao.stat;

import java.util.List;

import com.tz.core.dao.IBaseDao;
import com.tz.core.dao.TzParams;
import com.tz.model.Stat;
import com.tz.util.TzPageInfo;

/**
 * 
 * 统计管理
 * IStatDao
 * 创建人:maerhuan
 * 时间：2017年04月06日 19:18:56
 * @version 1.0.0
 *
 */
public interface IStatDao extends IBaseDao<Stat,Integer> {
	/**
	 * 
	 * 查询并且分页</br>
	 * com.tz.dao.stat </br>
	 * 方法名：findStats </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:21:12 </br>
	 * @param params
	 * @param pageInfo
	 * @return List<Stat>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Stat> findStats(TzParams params,TzPageInfo pageInfo);
	/**
	 * 
	 * 计算总数</br>
	 * com.tz.dao.stat </br>
	 * 方法名：countStat </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:23:13 </br>
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int countStat(TzParams params);
}
