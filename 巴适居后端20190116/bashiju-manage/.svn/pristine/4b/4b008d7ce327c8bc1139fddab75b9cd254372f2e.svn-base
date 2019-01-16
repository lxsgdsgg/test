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

import com.github.pagehelper.Page;

/**   
 * @ClassName:  RoleMapper   
 * @Description:角色管理持久层  
 * @author: yangz
 * @date:   2018年4月18日 下午2:54:02   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface RoleMapper {

	/**
	 * 
	 * @Description: 根据公司id查询该公司下边的角色  
	 * @param companyId 公司id
	 * @param groups 角色组
	 * @param roleName 角色名称
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	public Page<Map<String, Object>> queryRoles(@Param("companyId")String companyId, @Param("groups")String groups,@Param("roleName")String roleName);
	
	
	/**
	 * 
	 * @Description: 根据角色id查询角色信息 
	 * @param roleId 角色id
	 * @return      
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> getRole(@Param("roleId") String roleId);


	/**     
	 * @Description: 根据角色id判断该角色是否存在关联数据    
	 * @param roleId 角色id
	 * @return      
	 * @return: long      
	 */ 
	public long isRelation(@Param("roleId")String roleId);
}
