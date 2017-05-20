package com.tz.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

/**
 * 
 * 统计管理 Stat 创建人:maerhuan 时间：2017年04月06日 19:18:56
 * 
 * @version 1.0.0
 * 
 */
@Entity
@Table(name = "tz_stat")
public class Stat implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;// 主键
	private String className;// 类名
	private Long timer;// 耗时
	private String method;// 名称
	private String name;// 访问模块名称
	private String log;// 操作的日记描述
	private String params;// 参数
	private Integer isDelete;// 删除状态0未删除1删除
	private Date createTime;// 创建时间
	private Date updateTime;// 更新时间
	private User user;// 操作用户

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "is_delete", length = 1)
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "create_time", columnDefinition = "timestamp")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@JSON(serialize = false)
	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "class_name", length = 200)
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "timer")
	public Long getTimer() {
		return timer;
	}

	public void setTimer(Long timer) {
		this.timer = timer;
	}

	@Column(name = "method", length = 50)
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Column(name = "params",length = 1000)
	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	@Column(name = "log")
	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}
}