package com.tz.service.channel.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	@Override
	public int countChannel(TzParams params) {
		return getChannelDao().countChannel(params);
	}
	
}