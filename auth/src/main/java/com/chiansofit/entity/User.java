package com.chiansofit.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户表<br/>
 *  用户      <----->       角色     多对多  
 * @author maple
 *
 */
public class User {
	private String id;//编号
	private String username;
	private String password;
	private Set<Role> roles;//该用户都具有的角色 
	
	public User(){
		super();
		this.roles = new HashSet<Role>();
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
