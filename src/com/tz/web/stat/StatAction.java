package com.tz.web.stat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tz.core.Method;
import com.tz.core.action.BaseAction;
import com.tz.core.interceptor.TzRequestMethod;
import com.tz.model.Stat;
import com.tz.model.User;
import com.tz.service.stat.IStatService;

/**
 * 
 * 统计管理
 * StatAction
 * 创建人:keke 
 * 时间：2015年05月17日 01:15:47
 * @version 1.0.0
 *
 */
@Controller("statAction")
@Scope("prototype")
public class StatAction extends BaseAction{

	@Autowired
	private IStatService statService;
	private List<Stat> stats;
	private Stat stat;
	
	
	/**
	 * 
	 * 分页</br>
	 * com.tz.web.stat </br>
	 * 方法名：list </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月7日-下午5:29:24 </br>
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String list(){
		stats = statService.findStats(params, page);
		int itemCount = statService.countStat(params);
		page.setItemCount(String.valueOf(itemCount));
		return "list";
	}
	
	/**
	 * 
	 * 渲染模板</br>
	 * com.tz.web.stat </br>
	 * 方法名：listTemplate </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月7日-下午5:29:36 </br>
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String listTemplate(){
		stats = statService.findStats(params, page);
		int itemCount = statService.countStat(params);
		page.setItemCount(String.valueOf(itemCount));
		return "listTemplate";
	}
	
	/**
	 * 
	 * 编辑</br>
	 * com.tz.web.stat </br>
	 * 方法名：edit </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月7日-下午5:29:47 </br>
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String edit(){
		if(id!=null){
			stat = statService.get(id);
		}
		return "edit";
	}
	
	/**
	 * 
	 * 保存</br>
	 * com.tz.web.stat </br>
	 * 方法名：save </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月7日-下午5:29:57 </br>
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@Method(method=TzRequestMethod.POST)
	public String save(){
		stat.setUser(new User(1));
		
		stat.setIsDelete(0);
		stat = statService.save(stat);
		stat.setUser(null);
		result = "success";
		return AJAX_SUCCESS;
	}
	
	/**
	 * 
	 * 更新</br>
	 * com.tz.web.stat </br>
	 * 方法名：update </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月7日-下午5:30:07 </br>
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@Method(method=TzRequestMethod.POST)
	public String update(){
		stat.setUpdateTime(new Date());
		stat = statService.updateDefault(stat);
		stat=null;
		result ="success";
		return AJAX_SUCCESS;
	}
	
	/**
	 * 
	 * 删除</br>
	 * com.tz.web.stat </br>
	 * 方法名：delete </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年4月7日-下午5:30:16 </br>
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String delete(){
		try {
			stat.setUpdateTime(new Date());
			stat.setIsDelete(1);
			statService.updateDefault(stat);
			result = "success";
		} catch (Exception e) {
			result = "fail";
		}
		return AJAX_SUCCESS;
	}

	//setter/getter
	public Stat getStat() {
		return stat;
	}

	public void setStat(Stat stat) {
		this.stat = stat;
	}

	public List<Stat> getStats() {
		return stats;
	}

	public void setStats(List<Stat> stats) {
		this.stats = stats;
	}
}