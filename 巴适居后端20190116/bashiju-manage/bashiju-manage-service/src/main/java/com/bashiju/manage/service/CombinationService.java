/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CombinationService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年5月10日 下午6:33:18   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

/**   
 * @ClassName:  CombinationService   
 * @Description:组合条件权限服务
 * @author: yangz
 * @date:   2018年5月10日 下午6:33:19   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CombinationService {

	/**
	 * 
	 * @Description: 条件查询组合条件信息  (包括主表信息以及相应子列表)
	 * @param combinationId 组合条件id 
	 * @param name 组合条件名称
	 * @param menuId 菜单id
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryCombinations(String combinationId,String name,String menuId);
	
	/**
	 * 
	 * @Description: 条件查询组合条件主表信息 
	 * @param name 组合条件名称
	 * @param menuId 菜单id
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryCombinationMains(String name,String menuId);
	
	/**
	 * 
	 * @Description: 保存组合条件信息  
	 * @param main 组合条件主表信息
	 * @param details 组合条件子表信息
	 * @return      
	 * @return: boolean
	 */
	public boolean saveOrUpdateCombination(Map<String,Object> main,List<Map<String,Object>> details);
	
	/**
	 * 
	 * @Description: 根据id删除组合条件信息   
	 * @param combinationId 组合条件id
	 * @return      
	 * @return: boolean
	 */
	public boolean delCombination(String combinationId);
	
}
