/**
 * 
 */
package com.chiansofit.dao;

import java.util.List;

import com.chiansofit.entity.Role;

/**
 * 角色表
 * @author maple
 *
 */
public interface IRoleDao {
	// 新增角色  
    void insertRole(Role role);  
  
    // 更新角色  
    void updateRole(Role role);  
  
    // 删除角色  
    void deleteRole(String id);  
  
    // 根据id查找角色  
    Role findRoleById(String id);  
  
    // 查找所有角色  
    List<Role> fineAllRole();  

}
