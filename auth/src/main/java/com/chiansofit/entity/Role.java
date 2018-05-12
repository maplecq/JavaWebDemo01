package com.chiansofit.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色表<br/>
 *  角色      <----->     权限       多对多 
 * @author maple
 *
 */
public class Role {
	private String id;//编号
	private String name;//角色名
	private String description;//角色描述 
	private Set<Permission> permissions;//该角色具有的权限  

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	public Role() {
		super();
		this.permissions = new HashSet<Permission>();
	}
}
