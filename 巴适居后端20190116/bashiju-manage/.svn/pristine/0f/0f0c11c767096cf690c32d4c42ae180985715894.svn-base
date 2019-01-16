package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  DeptTypeMapper   
 * @Description:部门类型持久管理层 
 * @author: wangpeng
 * @date:   2018年4月24日 上午11:59:38   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DeptTypeMapper {
	
	/**
	 * 
	 * @Title: queryDeptTypeList   
	 * @Description: 通过部门类型id或者名字查询
	 * @param deptTypeId 部门类型id
	 * @param deptTypeName 部门类型名字
	 * @return: List<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryDeptTypeList(@Param("deptTypeName")String deptTypeName);
	
	/**
	 * 
	 * @Title: queryDeptTypeById   
	 * @Description: 通过部门类型id查询  
	 * @param id 部门id
	 * @return: Map<String,Object>
	 */
	public Map<String, Object> queryDeptTypeById(@Param("id")String id);
	
	/**
	 * 
	 * @Title: queryAllDeptTypes   
	 * @Description: 查询所有部门信息
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryAllDeptTypes();
	/**
	 * 
	 * @Title: isRelation   
	 * @Description: 通过部门类型id查找部门  
	 * @param deptTypeId 部门类型id
	 * @return: long
	 */
	public long isRelation(@Param("deptTypeId")String deptTypeId);
	
	
	/**
	 * 查询所有部门类型信息
	 * @Title: queryDeptTypes   
	 * @Description: 查询部门信息 
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryDeptTypes();
	
	/**
	 * @Title: queryDeptTypeIdAndName   
	 * @Description: 查询部门编号和部门名称
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryDeptTypeIdAndName();
	
}
