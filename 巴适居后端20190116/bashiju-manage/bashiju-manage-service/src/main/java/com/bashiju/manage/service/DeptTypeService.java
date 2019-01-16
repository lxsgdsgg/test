package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  DeptTypeService   
 * @Description:部门类型管理服务接口   
 * @author: wangpeng
 * @date:   2018年4月24日 下午12:04:07   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DeptTypeService {
	/**
	 * @Title: queryDeptTypeList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param deptTypeName 部门类型名称
	 * @param pageNum 当前页
	 * @param pageSize 每页总条数
	 * @throws Exception      
	 * @return: Page<Map<String,Object>>
	 */
	public  Page<Map<String, Object>> queryDeptTypeList(String deptTypeName,int pageNum,int pageSize)throws Exception;
	
	
	/**
	 * 
	 * @Title: queryDeptTypeById   
	 * @Description: 通过部门类型id查询部门类型信息  
	 * @param id 部门类型id
	 * @throws Exception      
	 * @return: Map<String,Object>
	 */
	public Map<String, Object> queryDeptTypeById(String id)throws Exception;
	
	
	/**
	 * @Title: queryAllDeptTypes   
	 * @Description: 查询所有部门类型信息
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryAllDeptTypes();
	
	
	
	/**
	 * @Title: isRelation   
	 * @Description: 通过部门类型id，查询是否有部门关联信息   
	 * @param deptTypeId 部门类型id
	 * @return: boolean true 为有关联数据 false为无关联数据
	 */
	public boolean isRelation(String deptTypeId);
	
	
	/**
	 * 
	 * @Title: saveOrUpdateDeptType   
	 * @Description: 保存或添加部门类型 
	 * @param map 参数
	 * @return: long
	 */
	boolean saveOrUpdateDeptType(Map<String,Object>map);
	
	/**
	 * 
	 * @Title: delDeptType   
	 * @Description: 通过id逻辑删除部门类型
	 * @param id 部门id
	 * @return: boolean
	 */
	boolean delDeptType(String deptTypeId);
	
	
	/**
	 * @Title: queryDeptTypeIdAndName   
	 * @Description: 查询部门编号和部门名称
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryDeptTypeIdAndName();

}
