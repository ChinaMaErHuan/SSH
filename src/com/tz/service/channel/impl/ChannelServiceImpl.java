package com.tz.service.channel.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tz.core.TzLog;
import com.tz.core.dao.TzParams;
import com.tz.core.service.BaseServiceImpl;
import com.tz.dao.channel.IChannelDao;
import com.tz.model.Channel;
import com.tz.service.channel.IChannelService;
import com.tz.util.TzPageInfo;

/**
 * 
 * 栏目管理
 * ChannelServiceImpl 
 * 创建人:maerhuan 
 * 时间：2017年04月06日 19:18:12
 * 
 * @version 1.0.0
 * 
 */
@Service
@TzLog(author="maerhuan",model = "栏目",desc="栏目管理模块",name = "栏目",time = "时间：2017年04月06日")
public class ChannelServiceImpl extends BaseServiceImpl<Channel,Integer>  implements IChannelService {
	
	public IChannelDao getChannelDao() {
		return (IChannelDao) super.getBaseDao();
	}
	
	@Autowired
	public void setChannelDao(IChannelDao channelDao) {
		super.setBaseDao(channelDao);
	}

	/**
	 * 查询</br>
	 * com.tz.service.channel.impl </br>
	 * 方法名：findChannels </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:29:08 </br>
	 * @param params
	 * @param pageInfo
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@TzLog(author="maerhuan",model = "栏目",desc="查询栏目")
	@Override
	public List<Channel> findChannels(TzParams params, TzPageInfo pageInfo) {
		return getChannelDao().findChannels(params, pageInfo);
	}

	/**
	 * 计算总数</br>
	 * com.tz.service.channel.impl </br>
	 * 方法名：countChannel </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:29:08 </br>
	 * @param params
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@TzLog(author="maerhuan",model = "栏目",desc="查询栏目数量")
	@Override
	public int countChannel(TzParams params) {
		return getChannelDao().countChannel(params);
	}

	/**
	 * 计算所有的根栏目</br>
	 * com.tz.service.channel.impl </br>
	 * 方法名：findRootChannels </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年5月10日-下午1:46:32 </br>
	 * @param params
	 * @param pageInfo
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@TzLog(author="maerhuan",model = "栏目",desc="查询根栏目")
	@Override
	public List<Channel> findRootChannels(TzParams params, TzPageInfo pageInfo) {
		
		return getChannelDao().findRootChannels(params, pageInfo);
	}

	/**
	 * 计算所有的根栏目的个数</br>
	 * com.tz.service.channel.impl </br>
	 * 方法名：countRootChannel </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年5月10日-下午1:46:32 </br>
	 * @param params
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@TzLog(author="maerhuan",model = "栏目",desc="查询根栏目数量")
	@Override
	public int countRootChannel(TzParams params) {
		return getChannelDao().countRootChannel(params);
	}
	
	
}
