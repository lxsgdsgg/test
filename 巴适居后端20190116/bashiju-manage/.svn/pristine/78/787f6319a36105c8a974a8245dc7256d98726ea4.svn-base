/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AdminPermissionService.java   
 * @Package com.bashiju.manage.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年5月26日 下午5:19:47   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.manage.mapper.AdminPermissionMapper;
import com.bashiju.manage.service.AdminPermissionService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.threadlocal.UserThreadLocal;

/**   
 * @ClassName:  AdminPermissionService   
 * @Description:平台管理员授权管理服务   
 * @author: yangz
 * @date:   2018年5月26日 下午5:19:47   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="平台管理员授权管理")
public class AdminPermissionServiceImpl implements AdminPermissionService{
	
	@Autowired
	private AdminPermissionMapper adminPermissionMapper;
	
	private static Logger logger = LoggerFactory.getLogger(AdminPermissionServiceImpl.class);

	/**
	 * 
	 * @Description: 条件查询权限信息 
	 * @Param roleId 角色id
	 * @param userId 用户id
	 * @param permissions 权限范围(主要用于查询城市管理员所管理的某个城市权限信息)
	 * @return: List<Map<String,String>>
	 * @see com.bashiju.manage.service.AdminPermissionService#queryUserPermissions(java.lang.String, java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryUserPermissions(String roleId, String userId,String permissions) {
		if(StringUtils.isEmpty(roleId) && StringUtils.isEmpty(userId))
			return null;
		return this.adminPermissionMapper.queryUserPermissions(roleId, userId,permissions);
	}
	
	/**
	 * 
	 * @Description: 根据角色id或用户id查询城市管理员责任城市信息   
	 * @param roleId 角色id
	 * @param userId 用户id
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.manage.service.AdminPermissionService#queryResponsibility(java.lang.String, java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryResponsibility(String roleId, String userId) {
		if(StringUtils.isEmpty(roleId))
			throw new BusinessException("角色编号不允许为空");
		return this.adminPermissionMapper.queryResponsibility(roleId, userId);
	}

	/**     
	 * @Description: 批量保存权限信息  
	 * @param roleId 角色id
	 * @param userId 用户id
	 * @param saveData 需要保存的权限信息
	 * @return      
	 * @return: boolean      
	 * @see com.bashiju.manage.service.AdminPermissionService#savePermissions(java.lang.String, java.lang.String, java.util.List)   
	 */
	@Override
	public boolean savePermissions(String roleId, String userId, List<Map<String, Object>> saveData) {
		if(StringUtils.isEmpty(roleId) && StringUtils.isEmpty(userId))
			return false;
		if(saveData==null || saveData.size()<=0) 
			return false;
		if(!StringUtils.isEmpty(userId)) //为具体用户授权，先删除原有授权数据，然后再授权
			adminPermissionMapper.deletePermissionByUserId(userId,(String)saveData.get(0).get("permissions"));
		else if(!StringUtils.isEmpty(roleId)) //为某个角色授权，先删除原有授权数据，然后再授权
			adminPermissionMapper.deletePermissionByRoleId(roleId,(String)saveData.get(0).get("permissions"));
		
		int flag = adminPermissionMapper.savePermissions(saveData);
		logger.warn("用户：【{}】成功保存了角色【{}】用户编号为【{}】{}条权限数据", UserThreadLocal.get().get("realName"),roleId,userId,flag);
		if(flag>0) {
			if(!StringUtils.isEmpty(userId))
				ExecutionResult.descFormat(userId, "编辑用户权限");
			else
				ExecutionResult.descFormat(roleId, "编辑角色权限");
			return true;
		}else
			return false;
	}

	/**     
	 * @Description: 根据菜单设置的可视角色组删除原有已经授权的不可见权限，防止脏数据的产生   
	 * @param menuId 可视菜单id     
	 * @return: boolean      
	 * @see com.bashiju.manage.service.AdminPermissionService#deletePermissionByMenuViewGroups(java.lang.String)   
	 */
	@Override
	public boolean deletePermissionByMenuViewGroups(String menuId) {
		if(StringUtils.isEmpty(menuId))
			return false;
		int result = this.adminPermissionMapper.deletePermissionByMenuViewGroups(menuId);
		logger.warn("用户：【{}】删除了菜单【{}】相关权限数据{}条", UserThreadLocal.get().get("realName"),menuId,result);
		if(result>0)
			return true;
		else
			return false;
	}


}
