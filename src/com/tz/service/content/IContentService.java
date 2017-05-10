/**
 * tzdesk系统平台
 * TzSSH
 * com.tz.service.content
 * IContentService.java
 * 创建人:maerhuan 
 * 时间：2017年2月21日-下午3:54:02 
 * 2017潭州教育公司-版权所有
 */
package com.tz.service.content;

import java.util.List;

import com.tz.core.dao.TzParams;
import com.tz.core.service.IBaseService;
import com.tz.model.Content;
import com.tz.util.TzPageInfo;
/**
 * 
 * 
 * IContentService
 * 创建人:maerhuan 
 * 时间：2017年2月21日-下午3:54:40 
 * @version 1.0.0
 *
 */
 public interface IContentService extends IBaseService<Content, Integer>{
	
	/**
	 * 
	 * 查询并且分页</br>
	 * com.tz.service.content </br>
	 * 方法名：findContents </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月11日-下午1:47:25 </br>
	 * @param params
	 * @param pageInfo
	 * @return List<Content>
	 * @exception 
	 * @since  1.0.0
	 */
	 List<Content> findContents(TzParams params,TzPageInfo pageInfo);
	/**
	 * 
	 * 统计数量</br>
	 * com.tz.service.content </br>
	 * 方法名：countCotent </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月11日-下午1:47:42 </br>
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	 int countCotent(TzParams params);
}
