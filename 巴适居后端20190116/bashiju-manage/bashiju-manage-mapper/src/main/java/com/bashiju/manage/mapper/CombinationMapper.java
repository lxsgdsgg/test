/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CombinationMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年5月11日 上午9:18:20   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  CombinationMapper   
 * @Description:条件组合持久层   
 * @author: yangz
 * @date:   2018年5月11日 上午9:18:20   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CombinationMapper {
	
	/**
	 * 
	 * @Description: 条件查询组合条件信息  (包括主表信息以及相应子列表)
	 * @param combinationId 组合条件id 
	 * @param name 组合条件名称
	 * @param menuId 菜单id
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryCombinations(@Param("combinationId")String combinationId,
			@Param("name")String name,@Param("menuId")String menuId);
	
	/**
	 * 
	 * @Description: 根据id删除组合条件信息(包括主表子表的信息，是物理删除)   
	 * @param combinationId 组合条件id
	 * @return      
	 * @return: long
	 */
	public long delCombination(String combinationId);
	
	/**
	 * 
	 * @Description: 根据组合条件id删除组合条件子信息   
	 * @param combinationId 组合条件id
	 * @return      
	 * @return: long
	 */
	public long delCombinationDetails(String combinationId);

	/**
	 * 
	 * @Description: 条件查询组合条件主表信息 
	 * @param name 组合条件名称
	 * @param menuId 菜单id
	 * @return: List<Map<String,Object>>     
	 */ 
	public List<Map<String, Object>> queryCombinationMains(@Param("name")String name, @Param("menuId")String menuId);
	
}
