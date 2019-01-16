/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  RoleMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月18日 下午2:54:02   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  PermissionMapper   
 * @Description:权限管理持久层  
 * @author: yangz
 * @date:   2018年4月18日 下午2:54:02   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface PermissionMapper {

	/**     
	 * @Description: 条件查询权限信息 
	 * @param roleId 角色id
	 * @param userId 用户id
	 * @return      
	 * @return: List<Map<String,Object>>      
	 */ 
	public List<Map<String, Object>> queryUserPermissions(@Param("roleId")String roleId, @Param("userId")String userId);
	
	/**
	 * 
	 * @Description: 查询菜单树，不包含按钮   
	 * @param roleId 角色id
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryMenuTrees(@Param("roleId")String roleId);
	
	/**     
	 * @Description: 查询组合条件信息 
	 * @return      
	 * @return: List<Map<String,Object>>      
	 */ 
	public List<Map<String, Object>> queryPermissionCombinations();

	/**     
	 * @Description: 根据用户id删除权限信息 (数据删除)  
	 * @param userId 用户id     
	 * @return: void      
	 */ 
	public void deletePermissionByUserId(@Param("userId")String userId);

	/**     
	 * @Description: 根据角色id删除权限信息 (数据删除)   
	 * @param roleId 角色id   
	 * @return: void      
	 */ 
	public void deletePermissionByRoleId(@Param("roleId")String roleId);
	
	/**     
	 * @Description: 根据菜单设置的可视角色组删除原有已经授权的不可见权限，防止脏数据的产生   
	 * @param menuId 可视菜单id     
	 * @return: int      
	 */ 
	int deletePermissionByMenuViewGroups(@Param("menuId")String menuId);

	/**     
	 * @Description: 批量保存权限信息  
	 * @param list 权限信息
	 * @return      
	 * @return: int 保存成功的条数      
	 */ 
	public int savePermissions(List<Map<String, Object>> list);

}
