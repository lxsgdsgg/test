package com.bashiju.housing.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  BuildingMapper   
 * @Description:TODO(座栋管理)   
 * @author: wangkaifa
 * @date:   2018年4月25日 下午12:04:51   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface BuildingMapper {
	/**
	 * 
	 * @Title: queryBuildingsByCommunity   
	 * @Description: TODO(根据小区出现楼栋列表)   
	 * @param: @param cid 小区id
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>>  queryBuildingsByCommunity(@Param("cid") Long cid);
	/**
	 * 
	 * @Title: queryBuildingUnitsByBuild   
	 * @Description: TODO(根据楼栋号查询单元列表)   
	 * @param: @param bid 楼栋id
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>>  queryBuildingUnitsByBuild(@Param("bid") Long bid);
	/**
	 * 
	 * @Title: queryHouseByUnit   
	 * @Description: TODO(根据单元查询房号列表)   
	 * @param: @param buid 单元id
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>>  queryHouseByUnit(@Param("buid") Long buid);
	/**
	 * 
	 * @Title: queryBuildingsById   
	 * @Description: TODO(根据id查询楼栋信息)   
	 * @param: @param id 楼栋id
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object>  queryBuildingsById(@Param("id") Long id);
	/**
	 * 
	 * @Title: queryBuildingUnitsById   
	 * @Description: TODO(根据楼栋单元编号查询单元信息)   
	 * @param: @param id 楼栋单元id
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object>  queryBuildingUnitsById(@Param("id") Long id);
	/**
	 * 
	 * @Title: countBuildingsByName   
	 * @Description: TODO(查询座栋名称是否存在)   
	 * @param: @param name 座栋名称
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	public long countBuildingsByName(@Param("name") String name,@Param("communityId") Long communityId);
	/**
	 * 
	 * @Title: countBuildingUnitsByName   
	 * @Description: TODO(查询座栋单元名称是否存在)   
	 * @param: @param name 座栋单元名称
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	public long countBuildingUnitsByName(@Param("name") String name,@Param("buildingId") Long buildingId);
	
	/**
	 * @Title: getRegionList   
	 * @Description: TODO(根据用户当前区划查询小区数据信息)   
	 * @param: @param rgCode:行政区划编码
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String,Object>> getRegionList(@Param("rgCode") String rgCode);
}
