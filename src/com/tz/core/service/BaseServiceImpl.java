/**
 * tzdesk系统平台
 * cms
 * com.tz.core.service
 * IBaseServiceImpl.java
 * 创建人:maerhuan 
 * 时间：2017年3月4日-下午10:23:14 
 * 2017潭州教育公司-版权所有
 */
package com.tz.core.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tz.core.dao.IBaseDao;

/**
 * 
 * BaseServiceImpl
 * 创建人:maerhuan 
 * 时间：2017年3月4日-下午10:24:04 
 * @version 1.0.0
 * 
 */
public class BaseServiceImpl<T extends Serializable,PK extends Serializable> implements IBaseService<T,PK> {

	protected Logger log = LoggerFactory.getLogger(getClass());

	
	private IBaseDao<T,PK> baseDao ;

	public IBaseDao<T,PK> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao<T,PK> baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public T save(T t) {
		return baseDao.save(t);
	}

	@Override
	public T get(PK id) {
		return baseDao.get(id);
	}

	@Override
	public T load(PK id) {
		return baseDao.load(id);
	}

	@Override
	public void delete(T entity) {
		baseDao.delete(entity);
	}

	@Override
	public T deleteById(PK id) {
		return baseDao.deleteById(id);
	}

	@Override
	public T update(T entity) {
		return baseDao.update(entity);
	}

	@Override
	public List<T> find(String sql, Object... args) {
		return baseDao.find(sql, args);
	}

	/**
	 * 更新对象的方法</br>
	 * com.tz.core.service </br>
	 * 方法名：updateDefault </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月12日-下午10:53:29 </br>
	 * @param entity
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@Override
	public T updateDefault(T entity) {
		return baseDao.updateDefault(entity);
	}


}
