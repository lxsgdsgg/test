/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AdminPermissionService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年5月26日 下午5:16:52   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

/**   
 * @ClassName:  AdminPermissionService   
 * @Description:平台管理员授权管理服务接口 
 * @author: yangz
 * @date:   2018年5月26日 下午5:16:52   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface AdminPermissionService {

	/**
	 * 
	 * @Description: 条件查询权限信息 
	 * @Param roleId 角色id
	 * @param userId 用户id
	 * @param permissions 权限范围(主要用于查询城市管理员所管理的某个城市权限信息)
	 * @return      
	 * @return: List<Map<String,String>>
	 */
	public List<Map<String,Object>> queryUserPermissions(String roleId,String userId,String permissions);
	
	/**
	 * 
	 * @Description: 根据角色id或用户id查询城市管理员责任城市信息   
	 * @param roleId 角色id
	 * @param userId 用户id
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryResponsibility(String roleId,String userId);
	
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
