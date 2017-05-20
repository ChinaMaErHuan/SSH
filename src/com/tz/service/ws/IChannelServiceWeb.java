/**
 * Tm系统平台
 * TzCms
 * com.tz.ws
 * IChannelService.java
 * 创建人:xuchengfei 
 * 时间：2015年5月28日-下午6:36:00 
 * 2015Tm公司-版权所有
 */
package com.tz.service.ws;

import java.util.List;
import java.util.Map;

import com.tz.model.Channel;


/**
 * 
 * 
 * IChannelServiceWeb
 * 创建人:maerhuan 
 * 时间：2017年5月14日-下午8:20:04 
 * @version 1.0.0
 *
 */
public interface IChannelServiceWeb {
	
	public String save(Channel channel);
	public Map<String, Object> save(Map<String, Object> map); 
	public List<Map<String, Object>> findChannels();
	
}
