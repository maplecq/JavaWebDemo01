package com.chiansofit.entity;
/**
 * 资源<br/>
 * 资源      ------>      权限      一对多  
 * @author maple
 *
 */
public class Resource {
	private String id;
	private String uri; //资源uri 
	private String description;//描述 
	private Permission permission;// 该资源需要的权限 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
}
