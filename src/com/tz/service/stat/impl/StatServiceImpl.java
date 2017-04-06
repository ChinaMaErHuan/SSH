package com.tz.service.stat.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tz.core.dao.TzParams;
import com.tz.core.service.BaseServiceImpl;
import com.tz.dao.stat.IStatDao;
import com.tz.model.Stat;
import com.tz.service.stat.IStatService;
import com.tz.util.TzPageInfo;

/**
 * 
 * 统计管理
 * StatServiceImpl 
 * 创建人:maerhuan 
 * 时间：2017年04月06日 19:18:56
 * 
 * @version 1.0.0
 * 
 */
@Service
public class StatServiceImpl extends BaseServiceImpl<Stat,Integer>  implements IStatService {
	
	public IStatDao getStatDao() {
		return (IStatDao) super.getBaseDao();
	}
	
	@Autowired
	public void setStatDao(IStatDao statDao) {
		super.setBaseDao(statDao);
	}

	/**
	 * 查询分页</br>
	 * com.tz.service.stat.impl </br>
	 * 方法名：findStats </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:33:48 </br>
	 * @param params
	 * @param pageInfo
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@Override
	public List<Stat> findStats(TzParams params, TzPageInfo pageInfo) {
		return getStatDao().findStats(params, pageInfo);
	}

	/**
	 * 计算总数</br>
	 * com.tz.service.stat.impl </br>
	 * 方法名：countStat </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月6日-下午10:33:48 </br>
	 * @param params
	 * @return
	 * @exception 
	 * @since  1.0.0
	*/
	@Override
	public int countStat(TzParams params) {
		return getStatDao().countStat(params);
	}
	
}
