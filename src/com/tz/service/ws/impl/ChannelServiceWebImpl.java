/**
 * Tm系统平台
 * TzCms
 * com.tz.ws
 * IChannelService.java
 * 创建人:xuchengfei 
 * 时间：2015年5月28日-下午6:36:00 
 * 2015Tm公司-版权所有
 */
package com.tz.service.ws.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tz.model.Channel;
import com.tz.service.channel.IChannelService;
import com.tz.service.ws.IChannelServiceWeb;
import com.tz.util.BeanMapUtils;

/**
 * 
 * 
 * ChannelServiceWebImpl
 * 创建人:maerhuan 
 * 时间：2017年5月14日-下午8:43:05 
 * @version 1.0.0
 *
 */
@Service
public class ChannelServiceWebImpl implements IChannelServiceWeb {

	@Autowired
	private IChannelService channelservice;

	
	public String save(Channel channel) {
		try {
			channel = channelservice.save(channel);
			return JSONUtil.serialize(channel);
		}catch (Exception e) {
			return  null;
		}
	}
	
	public Map<String, Object> save(Map<String, Object> map) {
		try {
			Channel channel = (Channel) BeanMapUtils.toBean(Channel.class,map);
			channel = channelservice.save(channel);
			return BeanMapUtils.toMap(channel);
		}catch (Exception e) {
			return  null;
		}
	}
	

	@Override
	public List<Map<String, Object>> findChannels() {
		try {
			List<Channel> channels = channelservice.find("from Channel where status =1 order by sort");
			List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
			for (Channel channel : channels) {
					maps.add(BeanMapUtils.toMap(channel));
			}
			return maps;
		} catch (IllegalAccessException | InvocationTargetException
				| IntrospectionException e) {
			return null;
		}
	}
}
