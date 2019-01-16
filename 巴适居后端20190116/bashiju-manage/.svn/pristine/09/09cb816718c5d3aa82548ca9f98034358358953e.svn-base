/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  MenuService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月25日 上午10:01:56   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  MenuService   
 * @Description:菜单管理服务接口  
 * @author: yangz
 * @date:   2018年4月25日 上午10:01:56   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface MenuService {

	/**     
	 * @Description: 条件查询菜单信息(分页)   
	 * @param menuName 菜单名称(模糊查询)
	 * @param parentMenuId 父级菜单id
	 * @param parentName 父级菜单名称(模糊查询)
	 * @param functionType 菜单类型
	 * @param level 菜单等级
	 * @param dataPerFlag 数据权限标识
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>      
	 */ 
	Page<Map<String, Object>> queryMenus(String menuName,String parentMenuId,String parentName, String functionType, String level,String dataPerFlag, int pageNum, int pageSize);

	/**
	 * 
	 * @Description: 条件查询菜单信息   
	 * @param menuName 菜单名称
	 * @param parentMenuId 父级菜单id
	 * @param parentName 父级菜单名称(模糊查询)
	 * @param functionType 菜单类型
	 * @param level 菜单等级
	 * @param dataPerFlag 数据权限标识
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryMenus(String menuName,String parentMenuId,String parentName,String functionType,String level,String dataPerFlag);

	/**     
	 * @Description: 根据id查询菜单信息   
	 * @param menuId 菜单id
	 * @return      
	 * @return: Map<String,Object>      
	 */ 
	Map<String, Object> getMenu(String menuId);

	/**     
	 * @Description: 根据菜单id查询该菜单下关联的数据   
	 * @param menuId 菜单id
	 * @return      
	 * @return: boolean      
	 */ 
	boolean isRelation(String menuId);
	
	/**
	 * 
	 * @Description: 保存菜单信息   
	 * @param map 需要保存的菜单信息，(注：map中包含的属性必须为sys_menu表中的字段；
	 * 	当map中存在id字段时，认为是修改操作，否则为新增操作)
	 * @return      
	 * @return: long
	 */
	long saveOrUpdateMenu(Map<String,Object> map);
	
	/**
	 * 
	 * @Description: 根据菜单id删除菜单信息   
	 * @param menuId 菜单id
	 * @return      
	 * @return: boolean
	 */
	boolean delMenu(String menuId);
}
