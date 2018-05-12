/**
 * 
 */
package com.chiansofit.dao;

import java.util.List;

import com.chiansofit.entity.User;

/**
 * 用戶表
 * @author maple
 *
 */
public interface IUserDao {

    // 插入用户  
    void insertUser(User user);  
  
    // 更新用户  
    void updateUser(User user);  
  
    // 删除用户  
    void deleteUser(String id);  
  
    // 根据id查找用户  
    User findUserById(String id);  
  
    // 查找所有用户  
    List<User> findAllUser();  
      
    User login(String username, String password);  
}
