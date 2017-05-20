package com.tz.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tz.util.TzPageInfo;

/**
 * 
 * 
 * BaseDaoImpl
 * 创建人:keke
 * 时间：2015年4月29日-上午12:03:20 
 * @version 1.0.0
 *
 */
@Transactional
public class BaseDaoImpl<T,PK extends Serializable> {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Class<T> entityClass;
	/**
	 * 无参构造函数获取注入的实体
	 */
	public BaseDaoImpl() {
		this.entityClass = TzReflectionUtils.getSuperClassGenricType(getClass());
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}
	
	

	/**
	 * 获取连接对象
	 * 方法名：getSession
	 * 创建人：xuchengfei 时间：2015年4月29日-上午12:03:46 
	 * @return Session
	 * @exception 
	 * @since  1.0.0
	 */
	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	
	
	public T save(T t){
		try {
			getSession().save(t);
			return t;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public T get(PK id) {
		Assert.notNull(id, "id不能为空");
		return (T) getSession().get(getEntityClass(), id);
	}
	
	public T load(PK id) {
		Assert.notNull(id, "id不能为空");
		return load(id, false);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public T load(PK id, boolean lock) {
		Assert.notNull(id, "id不能为空");
		T entity = null;
		if (lock) {
			entity = (T) getSession().load(getEntityClass(), id,
					LockMode.UPGRADE);
		} else {
			entity = (T) getSession().load(getEntityClass(), id);
		}
		return entity;
	}

	// 删除对象方法
	public void delete(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
	}
	
	public T deleteById(PK id) {
		Assert.notNull(id, "id不能为空");
		T entity = load(id);
		getSession().delete(entity);
		return entity;
	}
	
	public T update(T entity) {
		getSession().update(entity);
		return entity;
	}
	
	
	/**
	 * 简单通用通配符查询
	 * 方法名：find
	 * 创建人：xuchengfei 
	 * 时间：2015年5月6日-上午12:49:30 
	 * @param sql
	 * @param args
	 * @return List<T>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<T> find(String sql ,Object...args){
		Query query = getSession().createQuery(sql);
		if(args!=null && args.length>0){
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		return query.list();
	}
	
	/**
	 * 获取当前离线查询对象
	 * @author xuchengfei
	 * @date 2013-5-18 下午10:04:30
	 * @modifyNote
	 * @return
	 */
	public DetachedCriteria getCurrentDetachedCriteria() {
		return DetachedCriteria.forClass(getEntityClass());
	}
	
	public List<T> findByDetachedCriteria(final DetachedCriteria detachedCriteria,final TzPageInfo pageInfo){
		return detachedCriteria.getExecutableCriteria(getSession())
				.setFirstResult(Integer.parseInt(pageInfo.getFirstResult()))
				.setMaxResults(Integer.parseInt(pageInfo.getMaxResults()))
				.list();
	}
	
	
	public List<T> findByDetachedCriteria(final DetachedCriteria detachedCriteria,Integer pageNo,Integer pageSize){
		return detachedCriteria.getExecutableCriteria(getSession())
				.setFirstResult(pageNo)
				.setMaxResults(pageSize)
				.list();
	}
	
	/**
	 * 更新对象
	 * 方法名：updateDefault
	 * 创建人：xuchengfei 时间：2015年3月26日-下午10:33:16 
	 * @param entity
	 * @return T
	 * @exception 
	 * @since  1.0.0
	 */
	public T updateDefault(T entity) {
		return updateByUpdater(TzUpdater.create(entity));
	}
	
	
	@SuppressWarnings("rawtypes")
	private ClassMetadata getClassMetadata(Class clazz) {
		return (ClassMetadata) sessionFactory.getClassMetadata(clazz);
	}
	
	@SuppressWarnings("unchecked")
	public T updateByUpdater(TzUpdater updater) {
		ClassMetadata cm = getClassMetadata(updater.getBean().getClass());
		if (cm == null) {
			throw new RuntimeException("所更新的对象没有映射或不是实体对象");
		}
		Object bean = updater.getBean();
		T po = (T) getSession().load(bean.getClass(),cm.getIdentifier(bean));
		updaterCopyToPersistentObject(updater, po);
		return po;
	}
	
	/**
	 * 将更新对象拷贝至实体对象，并处理many-to-one的更新。
	 * 
	 * @param updater
	 * @param po
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void updaterCopyToPersistentObject(TzUpdater updater, Object po) {
		Map map = TzBeanUtil.describe(updater.getBean());
		Set<Map.Entry<String, Object>> set = map.entrySet();
		for (Map.Entry<String, Object> entry : set) {
			String name = entry.getKey();
			Object value = entry.getValue();
			if (!updater.isUpdate(name, value)) {
				continue;
			}
			if (value != null) {
				Class valueClass = value.getClass();
				ClassMetadata cm = getClassMetadata(valueClass);
				if (cm != null) {
					Serializable vid = cm.getIdentifier(value);
					// 如果更新的many to one的对象的id为空，则将many to one设置为null。
					if (vid != null) {
						value = getSession().load(valueClass, vid);
					} else {
						value = null;
					}
				}
			}
			try {
				PropertyUtils.setProperty(po, name, value);
			} catch (Exception e) {
			}
		}
	}
	
}
