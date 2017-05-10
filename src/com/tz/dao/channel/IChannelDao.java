package com.tz.dao.channel;

import java.util.List;

import com.tz.core.dao.IBaseDao;
import com.tz.core.dao.TzParams;
import com.tz.model.Channel;
import com.tz.util.TzPageInfo;

/**
 * 
 * 栏目管理
 * IChannelDao
 * 创建人:maerhuan
 * 时间：2017年04月06日 19:18:12
 * @version 1.0.0
 *
 */
public interface IChannelDao extends IBaseDao<Channel,Integer> {
	/**
	 * 
	 * 查询所有的栏目</br>
	 * com.tz.dao.channel </br>
	 * 方法名：findChannels </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午9:54:26 </br>
	 * @param params
	 * @param pageInfo
	 * @return List<Content>
	 * @exception 
	 * @since  1.0.0
	 */
	 List<Channel> findChannels(TzParams params,TzPageInfo pageInfo);
	/**
	 * 
	 * 计算所有的栏目</br>
	 * com.tz.dao.channel </br>
	 * 方法名：countChannel </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午9:54:42 </br>
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	 int countChannel(TzParams params);
	 /**
	  * 
	  * 查询根栏目</br>
	  * com.tz.dao.channel </br>
	  * 方法名：findRootChannels </br>
	  * 创建人：maerhuan </br>
	  * 时间：2017年5月10日-上午12:29:57 </br>
	  * @param params
	  * @param pageInfo
	  * @return List<Channel>
	  * @exception 
	  * @since  1.0.0
	  */
	 List<Channel> findRootChannels(TzParams params,TzPageInfo pageInfo);
	 /**
	  * 
	  * 求总数</br>
	  * com.tz.dao.channel </br>
	  * 方法名：countRootChannel </br>
	  * 创建人：maerhuan </br>
	  * 时间：2017年5月10日-上午12:30:27 </br>
	  * @param params
	  * @return int
	  * @exception 
	  * @since  1.0.0
	  */
	 int countRootChannel(TzParams params);
}
