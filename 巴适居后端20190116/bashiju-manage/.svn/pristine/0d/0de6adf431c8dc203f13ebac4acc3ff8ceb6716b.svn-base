/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ResponsibilityCityService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年5月30日 下午6:22:02   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  ResponsibilityCityService   
 * @Description:城市管理员责任城市管理接口 
 * @author: yangz
 * @date:   2018年5月30日 下午6:22:02   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ResponsibilityCityService {

	/**
	 * 
	 * @Description: 条件查询城市管理员责任城市信息   
	 * @param roleId 角色id
	 * @param userId 用户id
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryResponsibilityCity(String roleId,String userId);
	
	/**
	 * 
	 * @Description: 条件查询城市管理员责任城市信息  (分页) 
	 * @param roleId 角色id
	 * @param userId 用户id
	 * @Param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: List<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryResponsibilityCity(String roleId,String userId,int pageNum,int pageSize);
	
	/**
	 * 
	 * @Description: 保存城市管理员责任城市信息   
	 * @param roleId 被操作的角色Id
	 * @param userId 被操作的用户Id
	 * @param maps 待保存的数据
	 * @return: boolean
	 */
	public boolean saveResponsibilityCity(String roleId,String userId,List<Map<String,Object>>  maps);
	
	/**
	 * 
	 * @Description: 根据角色删除城市管理员责任城市信息 (同时删除与该角色相关的权限信息，包括独立用户权限)  
	 * @param roleId 角色id
	 * @return: boolean
	 */
	public boolean deleteResponsibilityCityByRoleId(String roleId);
	
	/**
	 * 
	 * @Description: 根据用户删除城市管理员责任城市信息(同时删除与该用户相关的权限信息)  
	 * @param userId 用户id
	 * @return: boolean
	 */
	public boolean deleteResponsibilityCityByuserId(String userId);
}
