package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

public interface IUserService {
    /**
	 * 
	 * @Description: 根据id查询用户信息   
	 * @param id 用户id
	 * @return      
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> getUserById(String id);
	
	/**
	 * 
	 * @Description: 根据手机号查询用户信息   
	 * @param mobile 手机号
	 * @return      
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> getUserByMobile(String mobile);
	
	/**
	 * 
	 * @Description: 多条件 查询用户信息
	 * @param map 查询条件，用户属性<br>
	 *  map{<br>
	 * 	realName 用户名<br>
	 * 	mobile 手机号<br>
	 *  wxNum 微信号<br>
	 *  telPhone 固定电话<br>
	 *  companyId 所属公司<br>
	 *  deptId 所属部门<br>
	 *  roleId 所属角色<br>
	 *  sex 性别<br>
	 *  employee_num 员工号<br>
	 *  email 邮件号<br>
	 *  postId 所属职位<br>
	 * }<br>
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryUser(Map<String,Object> map);
	
	/**
	 * 
	 * @Description: 多条件 查询用户信息(分页)  
	 * @param map 查询条件，用户属性<br>
	 *  map{<br>
	 * 	realName 用户名<br>
	 * 	mobile 手机号<br>
	 *  wxNum 微信号<br>
	 *  telPhone 固定电话<br>
	 *  companyId 所属公司<br>
	 *  deptId 所属部门<br>
	 *  roleId 所属角色<br>
	 *  sex 性别<br>
	 *  employee_num 员工号<br>
	 *  email 邮件号<br>
	 *  postId 所属职位<br>
	 * }<br>
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return      
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryUser(Map<String,Object> map,int pageNum,int pageSize);
    
    public Page<Map<String, Object>> queryAllUser(int pageNum,int pageSize);

	public  List<Map<String,Object>> queryTableField(String tableName,boolean isListShow);
	public List<Map<String, Object>> queryCustomComponentValues(String Sql);
	public List<Map<String, Object>> queryCustomField(String tableName,String showField, String queryCondition);
	/**
	 * 
		 * 查询用户授权城市列表
		 * @Description: 查询用户授权城市列表 
		 * @param deptId
		 * @param userId
		 * @return 
		 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryUserOpenCity(String deptId,Long userId,int page,int limit);
	/**
	 * 
		 * 给用户分配城市
		 * @Description: 给用户分配城市 
		 * @param jsonData 
		 * @return 
		 * Integer
	 */
	public Integer addUserOpenCity(String jsonData);
	/**
	 * 
		 * 修改用户分配城市
		 * @Description: 修改用户分配城市
		 * @param id
		 * @param cityCodes
		 * @param cityNames
		 * @return 
		 * Integer
	 */
	public Integer updateUserOpenCity(Integer id,String cityCodes,String cityNames);
	/**
	 * 
		 *  删除用户分配城市
		 * @Description: 删除用户分配城市
		 * @param id 
		 * void
	 */
	public void deleteUserOpenCity(Integer id);
	/**
	 * 
		 * 查询公司开通城市 
		 * @Description: 查询公司开通城市  
		 * @param companyId
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryCompanyOpenCity(String companyId);
}
