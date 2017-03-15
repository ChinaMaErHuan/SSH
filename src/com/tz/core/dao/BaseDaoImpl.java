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
 * BaseDaoImpl 创建人:maerhuan 时间：2017年2月24日-下午3:44:33
 * 
 * @version 1.0.0
 * 
 */
@Transactional
public class BaseDaoImpl<T extends Serializable, PK extends Serializable> {

	@Autowired
	private SessionFactory sessionFactory;

	protected Class<T> entityClass;

	/**
	 * 无参构造函数获取注入的实体
	 */
	public BaseDaoImpl() {
		this.entityClass = TzReflectionUtils
				.getSuperClassGenricType(getClass());
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * 
	 * 获取连接 com.tz.core.dao 方法名：getSession 创建人：maerhuan 时间：2017年2月24日-下午3:44:43
	 * 
	 * @return Session
	 * @exception
	 * @since 1.0.0
	 */
	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	
	/**
	 * 
	 * 保存</br> com.tz.core.dao </br> 方法名：save </br> 创建人：maerhuan </br>
	 * 时间：2017年3月4日-下午10:10:36 </br>
	 * 
	 * @param t
	 * @return T
	 * @exception
	 * @since 1.0.0
	 */
	public T save(T t) {
		try {
			getSession().save(t);
			
			return t;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * 根据id获取bean</br> com.tz.core.dao </br> 方法名：get </br> 创建人：maerhuan </br>
	 * 时间：2017年3月4日-下午10:17:13 </br>
	 * 
	 * @param id
	 * @return T
	 * @exception
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	public T get(PK id) {
		Assert.notNull(id, "id不能为空");
		return (T) getSession().get(getEntityClass(), id);
	}

	/**
	 * 
	 * 根据id获取bean</br> com.tz.core.dao </br> 方法名：get </br> 创建人：maerhuan </br>
	 * 时间：2017年3月4日-下午10:17:13 </br>
	 * 
	 * @param id
	 * @return T
	 * @exception
	 * @since 1.0.0
	 */
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

	/**
	 * 
	 * 删除对象</br> com.tz.core.dao </br> 方法名：delete </br> 创建人：maerhuan </br>
	 * 时间：2017年3月4日-下午10:17:54 </br>
	 * 
	 * @param entity
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
	public void delete(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
	}

	// 根据主键id删除 返回实体
	public T deleteById(PK id) {
		Assert.notNull(id, "id不能为空");
		T entity = load(id);
		getSession().delete(entity);
		return entity;
	}

	/**
	 * 
	 * 更新对象</br> com.tz.core.dao </br> 方法名：update </br> 创建人：maerhuan </br>
	 * 时间：2017年3月4日-下午10:18:42 </br>
	 * 
	 * @param entity
	 * @return T
	 * @exception
	 * @since 1.0.0
	 */
	public T update(T entity) {
		getSession().update(entity);
		return entity;
	}

	/**
	 * 
	 * 更新对象</br> com.tz.core.dao </br> 方法名：updateDefault </br> 创建人：maerhuan
	 * </br> 时间：2017年3月12日-下午11:10:47 </br>
	 * 
	 * @param entity
	 * @return T
	 * @exception
	 * @since 1.0.0
	 */
	public T updateDefault(T entity) {
		return updateByUpdater(TzUpdater.create(entity));
	}

	/**
	 * 
	 * 查询多个对象返回list</br> com.tz.core.dao </br> 方法名：find </br> 创建人：maerhuan </br>
	 * 时间：2017年3月4日-下午10:19:22 </br>
	 * 
	 * @param sql
	 * @param args
	 * @return List<T>
	 * @exception
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object... args) {
		Query query = getSession().createQuery(hql);
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		return query.list();
	}

	/**
	 * 
	 * 获取当前离线查询对象</br> com.tz.core.dao </br> 方法名：getCurrentDetachedCriteria
	 * </br> 创建人：maerhuan </br> 时间：2017年3月11日-上午12:46:38 </br>
	 * 
	 * @return DetachedCriteria
	 * @exception
	 * @since 1.0.0
	 */
	public DetachedCriteria getCurrentDetachedCriteria() {
		return DetachedCriteria.forClass(getEntityClass());
	}
	//查询并且分页
	@SuppressWarnings("unchecked")
	public List<T> findByDetachedCriteria(
			final DetachedCriteria detachedCriteria, final TzPageInfo pageInfo) {
		return detachedCriteria.getExecutableCriteria(getSession())
				.setFirstResult(Integer.parseInt(pageInfo.getFirstResult()))
				.setMaxResults(Integer.parseInt(pageInfo.getMaxResults()))
				.list();
	}
	//查询分页
	@SuppressWarnings("unchecked")
	public List<T> findByDetachedCriteria(
			final DetachedCriteria detachedCriteria, Integer pageNo,
			Integer pageSize) {
		if (pageNo < 1)
			pageNo = 1;
		if (pageSize < 0)
			pageSize = 0;
		return detachedCriteria.getExecutableCriteria(getSession())
				.setFirstResult(pageNo).setMaxResults(pageSize).list();
	}
	
	//获取hibernate的元数据
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
