package com.chiansofit.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.chiansofit.dao.IPermissionDao;
import com.chiansofit.dao.IResourceDao;
import com.chiansofit.dao.IRoleDao;
import com.chiansofit.dao.IUserDao;
import com.chiansofit.dao.impl.PermissionDaoImpl;
import com.chiansofit.dao.impl.ResourceDaoImpl;
import com.chiansofit.dao.impl.RoleDaoImpl;
import com.chiansofit.dao.impl.UserDaoImpl;
import com.chiansofit.entity.Permission;
import com.chiansofit.entity.Resource;
import com.chiansofit.entity.Role;
import com.chiansofit.entity.User;
import com.chiansofit.factory.DaoFactory;

public class Service {
	private IPermissionDao permissionDao = new PermissionDaoImpl();
	private IResourceDao resourceDao = new ResourceDaoImpl();
	private IRoleDao roleDao = new RoleDaoImpl();
	private IUserDao userDao = new UserDaoImpl();

	// 权限
	public void insertPermission(Permission permission) {
		permission.setId(UUID.randomUUID().toString());
		permissionDao.insertPermission(permission);
	}

	public void deletePermission(String id) {
		permissionDao.deletePermission(id);
	}

	public Permission findPermissionById(String id) {
		return permissionDao.findPermissionById(id);
	}

	public List<Permission> findAllPermission() {
		return permissionDao.findAllPermission();
	}

	// 资源
	public void insertResource(Resource resource, String permissionId) {
		Permission permission = findPermissionById(permissionId);
		resource.setPermission(permission);
		resource.setId(UUID.randomUUID().toString());
		resourceDao.insertResource(resource);
	}

	public void updateResource(Resource resource, String permissionId) {
		Permission permission = findPermissionById(permissionId);
		resource.setPermission(permission);
		resourceDao.updateResource(resource);
	}

	public List<Resource> findAllResource() {
		return resourceDao.findAllResource();
	}

	public Resource findResourceByURI(String uri) {
		return resourceDao.findResourceByURI(uri);
	}

	public Resource findResourceById(String id) {
		return resourceDao.findResourceById(id);
	}

	public void deleteResource(String id) {
		resourceDao.deleteResource(id);
	}

	// 角色
	public void insertRole(Role role, String[] permissionId) {
		Set<Permission> permissions = new HashSet<Permission>();
		for (int i = 0; permissionId != null && i < permissionId.length; i++) {
			Permission permission = findPermissionById(permissionId[i]);
			permissions.add(permission);
		}
		role.setPermissions(permissions);
		role.setId(UUID.randomUUID().toString());
		roleDao.insertRole(role);
	}

	public void updateRole(Role role, String[] permissionId) {
		Set<Permission> permissions = new HashSet<Permission>();
		for (int i = 0; permissionId != null && i < permissionId.length; i++) {
			Permission permission = findPermissionById(permissionId[i]);
			permissions.add(permission);
		}
		role.setPermissions(permissions);
		roleDao.updateRole(role);
	}

	public void deleteRole(String id) {
		roleDao.deleteRole(id);
	}

	public Role findRoleById(String id) {
		return roleDao.findRoleById(id);
	}

	public List<Role> fineAllRole() {
		return roleDao.fineAllRole();
	}

	// 用户
	public void insertUser(User user, String[] roleId) {
		Set<Role> roles = new HashSet<Role>();
		for (int i = 0; roleId != null && i < roleId.length; i++) {
			Role role = roleDao.findRoleById(roleId[i]);
			roles.add(role);
		}
		user.setRoles(roles);
		user.setId(UUID.randomUUID().toString());
		userDao.insertUser(user);
	}

	public void updateUser(User user, String[] roleId) {
		user.setUsername(findUserById(user.getId()).getUsername());
		user.setPassword(findUserById(user.getId()).getPassword());
		Set<Role> roles = new HashSet<Role>();
		for (int i = 0; roleId != null && i < roleId.length; i++) {
			Role role = roleDao.findRoleById(roleId[i]);
			roles.add(role);
		}
		user.setRoles(roles);
		userDao.updateUser(user);
	}

	public void deleteUser(String id) {
		userDao.deleteUser(id);
	}

	public User findUserById(String id) {
		return userDao.findUserById(id);
	}

	public List<User> findAllUser() {
		return userDao.findAllUser();
	}

	public User login(String username, String password) {
		return userDao.login(username, password);
	}

	public List<Permission> getUserPermission(User user) {
		List<Permission> list = new ArrayList<Permission>();
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			list.addAll(findRoleById(role.getId()).getPermissions());
		}
		return list;
	}
}
