package com.chiansofit.dao.impl;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.chiansofit.dao.IUserDao;
import com.chiansofit.entity.Role;
import com.chiansofit.entity.User;
import com.chiansofit.util.DBUtils;

public class UserDaoImpl implements IUserDao {

	public void insertUser(User user) {
		 QueryRunner runner = new QueryRunner(DBUtils.getDataSource());  
	        String sql = "insert into user (id,username,password) values(?,?,?)";  
	        Object[] params = { user.getId(), user.getUsername(),  
	                user.getPassword() };  
	        try {  
	            runner.update(sql, params);  
	            Set<Role> roles = user.getRoles();  
	            sql = "insert into user_role (user_id,role_id) values(?,?)";  
	            for (Role role : roles) {  
	                params = new Object[] { user.getId(), role.getId() };  
	                runner.update(sql, params);  
	            }  
	        } catch (SQLException e) {  
	            throw new RuntimeException(e);  
	        }  

	}

	public void updateUser(User user) {
		 QueryRunner runner = new QueryRunner(DBUtils.getDataSource());  
	        String sql = "delete from user_role where user_id=?";  
	        try {  
	            runner.update(sql, user.getId());  
	            sql = "update user set username=?,password=? where id=?";  
	            Object[] params = { user.getUsername(), user.getPassword(),  
	                    user.getId() };  
	            runner.update(sql, params);  
	            sql = "insert into user_role (user_id,role_id) values(?,?)";  
	            Set<Role> roles = user.getRoles();  
	            for (Role role : roles) {  
	                params = new Object[] { user.getId(), role.getId() };  
	                runner.update(sql, params);  
	            }  
	        } catch (SQLException e) {  
	            throw new RuntimeException(e);  
	        }  

	}

	public void deleteUser(String id) {
		 QueryRunner runner = new QueryRunner(DBUtils.getDataSource());  
	        String sql = "delete from user_role where user_id=?";  
	        try {  
	            runner.update(sql, id);  
	            sql = "delete from user where id=?";  
	            runner.update(sql, id);  
	        } catch (SQLException e) {  
	            throw new RuntimeException(e);  
	        }  

	}

	public User findUserById(String id) {
		  QueryRunner runner = new QueryRunner(DBUtils.getDataSource());  
	        String sql = "select id,username,password from user where id=?";  
	        Object[] params = { id };  
	        try {  
	            User user = (User) runner.query(sql, new BeanHandler(User.class),  
	                    params);  
	            sql = "select r.id,r.name,r.description from role r,user_role ur where r.id=ur.role_id and ur.user_id=?";  
	            params = new Object[] { id };  
	            List<Role> list = (List<Role>) runner.query(sql,  
	                    new BeanListHandler(Role.class), params);  
	            Set<Role> set = new HashSet<Role>();  
	            set.addAll(list);  
	            user.setRoles(set);  
	            return user;  
	        } catch (SQLException e) {  
	            throw new RuntimeException(e);  
	        }  
	}

	public List<User> findAllUser() {
		 List<User> list = null;  
	        QueryRunner runner = new QueryRunner(DBUtils.getDataSource());  
	        String sql = "select id,username,password from user";  
	        try {  
	            list = (List<User>) runner.query(sql, new BeanListHandler(  
	                    User.class));  
	            sql = "select r.id,r.name,r.description from role r,user_role ur where r.id=ur.role_id and ur.user_id=?";  
	            for (User user : list) {  
	                Object[] params = new Object[] { user.getId() };  
	                Set<Role> set = new HashSet<Role>();  
	                set.addAll((List<Role>) runner.query(sql, new BeanListHandler(  
	                        Role.class), params));  
	                user.setRoles(set);  
	            }  
	        } catch (SQLException e) {  
	            throw new RuntimeException(e);  
	        }  
	        return list;  
	}

	public User login(String username, String password) {
		 User user = null;  
	        QueryRunner runner = new QueryRunner(DBUtils.getDataSource());  
	        String sql = "select id,username,password from user where username=? and password=?";  
	        Object[] params = { username, password };  
	        try {  
	            user = (User) runner  
	                    .query(sql, new BeanHandler(User.class), params);  
	            if (user != null) {  
	                sql = "select r.id,r.name,r.description from role r, user_role ur where r.id=ur.role_id and ur.user_id=?";  
	                params = new Object[] { user.getId() };  
	                Set<Role> set = new HashSet<Role>();  
	                set.addAll((List<Role>) runner.query(sql, new BeanListHandler(  
	                        Role.class), params));  
	                user.setRoles(set);  
	            }  
	        } catch (SQLException e) {  
	            throw new RuntimeException(e);  
	        }  
	        return user;  
	}

}
