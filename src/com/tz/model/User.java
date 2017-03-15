/**
 * tzdesk系统平台
 * CMS
 * com.tz.model
 * User.java
 * 创建人:maerhuan 
 * 时间：2017年2月24日-下午4:39:28 
 * 2017潭州教育公司-版权所有
 */
package com.tz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * 
 * User 创建人:maerhuan 时间：2017年2月27日-下午9:11:18
 * 
 * @version 1.0.0
 */
@Entity
@Table(name = "tz_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;// 主键
	private String account;// 账号
	private String password;// 密码
	private String username;// 昵称
	private Date createTime;// 创建时间
	private List<Role> roles = new ArrayList<Role>(0);

	public User() {

	}

	
	public User(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "account", length = 100, nullable = false)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "password", length = 100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "username", length = 100)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL)
	@JoinTable(name = "tz_role_user", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
