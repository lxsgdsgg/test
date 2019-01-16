package com.bashiju.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.manage.mapper.UserManageMapper;
import com.bashiju.manage.mapper.UserMapper;
import com.bashiju.manage.service.IUserService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.service.DynamicFormService;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.IdWorker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	DynamicFormService dynamicFormService;
	@Autowired
	UserManageMapper userManageMapper;
	@Autowired(required=false)
	IdWorker idwork;
	@Autowired
	private DataAuthHelper dataAuthHelper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	 private CommonSqlServie commonSqlServie;
	/**   
	 * <p>Title: getUserById</p>   
	 * <p>Description: 根据id查询用户信息</p>   
	 * @param id 用户id
	 * @return   
	 * @see com.bashiju.manage.service.IUserService#getUserById(java.lang.String)   
	 */
	@SystemServiceLog(operationType="id查询用户")
	@Override
	public Map<String, Object> getUserById(String id) {
		if(StringUtils.isEmpty(id))
			return null;
		return userMapper.getUserById(id);
	}

	/**   
	 * <p>Title: getUserByMobile</p>   
	 * <p>Description: 根据手机号查询用户信息</p>   
	 * @param mobile 用户手机号
	 * @return   
	 * @see com.bashiju.manage.service.IUserService#getUserByMobile(java.lang.String)   
	 */
	@SystemServiceLog(operationType="手机查询用户")
	@Override
	public Map<String, Object> getUserByMobile(String mobile) {
		if(StringUtils.isEmpty(mobile))
			return null;
		else
			return userMapper.getUserByMobile(mobile);
	}

	/**   
	 * <p>Title: queryUser</p>   
	 * <p>Description: 多条件 查询用户信息</p>   
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
	 * @see com.bashiju.manage.service.IUserService#queryUser(java.util.Map)   
	 */
	@SystemServiceLog(operationType="多条件查询")
	@Override
	public List<Map<String, Object>> queryUser(Map<String, Object> map) {
		dataAuthHelper.auth(MenuEnum.MENU_63.getCode(),UserThreadLocal.get().get("id").toString());
		return userMapper.queryUser(map);
	}

	/**   
	 * <p>Title: queryUser</p>   
	 * <p>Description: 多条件 查询用户信息(分页)</p>   
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
	 * @see com.bashiju.manage.service.IUserService#queryUser(java.util.Map, int, int)   
	 */
	@SystemServiceLog(operationType="多条件分页查询")
	@Override
	public Page<Map<String, Object>> queryUser(Map<String, Object> map, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String,Object>> page = (Page<Map<String, Object>>) this.queryUser(map);
		return page;
	}


	/**   
	 * <p>Title queryAllUser</p>   
	 * <p>Description </p>   
	 * @return   
	 * @see com.bashiju.manage.service.IUserService#queryAllUser()   
	 */
	
	@Override
	@SystemServiceLog(operationType="查询用户列表")
	public Page<Map<String, Object>> queryAllUser(int pageNum,int pageSize) {

		PageHelper.startPage(pageNum, pageSize);
		ExecutionResult.descFormat("", "查询用户列表");
		return userManageMapper.queryAllUser();
	}
	
	@Override
	@SystemServiceLog(operationType="查询用户表标题")
	public List<Map<String, Object>> queryTableField(String tableName, boolean isListShow) {
		
		String companyId=UserThreadLocal.get().get("companyId").toString();
		return dynamicFormService.queryTableField(tableName, companyId, isListShow);
	}
	
	@Override
	@SystemServiceLog(operationType="查询自定义SQL语句")
	public List<Map<String, Object>> queryCustomComponentValues(String Sql) {
		
		return dynamicFormService.queryCustomComponentValues(Sql);
	}



	/**   
	 * <p>Title queryCustomField</p>   
	 * <p>Description </p>   
	 * @param tableName
	 * @param companyId
	 * @param showField
	 * @param queryCondition
	 * @return   
	 * @see com.bashiju.manage.service.IUserService#queryCustomField(java.lang.String, java.lang.String, java.lang.String, java.lang.String)   
	 */
	
	@Override
	public List<Map<String, Object>> queryCustomField(String tableName, String showField,
			String queryCondition) {
		String companyId=UserThreadLocal.get().get("companyId").toString();
		return dynamicFormService.queryCustomField(tableName, companyId, showField, queryCondition);
	}

	@Override
	public Page<Map<String, Object>> queryUserOpenCity(String deptId, Long userId,int page,int limit) {
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_880.getCode(), UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> results=userMapper.queryUserOpenCity(deptId, userId);
		return results;
	}

	@Override
	public Integer addUserOpenCity(String jsonData) {
		Map data=JSON.parseObject(jsonData, Map.class);
		Long userId = Long.parseLong(data.get("userId").toString());
		Integer count=userMapper.queryUserOpenCityCount(userId);
		if (count>0) {
			throw new BusinessException("用户已经分配了城市，禁止重复分配");
		}
		data.put("permissionArea", data.get("deptId"));
		data.put("operatorId", UserThreadLocal.get().get("id"));
		Long result=commonSqlServie.commonOperationDatabase(data, "sys_user_companyOpenCity", false);
		return result.intValue();
	}

	@Override
	public Integer updateUserOpenCity(Integer id, String cityCodes, String cityNames) {
		Map<String,Object> param = new HashMap<>();
		param.put("id", id);
		param.put("cityCodes", cityCodes);
		param.put("cityNames", cityNames);
		Long result=commonSqlServie.commonOperationDatabase(param, "sys_user_companyOpenCity", "id", false);
		return result.intValue();
	}

	@Override
	public void deleteUserOpenCity(Integer id) {
		commonSqlServie.delData("sys_user_companyOpenCity", "id", id.toString(), false);
		
	}

	@Override
	public List<Map<String, Object>> queryCompanyOpenCity(String companyId) {
		List<Map<String, Object>> result=userMapper.queryCompanyOpenCity(companyId);
		return result;
	}

}
