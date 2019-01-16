/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  PermissionService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月19日 下午3:41:46   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

/**   
 * @ClassName:  PermissionService   
 * @Description:权限管理接口  
 * @author: yangz
 * @date:   2018年4月19日 下午3:41:46   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface PermissionService {

	/**
	 * 
	 * @Description: 条件查询权限信息 
	 * @Param roleId 角色id
	 * @param userId 用户id
	 * @return      
	 * @return: List<Map<String,String>>
	 */
	public List<Map<String,Object>> queryUserPermissions(String roleId,String userId);
	
	/**
	 * 
	 * @Description: 查询菜单树，不包含按钮   
	 * @param roleId 角色id
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryMenuTrees(String roleId);
	
	/**
	 * 
	 * @Description: 查询组合条件信息  
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryPermissionCombinations();

	/**     
	 * @Description: 批量保存权限信息  
	 * @param roleId 角色id
	 * @param userId 用户id
	 * @param saveData 需要保存的权限信息
	 * @return      
	 * @return: boolean      
	 */ 
	public boolean savePermissions(String roleId, String userId, List<Map<String, Object>> saveData);
	
	/**     
	 * @Description: 根据菜单设置的可视角色组删除原有已经授权的不可见权限，防止脏数据的产生   
	 * @param menuId 可视菜单id     
	 * @return: boolean      
	 */ 
	public boolean deletePermissionByMenuViewGroups(String menuId);
}
