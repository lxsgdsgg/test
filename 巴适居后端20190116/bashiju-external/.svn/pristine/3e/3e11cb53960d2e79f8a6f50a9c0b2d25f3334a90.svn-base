package com.bashiju.ext.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface LogOperateMapper {
	/**
	 * 通用的添加
	 * @param map
	 * @return
	 */
	public Long commonAdd(Map<String,Object> map);
	
	/**
	 * 
		 * 根据小区查询区域信息
		 * @Description: 根据小区查询区域信息
		 * @param communityId 小区id
		 * @return 
		 * Map<String,Object>
	 */
	public Map<String, Object> queryRegionInfoByCommunityId(@Param("communityId")Long communityId);
	/**
	 * 
		 * 根据楼栋id查询区域信息
		 * @Description: 根据楼栋id查询区域信息 
		 * @param buildingId 楼栋id
		 * @return 
		 * Map<String,Object>
	 */
	public Map<String, Object> queryRegionInfoByBuildingId(@Param("buildingId")Long buildingId);
	/**
	 * 
		 * 根据楼栋id查询房源信息
		 * @Description: 根据楼栋id查询房源信息
		 * @param buildIds
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryHouseNumberInfoByBuildingIds(@Param("buildIds")List<Long>buildIds);
	/**
	 * 
		 *根据单元id查询房源信息
		 * @Description: 根据单元id查询房源信息
		 * @param unitId 单元id
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryHouseNumberInfoByUnitId(@Param("unitId")Long unitId);
}
