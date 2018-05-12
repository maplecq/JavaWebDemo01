package com.chiansofit.dao.impl;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.chiansofit.dao.IRoleDao;
import com.chiansofit.entity.Permission;
import com.chiansofit.entity.Role;
import com.chiansofit.util.DBUtils;

public class RoleDaoImpl implements IRoleDao {

	public void insertRole(Role role) {
		 QueryRunner runner = new QueryRunner(DBUtils.getDataSource());  
	        String sql = "insert into role (id,name,description) values(?,?,?)";  
	        Object[] params = { role.getId(), role.getName(), role.getDescription() };  
	        try {  
	            runner.update(sql, params);  
	            sql = "insert into permission_role (permission_id,role_id) values(?,?)";  
	            Set<Permission> set = role.getPermissions();  
	            for (Permission permission : set) {  
	                params = new Object[] { permission.getId(), role.getId() };  
	                runner.update(sql, params);  
	            }  
	        } catch (SQLException e) {  
	            throw new RuntimeException(e);  
	        }  

	}

	public void updateRole(Role role) {
		 QueryRunner runner = new QueryRunner(DBUtils.getDataSource());  
	        Set<Permission> set = role.getPermissions();  
	        String sql = "delete from permission_role where role_id=?";  
	        try {  
	            runner.update(sql, role.getId());  
	            sql = "update role set name=?,description=? where id=?";  
	            Object[] params = { role.getName(), role.getDescription(),  
	                    role.getId() };  
	            runner.update(sql, params);  
	            sql = "insert into permission_role (permission_id,role_id) values(?,?)";  
	            for (Permission permission : set) {  
	                params = new Object[] { permission.getId(), role.getId() };  
	                runner.update(sql, params);  
	            }  
	        } catch (SQLException e) {  
	            throw new RuntimeException(e);  
	        }  

	}

	public void deleteRole(String id) {
		 QueryRunner runner = new QueryRunner(DBUtils.getDataSource());  
	        String sql = "delete from permission_role where role_id=?";  
	        try {  
	            runner.update(sql, id);  
	            sql = "delete from role where id=?";  
	            runner.update(sql, id);  
	        } catch (SQLException e) {  
	            throw new RuntimeException(e);  
	        }  

	}

	public Role findRoleById(String id) {
		   QueryRunner runner = new QueryRunner(DBUtils.getDataSource());  
	        String sql = "select id,name,description from role where id=?";  
	        Object[] params = { id };  
	        try {  
	            Role role = (Role) runner.query(sql, new BeanHandler(Role.class),  
	                    params);  
	            sql = "select p.id,p.name,p.description from permission p,permission_role pr where p.id=pr.permission_id and pr.role_id=?";  
	            params = new Object[] { id };  
	            Set<Permission> set = new HashSet<Permission>();  
	            set.addAll((List<Permission>) runner.query(sql,  
	                    new BeanListHandler(Permission.class), params));  
	            role.setPermissions(set);  
	            return role;  
	        } catch (SQLException e) {  
	            throw new RuntimeException(e);  
	        }  
	}

	public List<Role> fineAllRole() {
		 List<Role> list = null;  
	        QueryRunner runner = new QueryRunner(DBUtils.getDataSource());  
	        String sql = "select id,name,description from role";  
	        try {  
	            list = (List<Role>) runner.query(sql, new BeanListHandler(  
	                    Role.class));  
	            sql = "select p.id,p.name,p.description from permission p,permission_role pr where p.id=pr.permission_id and pr.role_id=?";  
	            for (Role role : list) {  
	                Object[] params = new Object[] { role.getId() };  
	                Set<Permission> set = new HashSet<Permission>();  
	                set.addAll((List<Permission>) runner.query(sql,  
	                        new BeanListHandler(Permission.class), params));  
	                role.setPermissions(set);  
	            }  
	        } catch (SQLException e) {  
	            throw new RuntimeException(e);  
	        }  
	        return list;  
	}

}
