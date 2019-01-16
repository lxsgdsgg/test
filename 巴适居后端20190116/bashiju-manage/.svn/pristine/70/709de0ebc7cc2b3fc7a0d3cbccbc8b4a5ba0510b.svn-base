/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ResponsibilityCityMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年5月31日 上午10:02:57   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  ResponsibilityCityMapper   
 * @Description:城市管理员责任城市管理服务持久层  
 * @author: yangz
 * @date:   2018年5月31日 上午10:02:57   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ResponsibilityCityMapper {

	/**
	 * 
	 * @Description: 条件查询城市管理员责任城市信息   
	 * @param roleId 角色id
	 * @param userId 用户id
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryResponsibilityCity(@Param("roleId")String roleId,@Param("userId")String userId);
	
	
	/**
	 * 
	 * @Description: 根据角色删除城市管理员责任城市信息   
	 * @param roleId 角色id
	 * @return: boolean
	 */
	int deleteResponsibilityCityByRoleId(@Param("roleId")String roleId);
	
	/**
	 * 
	 * @Description: 根据用户删除城市管理员责任城市信息   
	 * @param userId 用户id
	 * @return: boolean
	 */
	int deleteResponsibilityCityByuserId(@Param("userId")String userId);
	
	/**
	 * 
	 * @Description: 删除与当前角色/用户关联的无效的权限信息   
	 * @param roleId 角色id
	 * @param userId 用户id
	 * @return: int
	 */
	int deletePermissionAdminRelationsInfo(@Param("roleId")String roleId,@Param("userId")String userId);
}
