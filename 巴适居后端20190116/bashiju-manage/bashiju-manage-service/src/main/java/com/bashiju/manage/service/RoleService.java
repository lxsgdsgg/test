/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  RoleService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月18日 下午2:41:26   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  RoleService   
 * @Description:角色管理服务接口   
 * @author: yangz
 * @date:   2018年4月18日 下午2:41:26   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface RoleService {
	
	/**
	 * 
	 * @Description: 根据公司id查询该公司下边的角色  
	 * @param companyId 公司id
	 * @param groups 角色组
	 * @param roleName 角色名称
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryRoles(String companyId,String groups,String roleName);
	
	/**
	 * 
	 * @Description: 根据公司id查询公司下边的角色信息
	 * @param companyId 公司id
	 * @param groups 角色组
	 * @param roleName 角色名称
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return      
	 * @return: PageInfo<Map<String,Object>>
	 */
	public Page<Map<String, Object>> queryRoles(String companyId,String groups,String roleName,int pageNum,int pageSize);
	
	/**
	 * 
	 * @Description: 根据角色id查询角色信息 
	 * @param roleId 角色id
	 * @return      
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> getRole(String roleId);
	
	/**
	 * 
	 * @Description: 根据角色id判断该角色是否存在关联数据  
	 * @param roleId 角色id
	 * @return      
	 * @return: boolean  true表示有关联数据，false表示无关联数据
	 */
	public boolean isRelation(String roleId);
	
	/**
	 * 
	 * @Description: 保存角色信息   
	 * @param map 需要保存的角色信息，(注：map中包含的属性必须为sys_role表中的字段；
	 * 	当map中存在id字段时，认为是修改操作，否则为新增操作)
	 * @return      
	 * @return: long
	 */
	long saveOrUpdateRole(Map<String,Object> map);
	
	/**
	 * 
	 * @Description: 根据角色id删除角色信息   
	 * @param roleId 角色id
	 * @return      
	 * @return: boolean
	 */
	public boolean delRole(String roleId);

}
