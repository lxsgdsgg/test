/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  MenuMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月25日 上午10:17:13   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  MenuMapper   
 * @Description:菜单管理持久层  
 * @author: yangz
 * @date:   2018年4月25日 上午10:17:13   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface MenuMapper {

	/**     
	 * @Description: 条件查询菜单信息   
	 * @param menuName 菜单名称(模糊查询)
	 * @param parentMenuId 父级菜单id
	 * @param parentName 父级菜单名称(模糊查询)
	 * @param functionType 菜单类型
	 * @param level 菜单等级
	 * @param dataPerFlag 数据权限标识
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryMenus(@Param("menuName")String menuName, @Param("parentMenuId")String parentMenuId,
			@Param("parentName")String parentName,@Param("functionType")String functionType,
			@Param("level")String level,@Param("dataPerFlag")String dataPerFlag);

	/**     
	 * @Description: 根据id查询菜单信息   
	 * @param menuId 菜单id
	 * @return      
	 * @return: Map<String,Object>      
	 */ 
	Map<String, Object> getMenu(@Param("menuId")String menuId);

	/**     
	 * @Description: 根据菜单id查询该菜单下关联的数据   
	 * @param menuId 菜单id
	 * @return      
	 * @return: int      
	 */
	long isRelation(@Param("menuId")String menuId);
}
