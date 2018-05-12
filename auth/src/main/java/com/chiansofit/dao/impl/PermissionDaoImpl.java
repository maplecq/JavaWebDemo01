/**
 * 
 */
package com.chiansofit.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.chiansofit.dao.IPermissionDao;
import com.chiansofit.entity.Permission;
import com.chiansofit.util.DBUtils;

/**
 * 权限实现类
 * 
 * @author maple
 *
 */
public class PermissionDaoImpl implements IPermissionDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chiansofit.dao.IPermissionDao#insertPermission(com.chiansofit.entity.
	 * Permission)
	 */
	public void insertPermission(Permission permission) {
		QueryRunner runner = new QueryRunner(DBUtils.getDataSource());
		String sql = "insert into permission (id,name,description) values(?,?,?)";
		Object[] params = { permission.getId(), permission.getName(), permission.getDescription() };
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chiansofit.dao.IPermissionDao#deletePermission(java.lang.String)
	 */
	public void deletePermission(String id) {
		// TODO Auto-generated method stub
		QueryRunner runer = new QueryRunner(DBUtils.getDataSource());
		String sql = "update resource set permission_id=null where permission_id=?";
		try {
			runer.update(sql, id);
			sql = "delete from permission where id=?";
			runer.update(sql, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chiansofit.dao.IPermissionDao#findPermissionById(java.lang.String)
	 */
	public Permission findPermissionById(String id) {
		 QueryRunner runer = new QueryRunner(DBUtils.getDataSource());  
	        String sql = "select id,name,description from permission where id=?";  
	        Object[] params = { id };  
	        try {  
	            return (Permission) runer.query(sql, new BeanHandler(  
	                    Permission.class), params);  
	        } catch (SQLException e) {  
	            throw new RuntimeException(e);  
	        }  
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chiansofit.dao.IPermissionDao#findAllPermission()
	 */
	public List<Permission> findAllPermission() {
		 List<Permission> list = null;  
	        QueryRunner runer = new QueryRunner(DBUtils.getDataSource());  
	        String sql = "select id,name,description from permission";  
	        try {  
	            list = (List<Permission>) runer.query(sql, new BeanListHandler(  
	                    Permission.class));  
	        } catch (SQLException e) {  
	            throw new RuntimeException(e);  
	        }  
	        return list;  
	}

}
