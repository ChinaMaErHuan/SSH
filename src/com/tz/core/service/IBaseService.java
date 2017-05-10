/**
 * tzdesk系统平台
 * cms
 * com.tz.core.service
 * IBaseService.java
 * 创建人:maerhuan 
 * 时间：2017年3月4日-下午10:20:38 
 * 2017潭州教育公司-版权所有
 */
package com.tz.core.service;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T extends Serializable,PK extends Serializable> {
	/**
	 * 
	 * 保存方法</br> com.tz.code.service </br> 方法名：save</br> 创建人：maerhuan </br>
	 * 时间：2017年3月3日-下午11:07:12 </br>
	 * 
	 * @param t
	 * @return T
	 * @exception
	 * @since 1.0.0
	 */
	 T save(T t);

	/**
	 * 根据主键id获取实体</br> com.tz.code.service </br> 方法名：get </br> 创建人：maerhuan </br>
	 * 时间：2017年3月3日-下午11:49:03 </br>
	 * 
	 * @param id
	 * @return T
	 * @exception
	 * @since 1.0.0
	 */

	 T get(PK id);

	/**
	 * 根据主键id获取实体</br> com.tz.code.service </br> 方法名：get </br> 创建人：maerhuan </br>
	 * 时间：2017年3月3日-下午11:49:03 </br>
	 * 
	 * @param id
	 * @return T
	 * @exception
	 * @since 1.0.0
	 */
	 T load(PK id);

	/**
	 * 
	 * 根据主键删除</br> com.tz.code.service </br> 方法名：delete </br> 创建人：maerhuan </br>
	 * 时间：2017年3月4日-上午12:45:28 </br>
	 * 
	 * @param entity
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
	 void delete(T entity);

	/**
	 * 删除</br> com.tz.code.service </br> 方法名：deleteById </br> 创建人：maerhuan </br>
	 * 时间：2017年3月4日-上午12:47:40 </br>
	 * 
	 * @param id
	 * @return T
	 * @exception
	 * @since 1.0.0
	 */
	 T deleteById(PK id);

	/**
	 * 根据主键更新实体</br> com.tz.code.service </br> 方法名：update </br> 创建人：maerhuan </br>
	 * 时间：2017年3月4日-上午12:46:10 </br>
	 * 
	 * @param entity
	 * @return T
	 * @exception
	 * @since 1.0.0
	 */
	 T update(T entity);
	
	
	 T updateDefault(T entity);
	

	/**
	 * 
	 * 查询</br> com.tz.code.service </br> 方法名：find </br> 创建人：maerhuan </br>
	 * 时间：2017年3月4日-下午3:56:00 </br>
	 * 
	 * @param sql
	 * @param args
	 * @return List<T>
	 * @exception
	 * @since 1.0.0
	 */
	 List<T> find(String sql, Object... args);
}
