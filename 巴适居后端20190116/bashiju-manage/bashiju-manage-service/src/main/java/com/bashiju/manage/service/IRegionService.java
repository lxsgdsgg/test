package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  IRegionService   
 * @Description:TODO(片区管理)   
 * @author: zhaobin
 * @date:   2018年4月19日 上午11:56:44   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface IRegionService {
	 /**
	  * 
	  * @Title: queryArea   
	  * @Description: TODO(获取行政区划列表)   
	  * @param: @return      
	  * @return: List<Map<String,Object>>      
	  * @throws
	  */
	Page<Map<String, Object>> queryArea(String code,int pageNum, int pageSize);
	/**
	 * 
	 * @Title: queryRegion   
	 * @Description: TODO(根据区划码查询片区列表)   
	 * @param: @param areaCode
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	 Page<Map<String, Object>> queryRegion(String areaCode, int pageNum, int pageSize);
	 /**
	  * 
	  * @Title: queryRegionByName   
	  * @Description: TODO(根据名称查询片区)   
	  * @param:  name
	  * @param: cityCode
	  * @param:  pageNum
	  * @param:  pageSize
	  * @param: @return      
	  * @return: Page<Map<String,Object>>      
	  * @throws
	  */
	 Page<Map<String, Object>> queryRegionByName(String cityCode,String name, int pageNum, int pageSize);
	 /**
	  * 
	  * @Title: queryAreaSelect   
	  * @Description: TODO(行政区划下拉数据源)   
	  * @param: @return      
	  * @return: List<Map<String,Object>>      
	  * @throws
	  */
	 public List<Map<String, Object>> queryAreaSelect();
	/**
	 * 
	 * @Title: updateArea   
	 * @Description: TODO(修改行政区划坐标)   
	 * @param: @param code
	 * @param: @param lng
	 * @param: @param lat
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	long updateArea (String code,String lng,String lat);
	/**
	 * 
	 * @Title: updateRegion   
	 * @Description: TODO(修改片区坐标)   
	 * @param: @param id
	 * @param: @param lng
	 * @param: @param lat
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	long updateRegion (Long id,String lng,String lat);
	/**
	 * 
	 * @Title: addRegion   
	 * @Description: TODO(添加片区)   
	 * @param: @param name
	 * @param: @param code
	 * @param: @param lng
	 * @param: @param lat
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	String addRegion (String name,String code,String lng,String lat);
	/**
	 * 
		 * 修改片区
		 * @Description: 修改片区
		 * @param id 片区id
		 * @param name 名称
		 * @param code 区域代码
		 * @return 
		 * long
	 */
	long updateName(Long id,String name,String code);
	/**
	 * 
		 * 删除片区
		 * @Description: 删除片区
		 * @param id 片区id
		 * @return 
		 * boolean
	 */
	boolean deleteRegion(Long id);
	/**
	 * 
		 * 根据城市查询所有片区
		 * @Description: 根据城市查询所有片区
		 * @param cityCode 城市代码
		 * @return  {id:片区id,name:片区名称,longitude:经度,latitude:纬度,areaCode:区域代码}
		 * List<Map<String,Object>>
	 */	
	public List<Map<String, Object>> queryRegionsByCity(String cityCode);
}
