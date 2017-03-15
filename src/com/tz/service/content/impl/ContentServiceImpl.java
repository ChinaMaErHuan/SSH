/**
 * tzdesk系统平台
 * TzSSH
 * com.tz.service.content.impl
 * ContentServoceImpl.java
 * 创建人:maerhuan 
 * 时间：2017年2月21日-下午3:55:17 
 * 2017潭州教育公司-版权所有
 */
package com.tz.service.content.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tz.core.dao.TzParams;
import com.tz.core.service.BaseServiceImpl;
import com.tz.dao.content.IContentDao;
import com.tz.model.Content;
import com.tz.service.content.IContentService;
import com.tz.util.TzPageInfo;
/**
 * 
 * 
 * ContentServiceImpl
 * 创建人:maerhuan 
 * 时间：2017年2月23日-下午3:41:43 
 * @version 1.0.0
 *
 */
@Service
public class ContentServiceImpl extends BaseServiceImpl<Content, Integer> implements IContentService{

	public IContentDao getContentDao() {
		return (IContentDao) super.getBaseDao();
	}
	
	@Autowired
	public void setContentDao(IContentDao contentDao) {
		super.setBaseDao(contentDao);
	}

	/**
	 * 查询并且分页</br>
	 * com.tz.service.content.impl </br>
	 * 方法名：findContents </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月11日-下午1:48:07 </br>
	 * @param params
	 * @param pageInfo
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@Override
	public List<Content> findContents(TzParams params, TzPageInfo pageInfo) {
		return getContentDao().findContents(params, pageInfo);
	}

	/**
	 * 统计数量</br>
	 * com.tz.service.content.impl </br>
	 * 方法名：countCotent </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月11日-下午1:48:07 </br>
	 * @param params
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@Override
	public int countCotent(TzParams params) {
		return getContentDao().countCotent(params);
	}

	
}
