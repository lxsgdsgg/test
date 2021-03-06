package com.bashiju.manage.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

public interface PostMapper {
	
/**
 * @Title: queryPostList   
 * @Description: 通过名称查询职位    
 * @param postId 职位id
 * @param postName 职位名称
 * @return: List<Map<String,Object>>
 */
	public Page<Map<String, Object>> queryPostList(@Param("postName") String postName);
	
	
	/**
	 * @Title: queryPostById   
	 * @Description: 通过id查询职位信息   
	 * @param postId id
	 * @return: Map<String,Object>
	 */
	public Map<String, Object> queryPostById(@Param("postId")String postId);
	
	
	/**
	 * 
	 * @Title: isRelation   
	 * @Description: 通过职位id查询是否关联数据  
	 * @param postId 职位id
	 * @return: long
	 */
	public long isRelation(@Param("postId")String postId);
	

}
