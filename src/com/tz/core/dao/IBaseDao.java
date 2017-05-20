package com.tz.core.dao;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * 
 * BaseDaoImpl
 * 创建人:maerhuan
 * @version 1.0.0
 *
 */
public interface IBaseDao<T,PK extends Serializable> {

	/**
	 * 
	 * (这里用一句话描述这个方法的作用)</br>
	 * com.tz.core.dao </br>
	 * 方法名：save </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年5月17日-下午7:04:57 </br>
	 * @param t
	 * @return T
	 * @exception 
	 * @since  1.0.0
	 */
	public T save(T t); 
	
	/**
	 * 根据主键获取实体
	 * 方法名：get
	 * 创建人：maerhuan
	 * @param id
	 * @return T
	 * @exception 
	 * @since  1.0.0
	 */
	public T get(PK id) ;
	
	/**
	 * 根据主键获取实体
	 * 方法名：load
	 * 创建人：maerhuan
	 * @param id
	 * @return T
	 * @exception 
	 * @since  1.0.0
	 */
	public T load(PK id) ;
	
	/**
	 * 根据主键删除
	 * 方法名：get
	 * 创建人：maerhuan
	 * @param id
	 * @return T
	 * @exception 
	 * @since  1.0.0
	 */
	public void delete(T entity);
	
	/**
	 * 根据主键获取实体
	 * 方法名：get
	 * 创建人：maerhuan
	 * @param id
	 * @return T
	 * @exception 
	 * @since  1.0.0
	 */
	public T deleteById(PK id);
	
	public T update(T entity);
	
	public T updateDefault(T entity);
	
	public List<T> find(String sql ,Object...args);
	
}
