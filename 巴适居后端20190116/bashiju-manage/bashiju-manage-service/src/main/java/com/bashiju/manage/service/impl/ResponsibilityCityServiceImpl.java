/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ResponsibilityCityServiceImpl.java   
 * @Package com.bashiju.manage.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年5月31日 上午9:33:02   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.manage.mapper.ResponsibilityCityMapper;
import com.bashiju.manage.service.ResponsibilityCityService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**   
 * @ClassName:  ResponsibilityCityServiceImpl   
 * @Description:城市管理员责任城市管理服务   
 * @author: yangz
 * @date:   2018年5月31日 上午9:33:02   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="责任城市管理")
public class ResponsibilityCityServiceImpl extends CommonSqlServie implements ResponsibilityCityService {

	@Autowired
	private ResponsibilityCityMapper responsibilityCityMapper;
	@Autowired
	private DataAuthHelper dataAuthHelper;
	

	/**
	 * 
	 * @Description: 条件查询城市管理员责任城市信息   
	 * @param roleId 角色id
	 * @param userId 用户id 
	 * @param dataAuthFlag 是否调用数据权限标识
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.manage.service.ResponsibilityCityService#queryResponsibilityCity(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public List<Map<String, Object>> queryResponsibilityCity(String roleId, String userId) {
		dataAuthHelper.auth(MenuEnum.MENU_42.getCode(),UserThreadLocal.get().get("id").toString());
		return responsibilityCityMapper.queryResponsibilityCity(roleId, userId);
	}
	
	/**
	 * 
	 * @Description: 条件查询城市管理员责任城市信息  (分页) 
	 * @param roleId 角色id
	 * @param userId 用户id
	 * @Param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.manage.service.ResponsibilityCityService#queryResponsibilityCity(java.lang.String, java.lang.String, boolean, int, int)
	 */
	@Override
	public Page<Map<String, Object>> queryResponsibilityCity(String roleId, String userId, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String,Object>> list = this.queryResponsibilityCity(roleId, userId);
		Page<Map<String,Object>> page = (Page<Map<String, Object>>) list;
		return page;
	}

	/**
	 * 
	 * @Description: 保存城市管理员责任城市信息   
	 * @param roleId 被操作的角色Id
	 * @param userId 被操作的用户Id
	 * @param maps 待保存的数据
	 * @return: boolean
	 * @see com.bashiju.manage.service.ResponsibilityCityService#saveResponsibilityCity(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="保存信息")
	public boolean saveResponsibilityCity(String roleId,String userId,List<Map<String,Object>>  maps) {
		if(StringUtils.isEmpty(roleId))
			throw new BusinessException("角色id不允许为空");
		if(maps!=null && maps.size()>0) {
			if(StringUtils.isEmpty(userId))//将原有与角色相关的信息删除
				this.deleteResponsibilityCityByRoleId(roleId);
			else//将原有与用户相关的信息删除
				this.deleteResponsibilityCityByuserId(userId);
			boolean result = batchCommonOperationDatabase(maps, "sys_city_responsibility", false);
			//将于角色或者用户关联的城市管理员授权信息根据所选城市信息部分删除，防止产生脏数据
			responsibilityCityMapper.deletePermissionAdminRelationsInfo(roleId, userId);
			if(result) {
				if(StringUtils.isEmpty(userId))
					ExecutionResult.descFormat(roleId, "保存角色信息");
				else
					ExecutionResult.descFormat(userId, "保存用户信息");
			}
			return result;
		}
			
		return false;
	}

	/**
	 * 
	 * @Description: 根据角色删除城市管理员责任城市信息   
	 * @param roleId 角色id
	 * @return: boolean
	 * @see com.bashiju.manage.service.ResponsibilityCityService#deleteResponsibilityCityByRoleId(java.lang.String)   
	 */
	@Override
	@SystemServiceLog(operationType="角色删除信息")
	public boolean deleteResponsibilityCityByRoleId(String roleId) {
		if(StringUtils.isEmpty(roleId))
			return false;
		//将于角色或者用户关联的城市管理员授权信息根据所选城市信息部分删除，防止产生脏数据
		responsibilityCityMapper.deletePermissionAdminRelationsInfo(roleId, null);
		int result = this.responsibilityCityMapper.deleteResponsibilityCityByRoleId(roleId);
		ExecutionResult.descFormat(roleId, "角色删除信息"+result+"条");
		if(result>0)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @Description: 根据用户删除城市管理员责任城市信息   
	 * @param userId 用户id
	 * @return: boolean
	 * @see com.bashiju.manage.service.ResponsibilityCityService#deleteResponsibilityCityByuserId(java.lang.String)   
	 */
	@Override
	@SystemServiceLog(operationType="用户删除信息")
	public boolean deleteResponsibilityCityByuserId(String userId) {
		if(StringUtils.isEmpty(userId))
			return false;
		//将于角色或者用户关联的城市管理员授权信息根据所选城市信息部分删除，防止产生脏数据
		responsibilityCityMapper.deletePermissionAdminRelationsInfo(null, userId);
		int result = this.responsibilityCityMapper.deleteResponsibilityCityByuserId(userId);
		ExecutionResult.descFormat(userId, "用户删除信息"+result+"条");
		if(result>0)
			return true;
		else
			return false;
	}

}
