package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  RegionMapper   
 * @Description:TODO(片区管理)   
 * @author: wangkaifa
 * @date:   2018年4月19日 上午11:51:09   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface RegionMapper {

	/**
	 * 
	 * @Title: queryArea   
	 * @Description: TODO(查询所有行政区划)   
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>> queryArea(@Param("code") String code);
	/**
	 * 
	 * @Title: queryAreaSelect   
	 * @Description: TODO(行政区域下拉框列表)   
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> queryAreaSelect();
	
	/**
	 * 
	 * @Title: queryRegion   
	 * @Description: TODO(根据行政区划码查询片区)   
	 * @param: @param code
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Page<Map<String, Object>>  queryRegion(@Param("code") String code);
	/**
	 * 
	 * @Title: queryRegionByName   
	 * @Description: TODO(根据片区名称查询)   
	 * @param:  name
	 * @param: cityCode
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>>  queryRegionByName(@Param("name") String name,@Param("cityCode") String cityCode);
	/**
	 * 
	 * @Title: countRegionByName   
	 * @Description: TODO(根据名称查询片区条数)   
	 * @param: @param name 片区名称
	 * @param: @param code 区划码
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	public long countRegionByName(@Param("name") String name,@Param("code") String code);
	/**
	 * 
		 * 根据城市查询所有片区
		 * @Description: 根据城市查询所有片区
		 * @param cityCode 城市代码
		 * @return  {id:片区id,name:片区名称,longitude:经度,latitude:纬度,areaCode:区域代码}
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryRegionsByCity(@Param("cityCode") String cityCode);
	/**
	 * 
		 * 查询片区下的小区数量
		 * @Description: 查询片区下的小区数量
		 * @param regionId 片区id
		 * @return 
		 * int
	 */
	public int queryCommunityCount(@Param("regionId") Long regionId);
}
