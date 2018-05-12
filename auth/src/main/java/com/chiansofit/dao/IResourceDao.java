/**
 * 
 */
package com.chiansofit.dao;

import java.util.List;

import com.chiansofit.entity.Resource;

/**
 * 资源表
 * @author maple
 *
 */
public interface IResourceDao {
	 // 增加资源  
    void insertResource(Resource resource);  
  
    // 修改资源  
    void updateResource(Resource resource);  
  
    // 查找所有资源  
    List<Resource> findAllResource();  
  
    // 根据uri查找资源  
    Resource findResourceByURI(String uri);  
  
    // 根据id查找资源  
    Resource findResourceById(String id);  
  
    // 删除资源  
    void deleteResource(String id);  
}
