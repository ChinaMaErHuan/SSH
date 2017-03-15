package com.tz.web.permission;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tz.core.action.BaseAction;
import com.tz.model.Permission;
import com.tz.service.permission.IPermissionService;
import com.tz.util.TzStringUtils;

/**
 * 
 * 老师用户 PermissionAction 创建人:maerhuan 时间：2017年03月06日 23:57:47
 * 
 * @version 1.0.0
 * 
 */
@Controller("permissionAction")
@Scope("prototype")
public class PermissionAction extends BaseAction{

	@Autowired
	private IPermissionService permissionService;

	private Permission permission;
	/* 返回结果 */
	private String root;
	private String children;
	HashMap<String, List<HashMap<String, Object>>> map = new HashMap<String, List<HashMap<String, Object>>>();

	/**
	 * 
	 * 保存 创建人：keke 时间：2015年05月07日 23:16:41
	 * 
	 * @return String
	 * @throws JSONException
	 * @exception
	 * @since 1.0.0 map.put("opid", permission.getId()); map.put("name",
	 *        permission.getName()); map.put("url", "javascript:void(0);");
	 *        map.put("pid", permission.getId());
	 */
	public String list() throws JSONException {
		// 获取session是否已经值了
		String sessionRoot = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("root");
		String sessionChildren = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("children");
		if (TzStringUtils.isEmpty(sessionRoot)) {
			// 查询所有的父节点
			List<HashMap<String, Object>> roots = permissionService.findRoot();
			if (roots != null && roots.size() > 0) {
				getPermissions(roots);
				root = JSONUtil.serialize(roots);
				children = JSONUtil.serialize(map);// 第一级子类
				ServletActionContext.getRequest().getSession()
						.setAttribute("root", root);
				ServletActionContext.getRequest().getSession()
						.setAttribute("children", children);
				map = null;
				roots = null;
			}
		} else {
			root = sessionRoot;
			children = sessionChildren;
		}
//		System.out.println(root);
//		System.out.println(children);
		return LIST;
	}


	// 我定义
	// 查询所有的根据子目录
	// action中不允许使用get开头方法名出了，除非属性getter以外，
	@JSON(serialize = false)
	private List<HashMap<String, Object>> getPermissions(List<HashMap<String, Object>> roots) {
		for (HashMap<String, Object> hashMap : roots) {// 根据父节点查询所有父节点对应子节点
			Integer parentId = (Integer) hashMap.get("opid");
			List<HashMap<String, Object>> childrens = permissionService
					.findChildrens(parentId);
			if (childrens != null) {
				map.put(String.valueOf(parentId), childrens);
				getPermissions(childrens);// 递归就在这里
			}
		}
		return roots;
	}

	// setter/getter
	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getChildren() {
		return children;
	}

	public void setChildren(String children) {
		this.children = children;
	}
}
