/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AdminPermissionMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年5月26日 下午5:27:17   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  AdminPermissionMapper   
 * @Description:平台管理员授权管理服务   
 * @author: yangz
 * @date:   2018年5月26日 下午5:27:17   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface AdminPermissionMapper {
	
	/**
	 * 
	 * @Description: 根据角色id或者用户id查询权限信息   
	 * @param roleId 角色id
	 * @param userId 用户id
	 * @param permissions 权限范围(主要用于查询城市管理员所管理的某个城市权限信息)
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryUserPermissions(@Param("roleId")String roleId, 
			@Param("userId")String userId,@Param("permissions")String permissions);

	/**     
	 * @Description: 根据用户id删除授权信息   
	 * @param userId 用户id     
	 * @param permissions 权限范围
	 * @return: void      
	 */ 
	void deletePermissionByUserId(@Param("userId")String userId, @Param("permissions")String permissions);

	/**     
	 * @Description: 根据角色id删除授权信息   
	 * @param roleId 角色id     
	 * @param permissions 权限范围
	 * @return: void      
	 */ 
	void deletePermissionByRoleId(@Param("roleId")String roleId,@Param("permissions")String permissions);
	
	/**     
	 * @Description: 根据菜单设置的可视角色组删除原有已经授权的不可见权限，防止脏数据的产生   
	 * @param menuId 可视菜单id     
	 * @return: int      
	 */ 
	int deletePermissionByMenuViewGroups(@Param("menuId")String menuId);
	
	/**     
	 * @Description: 批量保存授权信息   
	 * @param saveData 授权信息
	 * @return      
	 * @return: int 成功条数     
	 */ 
	int savePermissions(List<Map<String, Object>> saveData);

	/**     
	 * @Description: 根据角色id或用户id查询城市管理员责任城市信息   
	 * @param roleId 角色id
	 * @param userId 用户id
	 * @return      
	 * @return: List<Map<String,Object>>      
	 */ 
	List<Map<String, Object>> queryResponsibility(@Param("roleId")String roleId, @Param("userId")String userId);

}
