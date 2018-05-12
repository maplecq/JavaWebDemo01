
package com.chiansofit.dao;

import java.util.List;

import com.chiansofit.entity.Permission;

/**
 * 权限接口
 * @author maple
 *
 */
public interface IPermissionDao {
	// 插入新权限  
    void insertPermission(Permission permission);  
  
    // 删除权限  
    void deletePermission(String id);  
  
    // 根据id查找权限  
    Permission findPermissionById(String id);  
  
    // 查找所有权限  
    List<Permission> findAllPermission();
}
