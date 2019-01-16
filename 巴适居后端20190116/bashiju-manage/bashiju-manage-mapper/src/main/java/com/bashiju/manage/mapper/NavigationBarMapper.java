package com.bashiju.manage.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  NavigationBarMapper   
 * @Description:导航栏映射接口
 * @author: wangpeng
 * @date:   2018年7月24日 上午9:25:52   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface NavigationBarMapper {
	
	/**
	 * 
	 * @Title: queryNavigationBarData   
	 * @Description: 条件查询导航栏信息
	 * @param paramMap 查询参数
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String, Object>> queryNavigationBarData(Map<String,Object> paramMap);
	
	/**
	 * 
	 * @Title: queryNavigationBarById   
	 * @Description: 条件查询导航栏信息
	 * @param navigationBarId 导航栏id
	 * @return: Map<String,Object>
	 */
	Map<String,Object> queryNavigationBarById(@Param("navigationBarId")String navigationBarId);


	/**
	 * @Description: 查询导航树信息  
	 * @return: List<Map<String,Object>>      
	 */ 
	List<Map<String, Object>> queryNavigationBarTrees(String cityOpenInfoId);
	
	/**
	 * 
	 * @Title: queryAllNavigationNameByLevel   
	 * @Description:  查询所有导航条名字，通过等级
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>>  queryAllNavigationNameByLevel(@Param("level")String level);
	/**
	 * @Title: queryIsExistNavigateCode   
	 * @Description: 查询导航编号是否已存在  
	 * @param navigateCode  导航编号
	 * @return: long
	 */
	public long queryIsExistNavigateCode(@Param("navigateCode")String navigateCode);
	
}
