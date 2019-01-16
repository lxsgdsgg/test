package com.bashiju.anal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OperationAnalysisMapper {
	/**
	 * 
		 * 查询综合运营分析报表
		 * @Description: 查询综合运营分析报表 
		 * @param param
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryComprehensiveReport(Map<String, Object> param);
	/**
	 * 
		 * 根据控件编号查询控件下拉项
		 * @Description: 根据控件编号查询控件下拉项 
		 * @param widgetCode
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, String>> queryWidgetList(@Param("widgetCode")String widgetCode);
	/**
	 * 
		 * 房源状态跟进统计
		 * @Description: 房源状态跟进统计
		 * @param param
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryHouseStatusFollowUpReport(Map<String, Object> param);
	/**
	 * 
		 * 房源跟进方式跟进统计
		 * @Description: 房源跟进方式跟进统计
		 * @param param
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryHouseFollowUpTypeReport(Map<String, Object> param);
	/**
	 * 
		 * 客源状态跟进统计
		 * @Description: 客源状态跟进统计
		 * @param param
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryCustomerStatusFollowUpReport(Map<String, Object> param);
	/**
	 * 
		 * 客源跟进方式跟进统计
		 * @Description: 客源跟进方式跟进统计
		 * @param param
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryCustomerFollowUpTypeReport(Map<String, Object> param);
	
	/**
	 * 
		 * 房源维护频率分析
		 * @Description: 房源维护频率分析 
		 * @param param
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryHouseFollowUpCycleReport(Map<String, Object> param);
	/**
	 * 
		 * 客源维护频率分析
		 * @Description: 客源维护频率分析
		 * @param param
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryCustomerFollowUpCycleReport(Map<String, Object> param);
	
	/**
	 * 
		 * 社区精耕运营分析
		 * @Description: 社区精耕运营分析 
		 * @param param
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryCommunityDealReport(Map<String, Object> param);
}
