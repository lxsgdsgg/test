/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  PermissionServiceImpl.java   
 * @Package com.bashiju.manage.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月19日 下午4:25:49   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.manage.mapper.PermissionMapper;
import com.bashiju.manage.service.PermissionService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;

/**   
 * @ClassName:  PermissionServiceImpl   
 * @Description:权限服务接口   
 * @author: yangz
 * @date:   2018年4月19日 下午4:25:49   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class PermissionManageServiceImpl extends CommonSqlServie implements PermissionService {
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	private static Logger logger = LoggerFactory.getLogger(PermissionManageServiceImpl.class);

	/**
	 * 
	 * @Description: 条件查询权限信息 
	 * @param userId 用户id
	 * @return      
	 * @return: List<Map<String,String>>
	 */
	@Override
	public List<Map<String, Object>> queryUserPermissions(String roleId,String userId) {
		if(StringUtils.isEmpty(roleId))
			throw new BusinessException("角色编号不允许为空");
		return permissionMapper.queryUserPermissions(roleId,userId);
	}

	/**
	 * 
	 * @Description: 查询菜单树，不包含按钮   
	 * @param roleId 角色id
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	@Override
	public List<Map<String, Object>> queryMenuTrees(String roleId) {
		if(StringUtils.isEmpty(roleId))
			return new ArrayList<Map<String,Object>>();
		return permissionMapper.queryMenuTrees(roleId);
	}
	
	/**
	 * 
	 * @Description: 查询组合条件信息  
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	@Override
	public List<Map<String,Object>> queryPermissionCombinations(){
		return permissionMapper.queryPermissionCombinations();
	}

	/**     
	 * @Description: 批量保存权限信息  
	 * @param roleId 角色id
	 * @param userId 用户id
	 * @param saveData 需要保存的权限信息
	 * @return      
	 * @return: boolean      
	 */ 
	@Override
	public boolean savePermissions(String roleId, String userId, List<Map<String, Object>> saveData) {
		if(StringUtils.isEmpty(roleId) && StringUtils.isEmpty(userId))
			return false;
		if(saveData==null || saveData.size()<=0) 
			return false;
		if(!StringUtils.isEmpty(userId)) //为具体用户授权，先删除原有授权数据，然后再授权
			permissionMapper.deletePermissionByUserId(userId);
		else if(!StringUtils.isEmpty(roleId)) //为某个角色授权，先删除原有授权数据，然后再授权
			permissionMapper.deletePermissionByRoleId(roleId);
		
		int flag = permissionMapper.savePermissions(saveData);
		logger.warn("用户：【{}】成功保存了角色【{}】用户编号为【{}】{}条权限数据", UserThreadLocal.get().get("realName"),roleId,userId,flag);
		if(flag>0)
			return true;
		else
			return false;
	}

	/**     
	 * @Description: 根据菜单设置的可视角色组删除原有已经授权的不可见权限，防止脏数据的产生   
	 * @param menuId 可视菜单id     
	 * @return: boolean      
	 * @see com.bashiju.manage.service.PermissionService#deletePermissionByMenuViewGroups(java.lang.String)   
	 */
	@Override
	public boolean deletePermissionByMenuViewGroups(String menuId) {
		if(StringUtils.isEmpty(menuId))
			return false;
		int result = this.permissionMapper.deletePermissionByMenuViewGroups(menuId);
		logger.warn("用户：【{}】删除了菜单【{}】相关权限数据{}条", UserThreadLocal.get().get("realName"),menuId,result);
		if(result>0)
			return true;
		else
			return false;
	}

}
