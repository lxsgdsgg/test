package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  DeptService   
 * @Description:部门管理服务接口   
 * @author: wangpeng
 * @date:   2018年4月26日 下午4:37:49   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DeptService {
	
	/**
	 * @Title: queryDept   
	 * @Description: 条件查询部门信息  
	 * @param deptName 部门名称
	 * @return: List<Map<String,Object>>
	 */
	public Page<Map<String, Object>> queryDept(String deptName,String companyId,String deptTypeId,int pageNum,int pageSize);
	
	/**
	 * @Description: 条件查询部门信息   
	 * @param deptName 部门名称(模糊查询)
	 * @param companyId 公司id
	 * @param deptTypeId 部门类型
	 * @param parentId 上级部门id
	 * @param areaCode 区域代码
	 * @Param leader 所属领导
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryDepts(String deptName,String companyId,String deptTypeId,String parentId,String areaCode,String leader);
	
	
	/**
	 * 查询部门名称和部门编号
	 * @Title: queryDeptIdAndDeptName   
	 * @Description: 查询部门名称和部门编号
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryDeptIdAndDeptName();
	
	/**
	 * @Description: 根据id查询部门信息   
	 * @param deptId 部门id
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> getDept(String deptId);
	
	/**
	 * @Description: 是否存在关联数据  
	 * @param deptId 部门id
	 * @return: boolean
	 */
	public boolean isRelation(String deptId);
	
	/**
	 * 
	 * @Description: 保存部门信息   
	 * @param map 需要保存的部门信息
	 * @return: boolean
	 */
	public boolean saveOrUpdateDept(Map<String,Object> map);
	
	/**
	 * @Description: 根据部门id删除部门信息
	 * @param deptId 部门id
	 * @return: boolean
	 */
	public boolean delDept(String deptId);
	
	/**
	 * @Title: queryAreaCode   
	 * @Description: 查询区域代码  
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String, Object>>  queryAreaCode();
	
	
	/**
	 * @Title: queryUser   
	 * @Description: 查询所有用户   
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String, Object>>queryUser();
	
	/**
	 * @Description: 查询部门树列表   
	 * @param roleGroup 当前用户所属的角色组
	 * @param userId 当前用户id
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryDeptTrees(String roleGroup,String userId);
	
	
	/**
	 * @Title: saveAcrossStorePermission   
	 * @Description: 批量添加跨店信息(将覆盖部门下的原有跨店信息)   
	 * @param list 待保存的数据信息
	 * @return: boolean
	 */
	boolean saveAcrossStorePermission(String deptId,List<Map<String, Object>> list);
	/**
	 * 
	 * @Title: queryAcrossStorePermission   
	 * @Description: 查询跨店信息
	 * @param deptId 部门id
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryAcrossStorePermission(String deptId);
	
	/**
	 *  批量新增或修改责任盘
	 * @Title: saveResponsbilityDisc   
	 * @Description: 批量新增或修改责任盘
	 * @param list 批量数据
	 * @return: boolean
	 */
	boolean saveResponsbilityDisc(List<Map<String, Object>> list);
	
	 /**
	  * 查询部门所属城市
	  * @Title: queryDeptCityCode   
	  * @Description: 查询部门所属城市
	  * @param deptId 部门编号
	  * @return: Map<String,Object>
	  */
	 Map<String,Object> queryDeptCityCode(String deptId);
	 
	 /**
	  * 条件查询部门责任盘
	  * @Title: queryResponsibilityDisk   
	  * @Description: 条件查询部门责任盘 
	  * @param deptId 部门编号
	  * @return: List<Map<String,Object>>
	  */
	 List<Map<String,Object>> queryResponsibilityDisk(String deptId);
	 

}
