package com.tz.service.channel;

import java.util.List;

import com.tz.core.TzLog;
import com.tz.core.dao.TzParams;
import com.tz.core.service.IBaseService;
import com.tz.model.Channel;
import com.tz.util.TzPageInfo;

/**
 * 
 * 栏目管理
 * IChannelService
 * 创建人:maerhuan
 * 时间：2017年04月06日 19:18:12 
 * @version 1.0.0
 *
 */
 public interface IChannelService extends IBaseService<Channel,Integer> {
	/**
	 * 
	 * 查询分页</br>
	 * com.tz.service.channel </br>
	 * 方法名：findChannels </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:28:56 </br>
	 * @param params
	 * @param pageInfo
	 * @return List<Channel>
	 * @exception 
	 * @since  1.0.0
	 */
	 List<Channel> findChannels(TzParams params,TzPageInfo pageInfo);
	/**
	 * 
	 * 计算总数</br>
	 * com.tz.service.channel </br>
	 * 方法名：countChannel </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:28:59 </br>
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	 int countChannel(TzParams params);
	 /**
	  * 
	  * 查询所有的根栏目</br>
	  * com.tz.service.channel </br>
	  * 方法名：findRootChannels </br>
	  * 创建人：maerhuan </br>
	  * 时间：2017年5月10日-下午1:45:36 </br>
	  * @param params
	  * @param pageInfo
	  * @return List<Channel>
	  * @exception 
	  * @since  1.0.0
	  */
	  List<Channel> findRootChannels(TzParams params,TzPageInfo pageInfo);
	  /**
	   * 
	   * 计算根栏目个数</br>
	   * com.tz.service.channel </br>
	   * 方法名：countRootChannel </br>
	   * 创建人：maerhuan </br>
	   * 时间：2017年5月10日-下午1:45:53 </br>
	   * @param params
	   * @return int
	   * @exception 
	   * @since  1.0.0
	   */
	  int countRootChannel(TzParams params);
	
}
