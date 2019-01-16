package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**
 * 
 *   pc端列表页面搜索条件管理
 * @ClassName:  ClientQueryConditionMapper   
 * @Description:   pc端列表页面搜索条件管理
 * @author: wangkaifa
 * @date:   2018年12月26日 上午11:14:36       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ClientQueryConditionMapper {
	/**
	 * 
		 * 查询搜索条件管理主表列表
		 * @Description: 查询搜索条件管理主表列表
		 * @param map
		 * @return 
		 * Page<Map<String,Object>>
	 */
	public Page<Map<String, Object>>  queryConditionList(Map<String, Object> map);
	/**
	 * 
		 * 查询搜索条件城市管理列表
		 * @Description: 查询搜索条件城市管理列表 
		 * @param map
		 * @return 
		 * Page<Map<String,Object>>
	 */
	public Page<Map<String, Object>>  queryCityConditionList(Map<String, Object> map);
	/**
	 * 
		 * 查询价格条件城市管理列表
		 * @Description: 查询价格条件城市管理列表 
		 * @param map
		 * @return 
		 * Page<Map<String,Object>>
	 */
	public Page<Map<String, Object>>  queryCityAvgPriceList(Map<String, Object> map);
	/**
	 * 
		 * 查询城市搜索条件id
		 * @Description: 查询城市搜索条件id
		 * @param cityCode
		 * @return 
		 * List<Integer>
	 */
	public List<Integer> queryCityConditionIds(@Param("cityCode")String cityCode);
}
