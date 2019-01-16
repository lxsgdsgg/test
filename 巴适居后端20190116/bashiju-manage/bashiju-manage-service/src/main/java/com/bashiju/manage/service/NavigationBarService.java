package com.bashiju.manage.service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  NavigationBarService   
 * @Description:导航栏管理接口 
 * @author: wangpeng
 * @date:   2018年7月24日 上午9:33:33   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface NavigationBarService {
	
	/**
	 * @Title: queryNavigationBarData   
	 * @Description: 条件查询 导航栏  
	 * @param page 当前页
	 * @param limit 每页总跳数
	 * @param paramMap 查询参数
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryNavigationBarData(int page,int limit,Map<String,Object> paramMap);	
	
	/**
	 * @Title: queryNavigationBarById   
	 * @Description: 条件查询 导航栏
	 * @param navigationBarId id
	 * @return: Map<String,Object>
	 */
	public Map<String, Object> queryNavigationBarById(String navigationBarId);
	
	/**
	 * @Description: 查询导航树信息  
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryNavigationBarTrees(String cityOpenInfoId);
	
	/**
	 * @Title: delNavigationBarById   
	 * @Description: 逻辑删除导航栏
	 * @param navigationBarId  导航栏id
	 * @return: boolean
	 */
	public boolean delNavigationBarById(String navigationBarId);
	
	/**
	 * 
	 * @Title: saveOrUpdataNavigationBar   
	 * @Description:新增或编辑导航栏
	 * @param map
	 * @return: boolean
	 */
	public boolean saveOrUpdataNavigationBar(Map<String,Object> map);
	
	/**
	 * @Title: queryAllNavigationNameByLevel   
	 * @Description: 查询所有导航条名字，通过等级
	 * @param level
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryAllNavigationNameByLevel(String level);
	
	
	/**
	 * @Title: queryIsExistNavigateCode   
	 * @Description: 查询导航编号是都已存在
	 * @param navigateCode 导航编号
	 * @return: boolean
	 */
	public boolean queryIsExistNavigateCode(String navigateCode);
	
}
