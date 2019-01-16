package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  IBuildingService   
 * @Description:TODO(座栋管理接口)   
 * @author: wangkaifa
 * @date:   2018年4月25日 下午12:02:33   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface IBuildingService {

	/**
	 * 
	 * @Title: queryBuildingsByCommunity   
	 * @Description: TODO(根据小区出现楼栋列表)   
	 * @param: @param cid 小区id
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>>  queryBuildingsByCommunity(Long cid,int page,int limit);
	/**
	 * 
	 * @Title: queryBuildingUnitsByBuild   
	 * @Description: TODO(根据楼栋号查询单元列表)   
	 * @param: @param bid 楼栋id
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>>  queryBuildingUnitsByBuild(Long bid,int page,int limit);
	/**
	 * 
	 * @Title: queryHouseByUnit   
	 * @Description: TODO(根据单元查询房号列表)   
	 * @param: @param buid 单元id
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>>  queryHouseByUnit(Long buid);
	/**
	 * 
	 * @Title: queryBuildingsById   
	 * @Description: TODO(根据id查询楼栋信息)   
	 * @param: @param id 楼栋id
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object>  queryBuildingsById(Long id);
	/**
	 * 
	 * @Title: queryBuildingUnitsById   
	 * @Description: TODO(根据楼栋单元编号查询单元信息)   
	 * @param: @param id 楼栋单元id
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object>  queryBuildingUnitsById(Long id);
	/**
	 * 
	 * @Title: addBuilding   
	 * @Description: TODO(添加座栋)   
	 * @param: @param param
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	public long addBuilding (Map<String, Object> param);
	/**
	 * 
	 * @Title: batchAddBuilding   
	 * @Description: TODO(批量添加座栋)   
	 * @param: @param param
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	public long batchAddBuilding (Map<String, Object> param);
	/**
	 * 
	 * @Title: addBuildingUnit   
	 * @Description: TODO(添加座栋单元)   
	 * @param: @param param
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	public long addBuildingUnit (Map<String, Object> param);
	/**
	 * 
	 * @Title: updateBuildingUnit   
	 * @Description: TODO(修改座栋单元信息)   
	 * @param: @param param
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	public long updateBuildingUnit (Map<String, Object> param);
	/**
	 * 
	 * @Title: deleteBuildingUnit   
	 * @Description: TODO(删除座栋单元)   
	 * @param: @param id
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	public long deleteBuildingUnit (Long id);
	/**
	 * 
	 * @Title: updateBuilding   
	 * @Description: TODO(修改座栋信息)   
	 * @param: @param param
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	public long updateBuilding (Map<String, Object> param);
	/**
	 * 
	 * @Title: deleteBuilding   
	 * @Description: TODO(删除座栋)   
	 * @param: @param id
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	public long deleteBuilding (Long id);
	/**
	 * 
	 * @Title: saveHouses   
	 * @Description: TODO(保存房屋号)   
	 * @param: @param param
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	public long saveHouses (Map<String, Object> param);
	
	/**
	 * 
		 * 新，根据单元id查询房号，按照楼层拼接
		 * @Description:  新，根据单元id查询房号，按照楼层拼接
		 * @param buildingUnitId 单元id
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryBuildingHouseByUnitId(Long buildingUnitId);
}
