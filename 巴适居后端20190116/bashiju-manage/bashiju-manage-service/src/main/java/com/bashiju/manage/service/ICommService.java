package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ICommService {
	/**
	 * 
		 * 查询省、市所有数据
		 * @Description: 查询省、市所有数据
		 * @return 
		 * List<Map<String,Object>>
	 */
	List<Map<String, Object>> queryAllCity();
	/**
	 * 
		 * 查询开通城市
		 * @Description: 查询开通城市
		 * @return 
		 * List<Map<String,Object>>
	 */
	List<Map<String, Object>> queryOpenCity();
	/**
	 * 
		 * 查询开通省、市
		 * @Description: 查询开通省、市 
		 * @return 
		 * List<Map<String,Object>>
	 */
	List<Map<String, Object>> queryOpenProvinceCity();
	/**
	 * 
		 * 查询市、县开通数据
		 * @Description: 查询市、县开通数据
		 * @return 
		 * List<Map<String,Object>>
	 */
	List<Map<String, Object>> queryOpenCityCounty();
	/**
	 * 
		 * 查询开通城市、区县、片区
		 * @Description: 进入菜单主页面（详细描述） 
		 * @return 
		 * List<Map<String,Object>>
	 */
	List<Map<String, Object>> queryOpenCityCountyRegion();
	/**
	 * 
		 * 查询开通城市、区县、片区、小区
		 * @Description: 查询开通城市、区县、片区、小区 
		 * @return 
		 * List<Map<String,Object>>
	 */
	List<Map<String, Object>> queryOpenCityCountyRegionCommunity();
	/**
	 * 
		 * 查询开通城市、区县、片区、小区(包括楼盘)
		 * @Description: 查询开通城市、区县、片区、小区 (包括楼盘)
		 * @return 
		 * List<Map<String,Object>>
	 */
	List<Map<String, Object>> queryOpenCityCountyRegionAllCommunity();
	/**
	 * 
		 *查询开通城市、公司、部门
		 * @Description: 查询开通城市、公司、部门
		 * @return 
		 * List<Map<String,Object>>
	 */
	List<Map<String, Object>> queryOpenCityCompanyDepart();
	/**
	 * 
		 * 查询开通城市、公司、部门、用户
		 * @Description: 查询开通城市、公司、部门、用户
		 * @return 
		 * List<Map<String,Object>>
	 */
	List<Map<String, Object>> queryOpenCityCompanyDepartUser();
	/**
	 * 
		 * 查询开通城市、公司
		 * @Description: 查询开通城市、公司
		 * @return 
		 * List<Map<String,Object>>
	 */
	List<Map<String, Object>> queryOpenCityCompany();
	
	/**
	 * 根据城市区划编码查询 行政区、片区
	 * @Title: queryAreaAndRegion
	 * @author: zuoyuntao  
	 * @Description:根据城市区划编码查询 行政区、片区 
	 * @param cityCode 城市编码
	 * @param deptId 部门ID
	 * @return      
	 * List<Map<String,Object>> 
	 */
	public List<Map<String,Object>> queryAreaAndRegion(String cityCode,String deptId);
	/**
	 * 根据片区ID查询小区信息
	 * @Title: queryCommunityByRegionCode
	 * @author: zuoyuntao  
	 * @Description:根据片区ID查询小区信息   
	 * @param regionId
	 * @return      
	 * Object 
	 */
	public List<Map<String,Object>> queryCommunityByRegionId(String regionId);
	/**
	 * 
		 * 根据权限查询部门用户下拉
		 * @Description: 根据权限查询部门用户下拉 
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryDepartmentUserList();
	
	
	/**
	 * 根据城市编号查询区域
	 * @Title: queryAreaByCityCode   
	 * @Description: T根据城市编号查询区域
	 * @param cityCode
	 * @return  根据城市编号查询区域    
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryAreaByCityCode(String cityCode);
	
	/**
	 * 根据区域编号查询片区
	 * @Title: queryRegionByAreaCode   
	 * @Description:根据区域编号查询片区
	 * @param areaCode
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryRegionByAreaCode(String areaCode);
	
}
