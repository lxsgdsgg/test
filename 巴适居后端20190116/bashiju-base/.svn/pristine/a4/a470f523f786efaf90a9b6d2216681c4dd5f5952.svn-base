/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  MainMapper.java   
 * @Package com.bashiju.sso.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月10日 上午11:40:53   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.base.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
/**   
 * @ClassName:  MainMapper   
 * @Description:系统主页面服务   
 * @author: yangz
 * @date:   2018年4月10日 上午11:40:53   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface PermissionCertMapper {

	/**
	 * 
	 * @Description: 根据用户id查询该用户的权限信息 
	 * @param userId 用户id
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryUserMenuPermission(@Param("userId")String userId);
	
	/**
	 * 
	 * @Description: 根据用户id查询该用户的权限信息 
	 * @param userId 用户id
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryUserMenuPermission2(@Param("userId")String userId);
	
	/**
	 * 
	 * @Description: 根据用户id查询一级菜单  
	 * @param userId 用户id
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryFirstMenuByUser(@Param("userId")String userId);
	
	/**
	 * 
	 * @Description: 根据菜单id查询用户权限内子一级菜单信息  
	 * @param userId 用户id
	 * @param menuParentId 菜单id
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryChildMenus(@Param("userId")String userId,@Param("menuParentId")String menuParentId);
		
	/**     
	 * @Description: 根据菜单id查询权限信息以及子一级的权限信息   
	 * @param userId 用户id 
	 * @param menuParentId 菜单id
	 * @return      
	 * @return: List<Map<String,Object>>      
	 */ 
	public List<Map<String, Object>> queryFunctionMenuPermission(@Param("userId")String userId,@Param("menuParentId")String menuParentId);

	/**
	 * 
	 * @Description: 查询用户菜单下的唯一按钮权限    
	 * @param userId 用户id
	 * @param menuParentId 菜单id
	 * @return: List<Map<String,Object>>      
	 */ 
	public List<Map<String, Object>> callQueryUserMenusOnlyButton(@Param("userId")String userId,@Param("menuParentId")String menuParentId);
}
