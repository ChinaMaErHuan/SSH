/**
 * tzdesk系统平台
 * CMS
 * com.tz.model
 * Permission.java
 * 创建人:maerhuan 
 * 时间：2017年2月27日-下午9:09:16 
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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.struts2.json.annotations.JSON;

/**
 * 
 * 权限实体
 * Permission 创建人:maerhuan 时间：2017年2月27日-下午9:11:11
 * 
 * @version 1.0.0
 * 
 */
@Entity
@Table(name = "tz_permission2")
public class Permission implements Serializable {
	// 读写分离：hibernate就是做增删改，mybatis或者springjdbctemplate作为查询
	// 大型项目不建议使用hibernate
	// 如果你真的要使用hibernate那么，表不建立映射关系，而程序里面建立.

	private static final long serialVersionUID = 1L;
	private Integer id;// 主键
	private String name;// 权限名称
	private String url;// 权限链接,所有的功能操作
	private String model;// 访问模块
	private Integer sort;//排序
	private String method;// 模块的什么方法
	private Integer isDelete;//删除状态0未删除1删除
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	private Permission parent;//定义个根权限
	private List<Permission> permissions = new ArrayList<Permission>(0);

	public Permission() {

	}

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

	@Column(name = "url", length = 100)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "model", length = 100)
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "method", length = 50)
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "TIMESTAMP NOT  NULL DEFAULT CURRENT_TIMESTAMP")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="update_time")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	@Column(name="sort",length=3)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	@Column(name="id_delete",length=1)
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@JSON(serialize = false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public Permission getParent() {
		return parent;
	}

	public void setParent(Permission parent) {
		this.parent = parent;
	}

	@JSON(serialize = false)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	//根据用户查询拥有的角色对应的权限
	//把用户所有权限缓存在ehcache中或者session中
	//自定义拦截器 防止用户直接通过浏览器访问你的地址
	//自定义标签控制页面上的权限按钮或者链接是否隐藏和展示
}
