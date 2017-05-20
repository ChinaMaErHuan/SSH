/**
 * Tm系统平台
 * TzCms
 * com.tz.service.ws
 * IUserService.java
 * 创建人:xuchengfei 
 * 时间：2015年5月28日-下午9:40:51 
 * 2015Tm公司-版权所有
 */
package com.tz.service.ws;

import java.util.List;

import com.tz.model.User;

/**
 * 
 * 
 * IUserServiceWeb
 * 创建人:maerhuan 
 * 时间：2017年5月14日-下午8:20:14 
 * @version 1.0.0
 *
 */
public interface IUserServiceWeb {

	public List<User> findUsers();

	public boolean saveUser(User user);
}
