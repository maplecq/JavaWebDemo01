package com.chiansofit.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.chiansofit.dao.IResourceDao;
import com.chiansofit.entity.Permission;
import com.chiansofit.entity.Resource;
import com.chiansofit.util.DBUtils;

public class ResourceDaoImpl implements IResourceDao {

	public void insertResource(Resource resource) {
		QueryRunner runner = new QueryRunner(DBUtils.getDataSource());
		String sql = "insert into resource (id,uri,description,permission_id) values(?,?,?,?)";
		Object[] params = { resource.getId(), resource.getUri(), resource.getDescription(),
				resource.getPermission().getId() };
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void updateResource(Resource resource) {
		QueryRunner runner = new QueryRunner(DBUtils.getDataSource());
		String sql = "update resource set uri=?,description=?,permission_id=? where id=?";
		Object[] params = { resource.getUri(), resource.getDescription(), resource.getPermission().getId(),
				resource.getId() };
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Resource> findAllResource() {
		List<Resource> list = null;
		QueryRunner runner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select id,uri,description from resource";
		try {
			list = (List<Resource>) runner.query(sql, new BeanListHandler(Resource.class));
			for (Resource resource : list) {
				sql = "select p.id,p.name,p.description from permission p,resource r where r.permission_id=p.id and r.id=?";
				Object[] params = { resource.getId() };
				Permission permission = (Permission) runner.query(sql, new BeanHandler(Permission.class), params);
				resource.setPermission(permission);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public Resource findResourceByURI(String uri) {
		QueryRunner runner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select id,uri,description from resource where uri=?";
		Object[] params = { uri };
		try {
			Resource resource = (Resource) runner.query(sql, new BeanHandler(Resource.class), params);
			if (resource == null)
				return null;
			sql = "select p.id,p.name,p.description from permission p,resource r where r.permission_id=p.id and r.id=?";
			params = new Object[] { resource.getId() };
			Permission permission = (Permission) runner.query(sql, new BeanHandler(Permission.class), params);
			resource.setPermission(permission);
			return resource;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Resource findResourceById(String id) {
		QueryRunner runner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select id,uri,description from resource where id=?";
		Object[] params = { id };
		try {
			Resource resource = (Resource) runner.query(sql, new BeanHandler(Resource.class), params);
			sql = "select p.id,p.name,p.description from permission p,resource r where r.permission_id=p.id and r.id=?";
			params = new Object[] { resource.getId() };
			Permission permission = (Permission) runner.query(sql, new BeanHandler(Permission.class), params);
			resource.setPermission(permission);
			return resource;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteResource(String id) {
		 QueryRunner runner = new QueryRunner(DBUtils.getDataSource());  
	        String sql = "delete from resource where id=?";  
	        Object[] params = { id };  
	        try {  
	            runner.update(sql, params);  
	        } catch (SQLException e) {  
	            throw new RuntimeException(e);  
	        }  

	}

}
