/**
 * tzdesk系统平台
 * TzSSH
 * com.tz.dao.content
 * IContentDao.java
 * 创建人:maerhuan 
 * 时间：2017年2月21日-下午3:42:02 
 * 2017潭州教育公司-版权所有
 */
package com.tz.dao.content;

import java.util.List;

import com.tz.core.dao.IBaseDao;
import com.tz.core.dao.TzParams;
import com.tz.model.Content;
import com.tz.util.TzPageInfo;
/**
 * 
 * 
 * IContentDao
 * 创建人:maerhuan 
 * 时间：2017年2月21日-下午3:44:08 
 * @version 1.0.0
 *
 */
public interface IContentDao extends IBaseDao<Content, Integer> {
	/**
	 * 根据关键字查询出content并且分页</br>
	 * com.tz.dao.content </br>
	 * 方法名：findContents </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月11日-上午12:54:47 </br>
	 * @param params
	 * @param pageInfo
	 * @return List<Content>
	 * @exception 
	 * @since  1.0.0
	 */
	 List<Content> findContents(TzParams params,TzPageInfo pageInfo);
	/**
	 * 
	 * 计算总数</br>
	 * com.tz.dao.content </br>
	 * 方法名：countCotent </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月11日-下午8:40:13 </br>
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	 int countCotent(TzParams params);
	/**
	 * 批量新增</br>
	 * com.tz.dao.content </br>
	 * 方法名：saveBatch </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月23日-下午5:30:05 </br>
	 * @param contents void
	 * @exception 
	 * @since  1.0.0
	*/
	void saveBatch(List<Content> contents);
	/**
	 * 批量处理更新</br>
	 * com.tz.dao.content </br>
	 * 方法名：updateBatch </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月23日-下午5:30:52 </br>
	 * @param contents void
	 * @exception 
	 * @since  1.0.0
	*/
	void updateBatch(List<Content> contents);
	
	
}
