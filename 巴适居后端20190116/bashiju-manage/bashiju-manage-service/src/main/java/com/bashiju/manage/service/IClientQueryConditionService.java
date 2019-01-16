package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 
 *   pc端列表页面搜索条件管理
 * @ClassName:  IClientQueryConditionService   
 * @Description:   pc端列表页面搜索条件管理
 * @author: wangkaifa
 * @date:   2018年12月26日 上午11:24:24       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface IClientQueryConditionService {
	/**
	 * 
		 * 根据类型查询搜索条件，类型为空查询所有
		 * @Description: 根据类型查询搜索条件，类型为空查询所有 
		 * @param type 条件类型：0--出售房源，1--出租房源，2--新房，3--小区
		 * @return 
		 * Page<Map<String,Object>>
	 */
	public Page<Map<String, Object>>  queryConditionList(Integer type,int page,int limit);
	/**
	 * 
		 * 新增查询条件
		 * @Description: 新增查询条件 
		 * @param jsonData
		 * @return 
		 * Integer
	 */
	public Integer addQueryCondition(String jsonData);
	/**
	 * 
		 * 修改查询条件
		 * @Description: 修改查询条件 
		 * @param jsonData
		 * @return 
		 * Integer
	 */
	public Integer updateQueryCondition(String jsonData);
	/**
	 * 
		 * 删除查询条件
		 * @Description: 删除查询条件
		 * @param id
		 * @return 
		 * Integer
	 */
	public Integer deleteQueryCondition(Integer id);
	/**
	 * 
		 * 搜索条件查询树结构数据
		 * @Description: 搜索条件查询树结构数据
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>>  queryConditionTree();
	/**
	 * 
		 * 查询城市条件列表
		 * @Description: 查询城市条件列表
		 * @param cityCode
		 * @param type
		 * @return 
		 * Page<Map<String,Object>>
	 */
	public Page<Map<String, Object>>  queryCityConditionList(String cityCode,Integer type,int page,int limit);
	/**
	 * 
		 * 编辑城市条件类型
		 * @Description: 编辑城市条件类型
		 * @param JsonData 
		 * void
	 */
	public void saveCityCondition(String cityCode,String JsonData);
	/**
	 * 
		 * 查询价格条件城市管理列表
		 * @Description: 查询价格条件城市管理列表 
		 * @param cityCode
		 * @param type
		 * @return 
		 * Page<Map<String,Object>>
	 */
	public Page<Map<String, Object>>  queryCityAvgPriceList(String cityCode,Integer type,int page,int limit);
	/**
	 * 
		 * 新增均价查询条件
		 * @Description: 新增均价查询条件 
		 * @param jsonData
		 * @return 
		 * Integer
	 */
	public Integer addCityAvgPrice(String jsonData);
	/**
	 * 
		 * 修改均价查询条件
		 * @Description: 修改均价查询条件 
		 * @param jsonData
		 * @return 
		 * Integer
	 */
	public Integer updateCityAvgPrice(String jsonData);
	/**
	 * 
		 * 删除均价查询条件
		 * @Description: 删除均价查询条件
		 * @param id
		 * @return 
		 * Integer
	 */
	public Integer deleteCityAvgPrice(Integer id);
	/**
	 * 
		 * 查询城市搜索条件id
		 * @Description: 查询城市搜索条件id
		 * @param cityCode
		 * @return 
		 * List<Integer>
	 */
	public List<Integer> queryCityConditionIds(String cityCode);
}
