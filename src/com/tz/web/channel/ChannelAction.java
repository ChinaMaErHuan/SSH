/**
 * tzdesk系统平台
 * cms
 * com.tz.web.channel
 * ChannelAction.java
 * 创建人:maerhuan 
 * 时间：2017年4月6日-下午11:07:47 
 * 2017潭州教育公司-版权所有
 */
package com.tz.web.channel;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tz.core.Method;
import com.tz.core.action.BaseAction;
import com.tz.core.interceptor.TzRequestMethod;
import com.tz.model.Channel;
import com.tz.service.channel.IChannelService;

@Controller("channelAction")
@Scope("prototype")
public class ChannelAction extends BaseAction {
	@Autowired
	private IChannelService channelService;
	private List<Channel> channels;

	private Channel channel;

	/**
	 * 
	 * 查询展示</br> com.tz.web.channel </br> 方法名：list </br> 创建人：maerhuan </br>
	 * 时间：2017年4月7日-下午12:58:05 </br>
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public String list() {
		channels = channelService.findRootChannels(params, page);
		int itemCount = channelService.countRootChannel(params);
		page.setItemCount(String.valueOf(itemCount));
		return LIST;
	}

	/**
	 * 
	 * 加载模板数据</br> com.tz.web.channel </br> 方法名：listTemplate </br> 创建人：maerhuan
	 * </br> 时间：2017年4月7日-下午12:59:55 </br>
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public String listTemplate() {
		channels = channelService.findRootChannels(params, page);
		int itemCount = channelService.countRootChannel(params);
		page.setItemCount(String.valueOf(itemCount));
		return "listTemplate";
	}

	/**
	 * 
	 * 编辑</br> com.tz.web.channel </br> 方法名：edit </br> 创建人：maerhuan </br>
	 * 时间：2017年4月7日-下午1:02:17 </br> void
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public void edit() {// 和struts2的ajax无关了
		Channel channel2 = channelService.get(id);
		outObject(channel2);
	}

	/**
	 * 
	 * 保存</br> com.tz.web.channel </br> 方法名：save </br> 创建人：maerhuan </br>
	 * 时间：2017年4月7日-下午1:02:05 </br>
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	@Method(method = TzRequestMethod.POST)
	public String save() {
		try {
			channel.setUser(getUser());
			channel.setStatus(1);
			channel.setIsDelete(0);
			channel.setSort(1);
			channel = channelService.save(channel);//递归序列化
			channel.setUser(null);
			channel.setChannels(null);
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();//JsonWriterException
			result = "fail";
		}
		return AJAX_SUCCESS;
	}

	/**
	 * 
	 * 更新</br> com.tz.web.channel </br> 方法名：update </br> 创建人：maerhuan </br>
	 * 时间：2017年4月7日-下午1:01:55 </br>
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
//	@Method(method=TzRequestMethod.POST)
	public String update(){
		channel.setUpdateTime(new Date());
		channel = channelService.updateDefault(channel);
		channel=null;
		result ="success";
		return AJAX_SUCCESS;
	}

	/**
	 * 
	 * 删除方法</br> com.tz.web.channel </br> 方法名：delete </br> 创建人：maerhuan </br>
	 * 时间：2017年4月7日-下午1:01:39 </br>
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public String delete() {
		try {
			channel.setUpdateTime(new Date());
			channel.setIsDelete(1);
			channelService.updateDefault(channel);
			result = "success";
		} catch (Exception e) {
			result = "fail";
		}
		return AJAX_SUCCESS;
	}
	/**
	 * 
	 * 新增</br>
	 * com.tz.web.channel </br>
	 * 方法名：add </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年5月12日-下午10:16:58 </br>
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String add(){
		if(id!=null){
			channel = channelService.get(id);
		}
		return ADD;
	}

	public List<Channel> getChannels() {
		return channels;
	}

	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	

}
