package com.tz.service.stat;

import java.util.List;

import com.tz.core.dao.TzParams;
import com.tz.core.service.IBaseService;
import com.tz.model.Stat;
import com.tz.util.TzPageInfo;

/**
 * 
 * 统计管理
 * IStatService
 * 创建人:maerhuan
 * 时间：2017年04月06日 19:18:56 
 * @version 1.0.0
 *
 */
public interface IStatService extends IBaseService<Stat,Integer> {
	/**
	 * 
	 * 查询分页</br>
	 * com.tz.service.stat </br>
	 * 方法名：findStats </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:31:09 </br>
	 * @param params
	 * @param pageInfo
	 * @return List<Stat>
	 * @exception 
	 * @since  1.0.0
	 */
	 List<Stat> findStats(TzParams params,TzPageInfo pageInfo);
	/**
	 * 
	 * 计算总数</br>
	 * com.tz.service.stat </br>
	 * 方法名：countStat </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:31:24 </br>
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	 int countStat(TzParams params);
}
